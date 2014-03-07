package ro.alex.browser.ui.view;

import ro.alex.browser.ui.window.DeleteRezervarePrompt;
import ro.alex.browser.ui.window.ModifyHourView;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.RezervareProxy;
import ro.alex.shared.scaffold.ScaffoldRequestFactory.RezervareScaffoldRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.sencha.gxt.cell.core.client.form.TextInputCell;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.axis.GaugeAxis;
import com.sencha.gxt.chart.client.chart.series.GaugeSeries;
import com.sencha.gxt.chart.client.chart.series.SeriesToolTipConfig;
import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.Style.Side;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.fx.client.easing.EasingFunction;
import com.sencha.gxt.fx.client.easing.ElasticIn;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class PersoanaView implements IsWidget{

	public interface AbonamentProxyPropertyAccess extends PropertyAccess<AbonamentProxy> {
		ValueProvider<AbonamentProxy, Integer> kilometri();

		ValueProvider<AbonamentProxy, String> serie();

		@Path("serie")
		ModelKeyProvider<AbonamentProxy> serieKey();
	}

	private static final AbonamentProxyPropertyAccess AbonamentProxyAccess = GWT.create(AbonamentProxyPropertyAccess.class);
	private PersoanaProxy pp;
	private ApplicationRequestFactory requestFactory;
	private VerticalLayoutContainer layout = new VerticalLayoutContainer();
	private ToolBar toolBar = new ToolBar();
	private ToolBar toolBarJos = new ToolBar();
	private TextButton btnModifica, btnAbonament, btnInchide;
	private TextField tf;
	private HTML text;
	private RezervareProxy rezProxy;
	private ModifyHourView mhv;

	public PersoanaView(PersoanaProxy pp, ApplicationRequestFactory requestFactory){
		this.pp = pp;
		this.requestFactory = requestFactory;
	}

	@Override
	public Widget asWidget() {
		

		ContentPanel panel = new FramedPanel();
		panel.getElement().getStyle().setMargin(10, Unit.PX);
		panel.setCollapsible(true);
		panel.setHeadingText("Persoana View");
		panel.setPixelSize(600, 500);
		panel.setBodyBorder(true);
		panel.setHeaderVisible(false);

		panel.add(layout);

		Label lbl = new Label("Bine ai venit "+ pp.getUser().getNick());
		toolBar.add(lbl);

		toolBar.add(new SeparatorToolItem());

		TextButton btn = new TextButton("            ");
		toolBar.add(btn);

		toolBar.add(new SeparatorToolItem());

		btnModifica = new TextButton("Modifica rezervare existenta");
		btnModifica.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				TextButton tb = (TextButton)event.getSource();
				modificaRezervare();
				toolBarJos.add(new TextButton("Modifica ora", getHandler()));
				toolBarJos.add(new TextButton("Anuleaza rezervarea", getHandler()));
				toolBarJos.add(btnInchide = new TextButton("Inapoi", getHandler()));
				tb.setEnabled(false);
			}
		});
		toolBar.add(btnModifica);

		TextInputCell tic = new TextInputCell();
		tf = new TextField(tic);

		toolBar.add(tf);
		toolBar.add(new SeparatorToolItem());

		
		layout.setVisible(true);

		if(pp.getAbonament() == null){
			btnAbonament = new TextButton("Creeaza abonament", getHandler());
		}else{
			final ListStore<AbonamentProxy> store = new ListStore<AbonamentProxy>(AbonamentProxyAccess.serieKey());
			store.add(pp.getAbonament());

			final Chart<AbonamentProxy> chart = createGauge(store, 80, new RGB("#3AA8CB"), false, new ElasticIn(), AbonamentProxyAccess.kilometri());

			chart.setAnimated(true);
			chart.hide();
			chart.setLayoutData(new VerticalLayoutData(400, 200));
			chart.setPixelSize(200, 100);
			layout.add(chart);
			btnAbonament = new TextButton("Abonament");
			
			final SeriesToolTipConfig<AbonamentProxy> tt = new SeriesToolTipConfig<AbonamentProxy>();
			tt.setMouseOffset(new int[] {0, 0});
			tt.setAnchor(Side.LEFT);
			tt.setMinWidth(315);
			tt.setMaxWidth(615);

			btnAbonament.setToolTipConfig(tt);
			btnAbonament.getToolTip().addShowHandler(new ShowHandler() {

				@Override
				public void onShow(ShowEvent event) {
					store.clear();
					store.add(pp.getAbonament());
					chart.setAnimated(event.getSource().isEnabled());
					chart.redrawChart();
					tt.setBodyHtml(chart.getElement().getInnerHTML());
					btnAbonament.setToolTipConfig(tt);
				}
			});
		}
		toolBar.add(btnAbonament);
		layout.add(toolBar, new VerticalLayoutData(1, -1));

		return panel;
	}
	
	public void modificaRezervare(){
		Info.display("Cod Rezervare", tf.getValue());

		RezervareScaffoldRequest rezervareScaffoldRequest = requestFactory.rezervareScaffoldRequest();
		rezervareScaffoldRequest.getRezervareByCod(tf.getValue()).with("*","*.*").
		fire(new Receiver<RezervareProxy>() {

			@Override
			public void onSuccess(RezervareProxy rezervare) {
				//					ZborProxy zpDus = rezervare.getZborDus();
				String escalaStr = rezervare.getZborDus().getEscala() ? "Da" : "Nu";
				rezProxy = rezervare;

				String htmlString = "";
				if( rezervare.getDusIntors() ){
					htmlString = "<table align='center' cellpadding='1' cellspacing='1' style='border-width:1px; border-style:solid; border-color:#999; width: 582px;'>"+
							"<tbody>"+
							"<tr>"+
							"	<td>"+
							"		&nbsp;</td>"+
							"	<td>"+
							"		<strong><span style='color:#a9a9a9;'>Cursa dus "+ rezervare.getZborDus().getCod() +"</span></strong></td>"+
							"	<td>"+
							"		&nbsp;</td>"+
							"	<td>"+
							"		&nbsp;</td>"+
							"</tr>"+
							"<tr>"+
							"	<td>"+
							"		<strong>Oras plecare:&nbsp;</strong></td>"+
							"	<td>"+
							"		"+ rezervare.getZborDus().getOrasp().getDenumire() +"</td>"+
							"	<td>"+
							"		<strong>Oras destinatie:</strong></td>"+
							"	<td>"+
							"		"+ rezervare.getZborDus().getOrasd().getDenumire() +"</td>"+
							"</tr>"+
							"<tr>"+
							"	<td>"+
							"		<strong>Data plecare: </strong></td>"+
							"		<td>"+
							"		"+ DateTimeFormat.getFormat("dd-MM-yyyy").format(rezervare.getZborDus().getData()) +"</td>"+
							"	<td>"+
							"		<strong>Ora:</strong></td>"+
							"	<td>"+
							"		"+ DateTimeFormat.getFormat("hh:mm aaa").format(rezervare.getZborDus().getData()) +"</td>"+
							"</tr>"+
							"<tr>"+
							"	<td>"+
							"		<strong>Loc: </strong></td>"+
							"	<td>"+
							"		"+ rezervare.getLocDus().getNumar() +"</td>"+
							"	<td>"+
							"		<strong>Clasa:</strong></td>"+
							"	<td>"+
							"		"+ rezervare.getLocDus().getClasa().getClasa() +"</td>"+
							"</tr>"+
							"<tr>"+
							"	<td>"+
							"		&nbsp;</td>"+
							"	<td>"+
							"			<strong><span style='color:#a9a9a9;'>Cursa intors "+ rezervare.getZborIntors().getCod() +"</span></strong></td>"+
							"	<td>"+
							"		&nbsp;</td>"+
							"	<td>"+
							"		&nbsp;</td>"+
							"</tr>"+
							"<tr>"+
							"	<td>"+
							"		<strong>Oras plecare:</strong></td>"+
							"	<td>"+
							"		"+ rezervare.getZborIntors().getOrasp().getDenumire() +"</td>"+
							"	<td>"+
							"		<strong>Oras destinatie:</strong></td>"+
							"	<td>"+
							"		"+ rezervare.getZborIntors().getOrasd().getDenumire() +"</td>"+
							"</tr>"+
							"<tr>"+
							"	<td>"+
							"		<strong>Data intoarcere:</strong></td>"+
							"	<td>"+
							"		"+ DateTimeFormat.getFormat("dd-MM-yyyy").format(rezervare.getZborIntors().getData()) +"</td>"+
							"	<td>"+
							"		<strong>Ora:</strong></td>"+
							"	<td>"+
							"		"+ DateTimeFormat.getFormat("hh:mm aaa").format(rezervare.getZborIntors().getData()) +"</td>"+
							"</tr>"+
							"<tr>"+
							"	<td>"+
							"		<strong>Loc: </strong></td>"+
							"	<td>"+
							"		"+ rezervare.getLocIntors().getNumar() +"</td>"+
							"	<td>"+
							"		<strong>Clasa:</strong></td>"+
							"	<td>"+
							"		&nbsp;</td>"+
							"</tr>"+
							"<tr>"+
							"	<td>"+
							"		&nbsp;</td>"+
							"	<td>"+
							"		<strong><span style='color:#a9a9a9;'>Informatii aditionale</span></strong></td>"+
							"	<td>"+
							"		&nbsp;</td>"+
							"	<td>"+
							"		&nbsp;</td>"+
							"</tr>"+
							"<tr>"+
							"	<td>"+
							"		<strong>Nume:</strong></td>"+
							"	<td>"+
							"		"+ rezervare.getNume() +"</td>"+
							"	<td>"+
							"		<strong>Prenume:</strong></td>"+
							"	<td>"+
							"		"+ rezervare.getPrenume() +"</td>"+
							"</tr>"+
							"<tr>"+
							"		<td>Escala:</td>"+
							"	<td>"+
							"		"+ escalaStr +"</td>"+
							"	<td>"+
							"		<strong>Cost:</strong></td>"+
							"	<td>"+
							"		"+ 2 * rezervare.getCost() +"</td>"+
							"</tr>"+
							"</tbody>"+
							"</table>";
				}else htmlString = "<table align='center' cellpadding='1' cellspacing='1' style='border-width:1px; border-style:solid; border-color:#999; width: 582px;'>"+
						"<tbody>"+
						"<tr>"+
						"	<td>"+
						"		&nbsp;</td>"+
						"	<td>"+
						"		<strong><span style='color:#a9a9a9;'>Cursa dus "+ rezervare.getZborDus().getCod() +"</span></strong></td>"+
						"	<td>"+
						"		&nbsp;</td>"+
						"	<td>"+
						"		&nbsp;</td>"+
						"</tr>"+
						"<tr>"+
						"	<td>"+
						"		<strong>Oras plecare:&nbsp;</strong></td>"+
						"	<td>"+
						"		"+ rezervare.getZborDus().getOrasp().getDenumire() +"</td>"+
						"	<td>"+
						"		<strong>Oras destinatie:</strong></td>"+
						"	<td>"+
						"		"+ rezervare.getZborDus().getOrasd().getDenumire() +"</td>"+
						"</tr>"+
						"<tr>"+
						"	<td>"+
						"		<strong>Data plecare: </strong></td>"+
						"		<td>"+
						"		"+ DateTimeFormat.getFormat("dd-MM-yyyy").format(rezervare.getZborDus().getData()) +"</td>"+
						"	<td>"+
						"		<strong>Ora:</strong></td>"+
						"	<td>"+
						"		"+ DateTimeFormat.getFormat("hh:mm aaa").format(rezervare.getZborDus().getData()) +"</td>"+
						"</tr>"+
						"<tr>"+
						"	<td>"+
						"		<strong>Loc: </strong></td>"+
						"	<td>"+
						"		"+ rezervare.getLocDus().getNumar() +"</td>"+
						"	<td>"+
						"		<strong>Clasa:</strong></td>"+
						"	<td>"+
						"		"+ rezervare.getLocDus().getClasa().getClasa()+"</td>"+
						"</tr>"+
						"<tr>"+
						"	<td>"+
						"		&nbsp;</td>"+
						"	<td>"+
						"		<strong><span style='color:#a9a9a9;'>Informatii aditionale</span></strong></td>"+
						"	<td>"+
						"		&nbsp;</td>"+
						"	<td>"+
						"		&nbsp;</td>"+
						"</tr>"+
						"<tr>"+
						"	<td>"+
						"		<strong>Nume:</strong></td>"+
						"	<td>"+
						"		"+ rezervare.getNume() +"</td>"+
						"	<td>"+
						"		<strong>Prenume:</strong></td>"+
						"	<td>"+
						"		"+ rezervare.getPrenume() +"</td>"+
						"</tr>"+
						"<tr>"+
						"		<td>Escala:</td>"+
						"	<td>"+
						"		"+ escalaStr +"</td>"+
						"	<td>"+
						"		<strong>Cost:</strong></td>"+
						"	<td>"+
						"		"+ rezervare.getCost() +"</td>"+
						"</tr>"+
						"</tbody>"+
						"</table>";
				text = new HTML(htmlString);
				text.getElement().getStyle().setOverflowY(Overflow.AUTO);
				layout.add(text, new VerticalLayoutData(1, 1));
				layout.add(toolBarJos, new VerticalLayoutData(1, -1));
			}

			@Override
			public void onFailure(ServerFailure sf){
				Window.alert("Rezervarea cu codul "+ tf.getValue() +" nu a fost gasita");
			}
		});
	}

	private Chart<AbonamentProxy> createGauge(ListStore<AbonamentProxy> store, double donut, Color color, boolean needle,
			EasingFunction easing, ValueProvider<AbonamentProxy, Integer> provider) {
		Chart<AbonamentProxy> chart = new Chart<AbonamentProxy>();
		chart.setStore(store);
		chart.setAnimationDuration(750);
		chart.setAnimationEasing(easing);
		chart.setDefaultInsets(20);
		chart.setTitle("Kilometri ramasi");

		GaugeAxis<AbonamentProxy> axis = new GaugeAxis<AbonamentProxy>();
		axis.setMargin(8);
		axis.setDisplayGrid(true);
		axis.setMinimum(0);
		axis.setMaximum(2000);
		TextSprite title = new TextSprite("Kilometri ramasi");
		axis.setTitleConfig(title);
		chart.addAxis(axis);

		final GaugeSeries<AbonamentProxy> gauge = new GaugeSeries<AbonamentProxy>();
		gauge.addColor(color);
		gauge.addColor(new RGB("#ddd"));
		gauge.setAngleField(provider);
		gauge.setNeedle(needle);
		gauge.setDonut(donut);
		chart.addSeries(gauge);

		return chart;
	}

	public SelectHandler getHandler() {
//		TextButton tb = (TextButton)event.getSource();
		SelectHandler sh = new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				TextButton tb = (TextButton)event.getSource();
				if( tb.getText() == "Modifica ora" ) {
					Info.display("Event", "Modifica ora "+ rezProxy.getCod());
					mhv = new ModifyHourView(requestFactory, rezProxy, tb);
					mhv.getWindow().show();
				}
				if( tb.getText() == "Anuleaza rezervarea" ) {
					Info.display("Event", "Anuleaza rezervare");
					DeleteRezervarePrompt deleteRezervarePrompt = new DeleteRezervarePrompt(requestFactory, rezProxy, tb);
					deleteRezervarePrompt.getWindow().show();
				}
				if( tb.getText() == "Inapoi"){
					Info.display("Event", "Inapoi");
					//				panel.clear();
					btnModifica.setEnabled(true);
					layout.remove(text);
					layout.remove(toolBarJos);
					tf.clear();
				}
				if( tb.getText() == "Creeaza abonament"){
					layout.add(new CreateAbonamentFormView(requestFactory, layout, pp, btnAbonament));
//					tb.setText("Abonament");
				}
				
			}
		};
		return sh;
	}
}

