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
import com.google.gwt.event.shared.EventBus;
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
import com.sencha.gxt.core.client.Style.Side;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.fx.client.easing.EasingFunction;
import com.sencha.gxt.fx.client.easing.ElasticIn;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class UserView implements IsWidget{

	private ApplicationRequestFactory requestFactory;
	private EventBus eventBus;
	private PersoanaProxy pp;
	private TextField tf;
	private HTML text;
	private VerticalLayoutContainer con = new VerticalLayoutContainer();
	private TextButton btnModifica, btnAbonament, btnInchide;
	private RezervareProxy rezProxy;
	private ModifyHourView mhv;
	private SamplePanel panel;
	private Chart<AbonamentProxy> chart;

	public interface AbonamentProxyPropertyAccess extends PropertyAccess<AbonamentProxy> {
		ValueProvider<AbonamentProxy, Integer> kilometri();

		ValueProvider<AbonamentProxy, String> serie();

		@Path("serie")
		ModelKeyProvider<AbonamentProxy> serieKey();
	}
	private static final AbonamentProxyPropertyAccess AbonamentProxyAccess = GWT.create(AbonamentProxyPropertyAccess.class);

	public UserView(PersoanaProxy pp, ApplicationRequestFactory requestFactory){
		this.pp = pp;
		this.requestFactory = requestFactory;
		panel = new SamplePanel();
	}

	@Override
	public Widget asWidget() {
		FlowLayoutContainer con = new FlowLayoutContainer();
		con.getScrollSupport().setScrollMode(ScrollMode.AUTO);
		
//		final ListStore<AbonamentProxy> store = new ListStore<AbonamentProxy>(AbonamentProxyAccess.serieKey());
//		store.add(pp.getAbonament());
//		
//		final Chart<AbonamentProxy> chart = createGauge(store, 80, new RGB("#3AA8CB"), false, new ElasticIn(), AbonamentProxyAccess.kilometri());
//		
//		ContentPanel panel = new FramedPanel();
//	    panel.getElement().getStyle().setMargin(10, Unit.PX);
//	    panel.setCollapsible(true);
//	    panel.setHeadingText("Gauge Chart");
//	    panel.setPixelSize(420, 700);
//	    panel.setBodyBorder(true);
//	 
//	    VerticalLayoutContainer layout = new VerticalLayoutContainer();
//	    panel.add(layout);
//	    
//	    chart.setLayoutData(new VerticalLayoutData(400, 200));
//	    layout.add(chart);
//		
//		final TextButton test = new TextButton("Test ToolTip");
//	    final SeriesToolTipConfig<AbonamentProxy> tt = new SeriesToolTipConfig<AbonamentProxy>();
//
//	    tt.setLabelProvider(null);
//	    tt.setMouseOffset(new int[] {0, 0});
//	    tt.setAnchor(Side.LEFT);
//	    tt.setMinWidth(515);
//	    
//	    test.setToolTipConfig(tt);
//	    test.getToolTip().addShowHandler(new ShowHandler() {
//			
//			@Override
//			public void onShow(ShowEvent event) {
//				chart.redrawChartForced();
//			    tt.setBodyHtml(chart.getElement().getInnerHTML());
//			    test.setToolTipConfig(tt);
//			}
//		});
//	    layout.add(test);
//	    con.add(chart);
		con.add(createStandard(), new MarginData(10));
		return con;
	}

//	private Chart<AbonamentProxy> createGauge(ListStore<AbonamentProxy> store, double donut, Color color, boolean needle,
//			EasingFunction easing, ValueProvider<AbonamentProxy, Integer> provider) {
//		Chart<AbonamentProxy> chart = new Chart<AbonamentProxy>();
//		chart.setStore(store);
//		chart.setAnimationDuration(750);
//		chart.setAnimationEasing(easing);
//		chart.setDefaultInsets(20);
//
//		GaugeAxis<AbonamentProxy> axis = new GaugeAxis<AbonamentProxy>();
//		axis.setMargin(8);
//		axis.setDisplayGrid(true);
//		axis.setMinimum(0);
//		axis.setMaximum(1500);
//		chart.addAxis(axis);
//
//		final GaugeSeries<AbonamentProxy> gauge = new GaugeSeries<AbonamentProxy>();
//		gauge.addColor(color);
//		gauge.addColor(new RGB("#ddd"));
//		gauge.setAngleField(provider);
//		gauge.setNeedle(needle);
//		gauge.setDonut(donut);
//		chart.addSeries(gauge);
//
//		return chart;
//	}

	private ContentPanel createStandard() {

		panel.setHeaderVisible(false);

		Label lbl = new Label("Bine ai venit "+ pp.getUser().getNick());
		panel.getToolbarSus().add(lbl);

		panel.getToolbarSus().add(new SeparatorToolItem());

		TextButton btn = new TextButton("            ");
		panel.getToolbarSus().add(btn);

		panel.getToolbarSus().add(new SeparatorToolItem());

		btnModifica = new TextButton("Modifica rezervare existenta");
		btnModifica.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				TextButton tb = (TextButton) event.getSource();
				panel.modificaRezervare();
				tb.setEnabled(false);
			}
		});
		panel.getToolbarSus().add(btnModifica);

		TextInputCell tic = new TextInputCell();
		tf = new TextField(tic);

		panel.getToolbarSus().add(tf);
		panel.getToolbarSus().add(new SeparatorToolItem());

		if(pp.getAbonament() == null){
			btnAbonament = new TextButton("Creeaza abonament");
			btnAbonament.addSelectHandler(new SelectHandler() {

				@Override
				public void onSelect(SelectEvent event) {
					TextButton tb = (TextButton) event.getSource();
					
						con.add(new CreateAbonamentFormView(requestFactory, con, pp, tb));
						tb.setText("Abonament");
					}
			});
		}else{
//			btnAbonament = btnAbn;
			final ListStore<AbonamentProxy> store = new ListStore<AbonamentProxy>(AbonamentProxyAccess.serieKey());
			store.add(pp.getAbonament());
			
			final Chart<AbonamentProxy> chart = panel.createGauge(store, 80, new RGB("#3AA8CB"), false, new ElasticIn(), AbonamentProxyAccess.kilometri());
			chart.setBackground(null);
			chart.hide();
			
//			ContentPanel panel = new FramedPanel();
//		    panel.getElement().getStyle().setMargin(10, Unit.PX);
//		    panel.setCollapsible(true);
//		    panel.setHeadingText("Gauge Chart");
//		    panel.setPixelSize(420, 700);
//		    panel.setBodyBorder(true);
		 
//		    VerticalLayoutContainer layout = new VerticalLayoutContainer();
//		    con.add(layout);
		    
//		    chart.setLayoutData(new VerticalLayoutData(400, 200));
		    panel.getVerticalLayoutContainer().add(chart);
			
			final TextButton test = new TextButton("Test ToolTip");
		    final SeriesToolTipConfig<AbonamentProxy> tt = new SeriesToolTipConfig<AbonamentProxy>();

		    tt.setLabelProvider(null);
		    tt.setMouseOffset(new int[] {0, 0});
		    tt.setAnchor(Side.LEFT);
		    tt.setMinWidth(515);
		    
		    test.setToolTipConfig(tt);
		    test.getToolTip().addShowHandler(new ShowHandler() {
				
				@Override
				public void onShow(ShowEvent event) {
					store.clear();
					store.add(pp.getAbonament());
					chart.redrawChartForced();
				    tt.setBodyHtml(chart.getElement().getInnerHTML());
				    test.setToolTipConfig(tt);
				}
			});
//		    layout.add(test);
			btnAbonament = new TextButton("Abonament");
			btnAbonament.setToolTipConfig(tt);
			btnAbonament.getToolTip().addShowHandler(new ShowHandler() {
				
				@Override
				public void onShow(ShowEvent event) {
					store.clear();
					store.add(pp.getAbonament());
					chart.redrawChartForced();
				    tt.setBodyHtml(chart.getElement().getInnerHTML());
				    test.setToolTipConfig(tt);
				}
			});
			
		}
		
		panel.getToolbarSus().add(btnAbonament);

		return panel;
	}

	class SamplePanel extends ContentPanel implements SelectHandler{

		private ToolBar toolBar = new ToolBar();
		private ToolBar toolBarJos = new ToolBar();
		private VerticalLayoutContainer vlc = new VerticalLayoutContainer();

		public SamplePanel() {
			setPixelSize(650, 350);
			toolBar.setSpacing(2);
			toolBarJos.setSpacing(2);


			toolBarJos.add(new TextButton("Modifica ora", this));
			toolBarJos.add(new TextButton("Anuleaza rezervarea", this));
			toolBarJos.add(btnInchide = new TextButton("Inapoi", this));
			con.add(toolBar,  new VerticalLayoutData(1, -1));
			con.add(vlc,  new VerticalLayoutData(1, -1));

			add(con);
		}

		public ToolBar getToolbarSus() {
			return toolBar;
		}

		public ToolBar getToolbarJos(){
			return toolBarJos;
		}
		
		public VerticalLayoutContainer getVerticalLayoutContainer(){
			return vlc;
		}
		
		public Chart<AbonamentProxy> createGauge(ListStore<AbonamentProxy> store, double donut, Color color, boolean needle,
				EasingFunction easing, ValueProvider<AbonamentProxy, Integer> provider) {
			Chart<AbonamentProxy> chart = new Chart<AbonamentProxy>();
			chart.setStore(store);
			chart.setAnimationDuration(750);
			chart.setAnimationEasing(easing);
			chart.setDefaultInsets(20);

			GaugeAxis<AbonamentProxy> axis = new GaugeAxis<AbonamentProxy>();
			axis.setMargin(8);
			axis.setDisplayGrid(true);
			axis.setMinimum(0);
			axis.setMaximum(1500);
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
						htmlString = "<table align='center' cellpadding='1' cellspacing='1' style='border-width:1px; border-style:solid; border-color:#999; width: 598px;'>"+
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
					}else htmlString = "<table align='center' cellpadding='1' cellspacing='1' style='border-width:1px; border-style:solid; border-color:#999; width: 598px;'>"+
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
					con.add(text, new VerticalLayoutData(1, 1));
					con.add(getToolbarJos(), new VerticalLayoutData(1, -1));
				}

				@Override
				public void onFailure(ServerFailure sf){
					Window.alert("Rezervarea cu codul "+ tf.getValue() +" nu a fost gasita");
				}
			});
		}

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
				con.remove(text);
				con.remove(toolBarJos);
				tf.clear();
			}
		}
	}
}
