package ro.alex.browser.ui;

import java.util.List;

import ro.alex.browser.events.ClearCenterEvent;
import ro.alex.browser.events.CreateDusIntorsFormEvent;
import ro.alex.browser.events.DusEvent;
import ro.alex.browser.events.DusIntorsEvent;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.OrasProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class RezervareMenuWidget implements IsWidget {

	private static final int COLUMN_FORM_WIDTH = 130;
	private TextField orasPlecare, orasDestinatie;
	private DateField dataPlecare, dataSosire;
	private FieldLabel plecareFl, destinatieFl;
	private ContentPanel fp;
	private final ApplicationRequestFactory requestFactory;
	private final EventBus eventBus;


	interface OrasProperties extends PropertyAccess<OrasProxy> {
		ModelKeyProvider<OrasProxy> id();
		
		LabelProvider<OrasProxy> denumire();
	}
	
	@Inject
	public RezervareMenuWidget(ApplicationRequestFactory requestFactory, EventBus eventBus){
		this.requestFactory = requestFactory;
		this.eventBus = eventBus;
	}

	private ComboBox<OrasProxy> createCombo(){
		 OrasProperties props = GWT.create(OrasProperties.class);
	      final ListStore<OrasProxy> oras = new ListStore<OrasProxy>(props.id());
//	      states.addAll(TestData.getStates());
	      requestFactory.orasRequest().findAllOrases().fire(new Receiver<List<OrasProxy>>() {

			@Override
			public void onSuccess(List<OrasProxy> response) {
				oras.addAll(response);
			}
		});
	      
	      ComboBox<OrasProxy> combo = new ComboBox<OrasProxy>(oras, props.denumire());
	      combo.setEmptyText("Alegeti un oras");
	      combo.setWidth(COLUMN_FORM_WIDTH-2);
	      combo.setTriggerAction(TriggerAction.ALL);
	      
	      return combo;
	}
	
	private native String getTableMarkup() /*-{
	  return [ '<table width=100% cellpadding=0 cellspacing=0>',
	  	  '<tr><td class=lp></td><td></td></tr>',
	      '<tr><td class=op></td><td></td></tr>',
	      '<tr><td class=ld></td><td></td></tr>',
	      '<tr><td class=od></td><td></td></tr>',
	      '<tr><td class=ldp></td><td></td></tr>',
	      '<tr><td class=dp></td><td></td></tr>',
	      '<tr><td class=lds></td><td></td></tr>',
	      '<tr><td class=ds></td><td></td></tr>',
	      '<tr><td class=es></td><td></td></tr>', 
	      '<tr><td class=btn width=20%></td><td width=80%></td></tr>','</table>'

	  ].join("");
	}-*/;

	@Override
	public Widget asWidget() {
//		VerticalPanel vp = new VerticalPanel();
//		
//		vp.add(tabbedPanel());
		
		return tabbedPanel();
	}
	
	private ContentPanel dusIntorsContent(){
		fp = new ContentPanel();
		fp.setHeadingText("Detalii zbor");
		fp.setHeaderVisible(false);
		fp.setWidth(COLUMN_FORM_WIDTH);
		fp.getElement().getStyle().setOverflowY(Overflow.AUTO);
//		fp.getElement().getStyle().setBackgroundColor("white");
		fp.getBody().getStyle().setBackgroundColor("white");
		fp.getBody().getStyle().setBorderColor("white");
		fp.setBorders(false);
		fp.getElement().getStyle().setMargin(5, Unit.PX);
		fp.getElement().getStyle().setBorderStyle(BorderStyle.HIDDEN);

		HtmlLayoutContainer con = new HtmlLayoutContainer(getTableMarkup());
		fp.setWidget(con);

		int cw = COLUMN_FORM_WIDTH - 2;

		Label label = new Label("Oras plecare:");
		con.add(label, new HtmlData(".lp"));
		
		final ComboBox<OrasProxy> cPlecare = createCombo();
		con.add(cPlecare, new HtmlData(".op"));

		label = new Label("Oras destinatie:");
		con.add(label, new HtmlData(".ld"));

		final ComboBox<OrasProxy> cDestinatie = createCombo();
		con.add(cDestinatie, new HtmlData(".od"));

		label = new Label("Data plecare:");
		con.add(label, new HtmlData(".ldp"));
		final DateField dataPlecare = new DateField();
		dataPlecare.setWidth(cw);
		dataPlecare.setEnabled(true);
		dataPlecare.setPropertyEditor(new DateTimePropertyEditor("dd-MM-yyyy"));
		//	    dataPlecare.
		con.add(dataPlecare, new HtmlData(".dp"));

		label = new Label("Data sosire:");
		con.add(label, new HtmlData(".lds"));
		final DateField dataSosire = new DateField();
		dataSosire.setWidth(cw);
		dataSosire.setEnabled(true);
		dataSosire.setPropertyEditor(new DateTimePropertyEditor("dd-MM-yyyy"));
		//data sosire
		con.add(dataSosire, new HtmlData(".ds"));
		
		final CheckBox cb = new CheckBox();
		con.add(new FieldLabel(cb, "Escala"), new HtmlData(".es"));
		
		TextButton btn = new TextButton("Cauta");
		btn.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				//incarca grid cu zborurile filtrate dupa datele introduse
				eventBus.fireEvent(new ClearCenterEvent());
				DusIntorsEvent de = new DusIntorsEvent();
				de.setOrasp(cPlecare.getValue().getId().intValue());
				de.setOrasd(cDestinatie.getValue().getId().intValue());
				de.setOrasdi(cPlecare.getValue().getId().intValue());
				de.setOraspi(cDestinatie.getValue().getId().intValue());
				de.setDatap(new DateTimePropertyEditor("yyyy-MM-dd").render(dataPlecare.getValue()));
				de.setDatai(new DateTimePropertyEditor("yyyy-MM-dd").render(dataSosire.getValue()));
				de.setEscala(cb.getValue());
				eventBus.fireEvent(de);
			}
		});
		con.add(btn, new HtmlData(".btn"));
		return fp;
	}
	
	private ContentPanel dusContent(){
		fp = new ContentPanel();
		fp.setHeadingText("Detalii zbor");
		fp.setHeaderVisible(false);
		fp.setWidth(COLUMN_FORM_WIDTH);
		fp.getElement().getStyle().setOverflowY(Overflow.AUTO);
//		fp.getElement().getStyle().setBackgroundColor("white");
		fp.getBody().getStyle().setBackgroundColor("white");
		fp.getBody().getStyle().setBorderColor("white");
		fp.setBorders(false);
		fp.getElement().getStyle().setMargin(5, Unit.PX);
		fp.getElement().getStyle().setBorderStyle(BorderStyle.HIDDEN);

		HtmlLayoutContainer con = new HtmlLayoutContainer(getTableMarkup());
		fp.setWidget(con);

		int cw = COLUMN_FORM_WIDTH - 2;

		Label label = new Label("Oras plecare:");
		con.add(label, new HtmlData(".lp"));
		
		final ComboBox<OrasProxy> cPlecare = createCombo();
		con.add(cPlecare, new HtmlData(".op"));

		label = new Label("Oras destinatie:");
		con.add(label, new HtmlData(".ld"));

		final ComboBox<OrasProxy> cDestinatie = createCombo();
		con.add(cDestinatie, new HtmlData(".od"));

		label = new Label("Data plecare:");
		con.add(label, new HtmlData(".ldp"));
		final DateField dataPlecare = new DateField();
		dataPlecare.setWidth(cw);
		dataPlecare.setEnabled(true);
		dataPlecare.setPropertyEditor(new DateTimePropertyEditor("dd-MM-yyyy"));
//		dataPlecare.setPropertyEditor(new DateTimePropertyEditor("yyyy-MM-dd"));
		//	    dataPlecare.
		con.add(dataPlecare, new HtmlData(".dp"));

		final CheckBox cb = new CheckBox();
		con.add(new FieldLabel(cb, "Escala"), new HtmlData(".es"));
		
		TextButton btn = new TextButton("Cauta");
		btn.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				//incarca grid cu zborurile filtrate dupa datele introduse
				eventBus.fireEvent(new ClearCenterEvent());
				DusEvent de = new DusEvent();
				de.setOrasp(cPlecare.getValue().getId().intValue());
				de.setOrasd(cDestinatie.getValue().getId().intValue());
				
				de.setDatap(new DateTimePropertyEditor("yyyy-MM-dd").render(dataPlecare.getValue()));
//				de.setDatap(dataPlecare.getPropertyEditor().getFormat());
				de.setEscala(cb.getValue());
				eventBus.fireEvent(de);
			}
		});
		con.add(btn, new HtmlData(".btn"));
		return fp;
	}
	
	private PlainTabPanel tabbedPanel(){
		SelectionHandler<Widget> handler = new SelectionHandler<Widget>() {
		      @Override
		      public void onSelection(SelectionEvent<Widget> event) {
		        TabPanel panel = (TabPanel) event.getSource();
		        Widget w = event.getSelectedItem();
		        TabItemConfig config = panel.getConfig(w);
		        Info.display("Message", "'" + config.getText() + "' Selected");
		      }
		    };
		
		final PlainTabPanel panel = new PlainTabPanel();
	    panel.setPixelSize(450, 250);
	    panel.addSelectionHandler(handler);
	    panel.setBorders(false);
	 
	    Label normal = new Label("Dus");
	    normal.addStyleName("pad-text");
	    panel.add(dusContent(), "Dus");
	 
	    panel.add(dusIntorsContent(), "Dus-Intors");
	  		
		return panel;
	}
}
