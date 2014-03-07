package ro.alex.browser.ui.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ro.alex.browser.ZborProxyProperties;
import ro.alex.browser.events.ClearCenterEvent;
import ro.alex.browser.ui.ClasaProperties;
import ro.alex.browser.ui.window.DisplayTransactionInfo;
import ro.alex.browser.ui.window.TransactionInfo;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.proxy.LocProxy;
import ro.alex.client.proxy.ZborProxy;
import ro.alex.shared.scaffold.ScaffoldRequestFactory.ClasaPagingLoadRequest;
import ro.alex.shared.scaffold.ScaffoldRequestFactory.LocScaffoldRequest;
import ro.alex.shared.scaffold.ScaffoldRequestFactory.ZborPagingLoadRequest;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.data.shared.loader.RequestFactoryProxy;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.FormPanelHelper;
import com.sencha.gxt.widget.core.client.form.HtmlEditor;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

public class RezervareDusIntorsView implements IsWidget, SelectHandler {

	private ApplicationRequestFactory requestFactory;
	private EventBus eventBus;
	private TextField aeroportPlecareD, aeroportDestinatieD, aeroportPlecareDI, aeroportDestinatieDI, nume, prenume;
	private HorizontalPanel vp;
	private DateField dataPlecare, dataIntoarcere;
	private Label pretLbl;
	private ZborProxy zpDus;
	private ZborProxy zpIntors;
	private ComboBox<ClasaProxy> combo;
	private TransactionInfo ti;

	private FieldLabel plecareFl, destinatieFl;
	private String codGenerat;

	private Double pretCalculat;
	private HideHandler hideHandler;
	private boolean isAbonament = false, isDusIntors, logged;
	private Integer nrKm = 0;

	private ContentPanel panel;
	private static final int COLUMN_FORM_WIDTH = 680;

	private int orasp, orasd, oraspi, orasdi;
	private String datap, datai;
	private boolean escala;

	private VerticalLayoutContainer con = new VerticalLayoutContainer();
	protected TextButton salveazaBtn, anuleazaBtn;
	private DisplayTransactionInfo dti;


	public boolean isLogged() {
		return logged;
	}



	public void setLogged(boolean logged) {
		this.escala = logged;
	}

	public int getOraspi() {
		return oraspi;
	}



	public void setOraspi(int oraspi) {
		this.oraspi = oraspi;
	}



	public int getOrasdi() {
		return orasdi;
	}



	public void setOrasdi(int orasdi) {
		this.orasdi = orasdi;
	}



	public int getOrasp() {
		return orasp;
	}



	public void setOrasp(int orasp) {
		this.orasp = orasp;
	}



	public int getOrasd() {
		return orasd;
	}



	public void setOrasd(int orasd) {
		this.orasd = orasd;
	}



	public String getDatap() {
		return datap;
	}



	public void setDatap(String datap) {
		this.datap = datap;
	}



	public String getDatai() {
		return datai;
	}



	public void setDatai(String datai) {
		this.datai = datai;
	}



	public boolean isEscala() {
		return escala;
	}



	public void setEscala(boolean escala) {
		this.escala = escala;
	}


	public boolean isDusIntors() {
		return isDusIntors;
	}



	public void setDusIntors(boolean isDusIntors) {
		this.isDusIntors = isDusIntors;
	}

	@Inject
	public RezervareDusIntorsView(ApplicationRequestFactory requestFactory, EventBus eventBus, DisplayTransactionInfo dti){
		this.requestFactory = requestFactory;
		this.eventBus = eventBus;
		this.dti = dti;
	}

	@Override
	public Widget asWidget() {
		ContentPanel cp = new ContentPanel();

		con.setScrollMode(ScrollMode.AUTO);
		cp.setPixelSize(1412, 750);

		con.setPixelSize( cp.getOffsetWidth() , cp.getOffsetHeight() );

		HorizontalLayoutContainer hlc = new HorizontalLayoutContainer();
		VerticalPanel vp = new VerticalPanel();
		vp.add(dusGrid(getOrasp(), getOrasd(), getDatap()));
		if( isDusIntors() ) vp.add(intorsGrid( getOraspi(), getOrasdi(), getDatai() ));

		hlc.add(vp);
		if( !isDusIntors() ) hlc.add(formDus());
		else hlc.add(formDusIntors());
		hlc.add(panel);
		con.add(hlc);

		cp.add(con);

		return cp;
	}

	private Widget dusGrid(final int orasp, final int orasd, final String data){
		RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>> proxy = new RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>>() {
			@Override
			public void load(FilterPagingLoadConfig loadConfig, Receiver<? super PagingLoadResult<ZborProxy>> receiver) {
				ZborPagingLoadRequest req = requestFactory.zborPagingRequest();
				List<SortInfo> sortInfo = createRequestSortInfo(req, loadConfig.getSortInfo());

				req.sortFlights(loadConfig.getOffset(), loadConfig.getLimit(), sortInfo, orasp, orasd, data).with("data.orasd").with("data.orasp").with("data.avion").fire(receiver);
			}
		};

		final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>>(proxy) {
			@Override
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(true);

		ZborProxyProperties props = GWT.create(ZborProxyProperties.class);

		ListStore<ZborProxy> store = new ListStore<ZborProxy>(props.id());
		loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, ZborProxy, PagingLoadResult<ZborProxy>>(
				store));

		final PagingToolBar toolBar = new PagingToolBar(50);
		toolBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolBar.bind(loader);

		ColumnConfig<ZborProxy, String> codCfg = new ColumnConfig<ZborProxy, String>(props.cod(), 50, "Cod zbor");
		ColumnConfig<ZborProxy, String> oraspCfg = new ColumnConfig<ZborProxy, String>(props.orasp(), 50, "Oras plecare");
		ColumnConfig<ZborProxy, String> orasdCfg = new ColumnConfig<ZborProxy, String>(props.orasd(), 50, "Oras destinatie");
		ColumnConfig<ZborProxy, Integer> kmCfg = new ColumnConfig<ZborProxy, Integer>(props.numarKm(), 50, "Numar km");
		ColumnConfig<ZborProxy, Date> dataCfg = new ColumnConfig<ZborProxy, Date>(props.data(), 50, "Data plecare");
		ColumnConfig<ZborProxy, String> avionCfg = new ColumnConfig<ZborProxy, String>(props.avion(), 50, "Tip avion");
		avionCfg.setHidden(true);
		DateTimeFormat format = DateTimeFormat.getFormat("dd-MM-yyyy 'la ora' hh:mm aaa");
		dataCfg.setCell(new DateCell(format));

		List<ColumnConfig<ZborProxy, ?>> l = new ArrayList<ColumnConfig<ZborProxy, ?>>();
		l.add(codCfg);
		l.add(oraspCfg);
		l.add(orasdCfg);
		l.add(dataCfg);
		l.add(kmCfg);
		l.add(avionCfg);

		ColumnModel<ZborProxy> cm = new ColumnModel<ZborProxy>(l);

		final Grid<ZborProxy> view = new Grid<ZborProxy>(store, cm) {
			@Override
			protected void onAfterFirstAttach() {
				super.onAfterFirstAttach();
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						loader.load();
					}
				});
			}
		};
		view.getView().setForceFit(true);
		view.setLoadMask(true);
		view.setLoader(loader);
		view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		view.setWidth(COLUMN_FORM_WIDTH);
		view.setHeight(300);

		//leaga grid de form
		view.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<ZborProxy>() {

			@Override
			public void onSelectionChanged(SelectionChangedEvent<ZborProxy> event) {
				zpDus = event.getSource().getSelectedItem();
				aeroportPlecareD.setText(zpDus.getOrasp().getDenumire());
				aeroportDestinatieD.setText(zpDus.getOrasd().getDenumire());
				dataPlecare.setPropertyEditor(new DateTimePropertyEditor("dd-MM-yyyy 'la ora' hh:mm aaa"));
				dataPlecare.setValue(zpDus.getData());
			}
		});
		FramedPanel panel = new FramedPanel();
		panel.setHeaderVisible(false);
		panel.setWidth(COLUMN_FORM_WIDTH);
		panel.getElement().getStyle().setOverflowY(Overflow.AUTO);
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		panel.setWidget(con);

		con.add(new Label("Lista zborurilor deschise la dus"), new VerticalLayoutData(1, -1));
		con.add(view, new VerticalLayoutData(1, 1));

		return panel;
	}

	private Widget intorsGrid(final int orasp, final int orasd, final String data){
		RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>> proxy = new RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>>() {
			@Override
			public void load(FilterPagingLoadConfig loadConfig, Receiver<? super PagingLoadResult<ZborProxy>> receiver) {
				ZborPagingLoadRequest req = requestFactory.zborPagingRequest();
				List<SortInfo> sortInfo = createRequestSortInfo(req, loadConfig.getSortInfo());

				req.sortFlights(loadConfig.getOffset(), loadConfig.getLimit(), sortInfo, orasp, orasd, data).with("data.orasd").with("data.orasp").with("data.avion").fire(receiver);
			}
		};

		final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>>(proxy) {
			@Override
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(true);

		ZborProxyProperties props = GWT.create(ZborProxyProperties.class);

		ListStore<ZborProxy> store = new ListStore<ZborProxy>(props.id());
		loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, ZborProxy, PagingLoadResult<ZborProxy>>(
				store));

		final PagingToolBar toolBar = new PagingToolBar(50);
		toolBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolBar.bind(loader);

		ColumnConfig<ZborProxy, String> codCfg = new ColumnConfig<ZborProxy, String>(props.cod(), 50, "Cod zbor");
		ColumnConfig<ZborProxy, String> oraspCfg = new ColumnConfig<ZborProxy, String>(props.orasp(), 50, "Oras plecare");
		ColumnConfig<ZborProxy, String> orasdCfg = new ColumnConfig<ZborProxy, String>(props.orasd(), 50, "Oras destinatie");
		ColumnConfig<ZborProxy, Integer> kmCfg = new ColumnConfig<ZborProxy, Integer>(props.numarKm(), 50, "Numar km");
		ColumnConfig<ZborProxy, Date> dataCfg = new ColumnConfig<ZborProxy, Date>(props.data(), 50, "Data plecare");
		ColumnConfig<ZborProxy, String> avionCfg = new ColumnConfig<ZborProxy, String>(props.avion(), 50, "Tip avion") ;
		avionCfg.setHidden(true);
		
		DateTimeFormat format = DateTimeFormat.getFormat("dd-MM-yyyy 'la ora' hh:mm aaa");
		dataCfg.setCell(new DateCell(format));
		
		List<ColumnConfig<ZborProxy, ?>> l = new ArrayList<ColumnConfig<ZborProxy, ?>>();
		l.add(codCfg);
		l.add(oraspCfg);
		l.add(orasdCfg);
		l.add(dataCfg);
		l.add(kmCfg);
		l.add(avionCfg);

		ColumnModel<ZborProxy> cm = new ColumnModel<ZborProxy>(l);

		final Grid<ZborProxy> view = new Grid<ZborProxy>(store, cm) {
			@Override
			protected void onAfterFirstAttach() {
				super.onAfterFirstAttach();
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						loader.load();
					}
				});
			}
		};
		view.getView().setForceFit(true);
		view.setLoadMask(true);
		view.setLoader(loader);
		view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		view.setWidth(COLUMN_FORM_WIDTH);
		view.setHeight(300);

		//bind grid vs form
		view.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<ZborProxy>() {

			@Override
			public void onSelectionChanged(SelectionChangedEvent<ZborProxy> event) {
				zpIntors = event.getSource().getSelectedItem();
				aeroportPlecareDI.setText(zpIntors.getOrasp().getDenumire());
				aeroportDestinatieDI.setText(zpIntors.getOrasd().getDenumire());
				dataIntoarcere.setPropertyEditor(new DateTimePropertyEditor("dd-MM-yyyy 'la ora' hh:mm aaa"));
				dataIntoarcere.setValue(zpIntors.getData());
			}
		});
		FramedPanel panel = new FramedPanel();
		panel.setHeaderVisible(false);
		panel.setWidth(COLUMN_FORM_WIDTH);
		panel.getElement().getStyle().setOverflowY(Overflow.AUTO);
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		panel.setWidget(con);

		con.add(new Label("Lista zborurilor deschise la intors"), new VerticalLayoutData(1, -1));
		con.add(view, new VerticalLayoutData(1, 1));

		return panel;
	}



	private ComboBox<ClasaProxy> createClasaCombo(){
		ClasaProperties props = GWT.create(ClasaProperties.class);
		final ListStore<ClasaProxy> clase = new ListStore<ClasaProxy>(props.id());
		ComboBox<ClasaProxy> combo = new ComboBox<ClasaProxy>(clase, props.clasa());

		//		addHandlersForEventObservation(combo, props.clasa());



		combo.setEmptyText("Selectati o clasa");
		combo.setWidth(150);
		combo.setTypeAhead(true);
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setEditable(false);
		combo.setExpanded(false);

		RequestFactoryProxy<PagingLoadConfig, PagingLoadResult<ClasaProxy>> proxy = new RequestFactoryProxy<PagingLoadConfig, PagingLoadResult<ClasaProxy>>() {
			@Override
			public void load(PagingLoadConfig loadConfig, Receiver<? super PagingLoadResult<ClasaProxy>> receiver) {
				ClasaPagingLoadRequest reqClasa = requestFactory.clasaPagingRequest();

				//				List<SortInfo> sortInfo = createRequestSortInfo(reqClasa, loadConfig.getSortInfo());
				reqClasa.findAllClasePaginat(loadConfig.getOffset(), loadConfig.getLimit()).with("clasa").to(receiver).fire();
			}
		};

		final PagingLoader<PagingLoadConfig, PagingLoadResult<ClasaProxy>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<ClasaProxy>>(proxy);
		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, ClasaProxy, PagingLoadResult<ClasaProxy>>(clase));
		loader.setRemoteSort(true);

		//		combo.setPageSize(3);
		combo.setLoader(loader);
		combo.setTriggerAction(ComboBoxCell.TriggerAction.QUERY);
		combo.setForceSelection(true);

		//handler for combo
		addHandlersForEventObservation(combo, props.clasa());

		return combo;
	}


	protected native String getTableMarkup() /*-{
  return [ '<table width=100% cellpadding=0 cellspacing=0>',
      '<tr><td class=ap width=50%></td><td class=ad width=50%></td></tr>',
      '<tr><td class=nume></td><td class=prenume></td></tr>',
      '<tr><td class=dp></td><td class=cod></td></tr>',
      '<tr><td class=plata></td> <tr><td class=combo></td></tr>',
      '<tr><td class=editor colspan=2></td></tr>', '</table>'

  ].join("");
}-*/;

	protected native String getTableMarkup1() /*-{
	  return [ '<table width=100% cellpadding=0 cellspacing=0>',
	      '<tr><td class=ap width=50%></td><td class=ad width=50%></td></tr>',
	       '<tr><td class=dp></td></tr>', '</table>'

	  ].join("");
	}-*/;

	protected native String getTableMarkup2() /*-{
	  return [ '<table width=100% cellpadding=0 cellspacing=0>',
	      '<tr><td class=api width=50%></td><td class=adi width=50%></td></tr>',
	       '<tr><td class=dpi></td></tr>', '</table>'

	  ].join("");
	}-*/;

	protected native String getTableMarkup3() /*-{
	  return [ '<table width=100% cellpadding=0 cellspacing=0>',
	      '<tr><td class=nume width=50%></td><td class=prenume width=50%></td></tr>',
	      '<tr><td class=plata></td><td class=combo></td></tr>',
	      '<tr><td class=cod></td></tr>',
	      '<tr><td class=editor colspan=2></td></tr>', '</table>'

	  ].join("");
	}-*/;

	//implement handlers
	private <T> void addHandlersForEventObservation(ComboBox<T> combo, final LabelProvider<T> labelProvider) {
		combo.addValueChangeHandler(new ValueChangeHandler<T>() {
			@Override
			public void onValueChange(ValueChangeEvent<T> event) {
				Info.display("Valoare schimbata", "Valoare noua: "
						+ (event.getValue() == null ? "nimic" : labelProvider.getLabel(event.getValue()) + "!"));
				//				rf.rezervareRequest().calculPret(labelProvider.getLabel(event.getValue())).fire(new Receiver<BigDecimal>() {
				//
				//					@Override
				//					public void onSuccess(BigDecimal response) {
				//						codLbl.setText(response.toString());
				//					}
				//				});
				//				Window.alert(labelProvider.getLabel(event.getValue()));
			}
		});
		combo.addSelectionHandler(new SelectionHandler<T>() {
			@Override
			public void onSelection(SelectionEvent<T> event) {
				Info.display("State Selected", "You selected "
						+ (event.getSelectedItem() == null ? "nothing" : labelProvider.getLabel(event.getSelectedItem()) + "!"));
				//				clasa = labelProvider.getLabel(event.getSelectedItem());
				requestFactory.rezervareScaffoldRequest().calculPret(labelProvider.getLabel(event.getSelectedItem()).toString()).fire(new Receiver<Double>() {

					@Override
					public void onSuccess(Double response) {
						pretLbl.setText(response.toString());
						pretCalculat = response;
					}
				});
				Window.alert(labelProvider.getLabel(event.getSelectedItem()));
			}
		});
	}

	protected void addGroupRadioHandler(ToggleGroup toggle){
		toggle.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {

			@Override
			public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
				ToggleGroup group = (ToggleGroup)event.getSource();
				Radio radio = (Radio)group.getValue();
				Info.display("Mod plata selectat", "Ati ales: " + radio.getBoxLabel());
				if("Abonament".equals(radio.getBoxLabel())) isAbonament = true;
				else isAbonament = false;
			}
		});
	}

	private Widget formDusIntors(){
		panel = new FramedPanel();
		VerticalLayoutContainer vpanel = new VerticalLayoutContainer();
		panel.setHeadingText("Rezerva un zbor");
		panel.setHeaderVisible(false);
		vpanel.setWidth(COLUMN_FORM_WIDTH);
		vpanel.getElement().getStyle().setOverflowY(Overflow.AUTO);

		FramedPanel cp1 = new FramedPanel();
		FramedPanel cp2 = new FramedPanel();
		FramedPanel cp3 = new FramedPanel();
		FieldSet fieldSet1 = new FieldSet();
		fieldSet1.setHeadingText("Informatii zbor dus");
		fieldSet1.setCollapsible(true);
		cp1.add(fieldSet1);
		vpanel.add(cp1);

		FieldSet fieldSet2 = new FieldSet();
		fieldSet2.setHeadingText("Informatii zbor intors");
		fieldSet2.setCollapsible(true);
		cp2.add(fieldSet2);
		vpanel.add(cp2);

		FieldSet fieldSet3 = new FieldSet();
		fieldSet3.setHeadingText("Informatii aditionale");
		fieldSet3.setCollapsible(true);
		cp3.add(fieldSet3);
		vpanel.add(cp3);

		HtmlLayoutContainer con1 = new HtmlLayoutContainer(getTableMarkup1());
		//		panel.setWidget(con);
		fieldSet1.add(con1);

		int cw = (COLUMN_FORM_WIDTH / 2) - 12;

		aeroportPlecareD = new TextField();
		aeroportPlecareD.setAllowBlank(false);
		aeroportPlecareD.setWidth(cw);
		aeroportPlecareD.setEnabled(false);
		con1.add(plecareFl = new FieldLabel(aeroportPlecareD, "Aeroport Plecare"), new HtmlData(".ap"));

		aeroportDestinatieD = new TextField();
		aeroportDestinatieD.setAllowBlank(false);
		aeroportDestinatieD.setWidth(cw);
		aeroportDestinatieD.setEnabled(false);
		con1.add(destinatieFl = new FieldLabel(aeroportDestinatieD, "Aeroport Destinatie"), new HtmlData(".ad"));

		dataPlecare = new DateField();
		dataPlecare.setWidth(cw);
		dataPlecare.setEnabled(false);
		//	    dataPlecare.
		con1.add(new FieldLabel(dataPlecare, "Data plecare"), new HtmlData(".dp"));
		/* end of panel1 */

		/*start panel2 */
		HtmlLayoutContainer con2 = new HtmlLayoutContainer(getTableMarkup2());
		fieldSet2.add(con2);

		aeroportPlecareDI = new TextField();
		aeroportPlecareDI.setAllowBlank(false);
		aeroportPlecareDI.setWidth(cw);
		aeroportPlecareDI.setEnabled(false);
		con2.add(plecareFl = new FieldLabel(aeroportPlecareDI, "Aeroport Plecare"), new HtmlData(".api"));

		aeroportDestinatieDI = new TextField();
		aeroportDestinatieDI.setAllowBlank(false);
		aeroportDestinatieDI.setWidth(cw);
		aeroportDestinatieDI.setEnabled(false);
		con2.add(destinatieFl = new FieldLabel(aeroportDestinatieDI, "Aeroport Destinatie"), new HtmlData(".adi"));

		dataIntoarcere = new DateField();
		dataIntoarcere.setWidth(cw);
		dataIntoarcere.setEnabled(false);
		//	    dataPlecare.
		con2.add(new FieldLabel(dataIntoarcere, "Data intoarcere"), new HtmlData(".dpi"));
		/* end panel2 */

		/*start panel3*/
		HtmlLayoutContainer con3 = new HtmlLayoutContainer(getTableMarkup3());
		fieldSet3.add(con3);
		nume = new TextField();
		nume.setWidth(cw);
		con3.add(new FieldLabel(nume, "Nume"), new HtmlData(".nume"));

		prenume = new TextField();
		prenume.setWidth(cw);
		con3.add(new FieldLabel(prenume, "Prenume"), new HtmlData(".prenume"));



		//modalitate plata
		Radio radio1 = new Radio();
		radio1.setBoxLabel("Abonament");

		Radio radio2 = new Radio();
		radio2.setBoxLabel("Prin agentie");

		pretLbl = new Label("Selectati o categorie pentru a calcula pretul!");
		//		codLbl.setText(getCodGenerat());
		con3.add(new FieldLabel(pretLbl, "Pretul calculat"), new HtmlData(".cod"));

		HorizontalPanel hp = new HorizontalPanel();
		hp.add(radio1);
		hp.add(radio2);

		con3.add(new FieldLabel(hp, "Modalitate de plata"), new HtmlData(".plata"));

		ToggleGroup group = new ToggleGroup();
		group.add(radio1);
		group.add(radio2);
		addGroupRadioHandler(group);

		combo = createClasaCombo();
		con3.add(combo, new HtmlData(".combo"));

		HtmlEditor a = new HtmlEditor();
		a.setWidth(COLUMN_FORM_WIDTH - 25);
		a.setEnableAlignments(false);
		a.setEnableColors(false);
		a.setEnableFont(false);
		a.setEnableFontSize(false);
		a.setEnableLinks(false);
		a.setEnableLists(false);
		con3.add(new FieldLabel(a, "Observatii"), new HtmlData(".editor"));

		//		panel.addButton(new TextButton("Anuleaza", this));
		//		panel.addButton(new TextButton("Salveaza", this));

		// need to call after everything is constructed
		List<FieldLabel> labels = FormPanelHelper.getFieldLabels(vpanel);
		for (FieldLabel lbl : labels) {
			lbl.setLabelAlign(LabelAlign.TOP);
		}
		panel.add(vpanel);
		salveazaBtn = new TextButton("Salveaza", (SelectHandler)this);
		anuleazaBtn = new TextButton("Anuleaza", (SelectHandler)this);
		panel.addButton(salveazaBtn);
		panel.addButton(anuleazaBtn);
		return panel;
	}

	private Widget formDus(){
		panel = new FramedPanel();
		panel.setHeadingText("Rezerva un zbor");
		panel.setHeaderVisible(false);
		panel.setWidth(COLUMN_FORM_WIDTH);
		panel.getElement().getStyle().setOverflowY(Overflow.AUTO);

		HtmlLayoutContainer con = new HtmlLayoutContainer(getTableMarkup());
		panel.setWidget(con);

		int cw = (COLUMN_FORM_WIDTH / 2) - 12;

		aeroportPlecareD = new TextField();
		aeroportPlecareD.setAllowBlank(false);
		aeroportPlecareD.setWidth(cw);
		aeroportPlecareD.setEnabled(false);
		con.add(plecareFl = new FieldLabel(aeroportPlecareD, "Aeroport Plecare"), new HtmlData(".ap"));

		aeroportDestinatieD = new TextField();
		aeroportDestinatieD.setAllowBlank(false);
		aeroportDestinatieD.setWidth(cw);
		aeroportDestinatieD.setEnabled(false);
		con.add(destinatieFl = new FieldLabel(aeroportDestinatieD, "Aeroport Destinatie"), new HtmlData(".ad"));

		nume = new TextField();
		nume.setWidth(cw);
		con.add(new FieldLabel(nume, "Nume"), new HtmlData(".nume"));

		prenume = new TextField();
		prenume.setWidth(cw);
		con.add(new FieldLabel(prenume, "Prenume"), new HtmlData(".prenume"));

		dataPlecare = new DateField();
		dataPlecare.setWidth(cw);
		dataPlecare.setEnabled(false);
		//	    dataPlecare.
		con.add(new FieldLabel(dataPlecare, "Data plecare"), new HtmlData(".dp"));

		//modalitate plata
		Radio radio1 = new Radio();
		radio1.setBoxLabel("Abonament");

		Radio radio2 = new Radio();
		radio2.setBoxLabel("Prin agentie");

		pretLbl = new Label("Selectati o categorie pentru a calcula pretul!");
		//		codLbl.setText(getCodGenerat());
		con.add(new FieldLabel(pretLbl, "Pretul calculat"), new HtmlData(".cod"));

		HorizontalPanel hp = new HorizontalPanel();
		hp.add(radio1);
		hp.add(radio2);

		con.add(new FieldLabel(hp, "Modalitate de plata"), new HtmlData(".plata"));

		ToggleGroup group = new ToggleGroup();
		group.add(radio1);
		group.add(radio2);
		addGroupRadioHandler(group);

		combo = createClasaCombo();

		con.add(combo, new HtmlData(".combo"));

		HtmlEditor a = new HtmlEditor();
		a.setWidth(COLUMN_FORM_WIDTH - 25);
		a.setEnableAlignments(false);
		a.setEnableColors(false);
		a.setEnableFont(false);
		a.setEnableFontSize(false);
		a.setEnableLinks(false);
		a.setEnableLists(false);
		con.add(new FieldLabel(a, "Observatii"), new HtmlData(".editor"));

		anuleazaBtn = new TextButton("Anuleaza", this);
		panel.addButton(anuleazaBtn);
		salveazaBtn = new TextButton("Salveaza", this);

		panel.addButton(salveazaBtn);

		// need to call after everything is constructed
		List<FieldLabel> labels = FormPanelHelper.getFieldLabels(panel);
		for (FieldLabel lbl : labels) {
			lbl.setLabelAlign(LabelAlign.TOP);
		}
		return panel;
	}

	@Override
	public void onSelect(SelectEvent event) {
		TextButton tb = (TextButton) event.getSource();

		if (tb.getText() == "Salveaza") {
			Info.display("Click event", "Salveaza action");
			//			final LocProxy lp = getLocLiber(combo.getValue(), zpDus);
			//			Info.display("Alert", getLocLiber(combo.getValue(), zpDus).getNumar().toString());
			//			dti.getWindow().show();
			

			Info.display("Alert", "After Abonament");
			if(ti != null) ti = null;

			LocScaffoldRequest lsrDus = requestFactory.locScaffoldRequest();
			//aici e un null
			if (zpIntors != null) Window.alert(combo.getValue().getId() + " "+ zpIntors.getAvion().getTip() +" " + zpDus.getId());
			lsrDus.getLocLiber(combo.getValue().getId(), zpDus.getAvion().getId(),zpDus.getId()).fire(new Receiver<LocProxy>() {

				@Override
				public void onSuccess(final LocProxy locDus) {
					Info.display("Alert", locDus.getNumar().toString());
					LocScaffoldRequest lsrIntors = requestFactory.locScaffoldRequest();

					//daca este dus-intors mai cer un loc
					if(isDusIntors()) lsrIntors.getLocLiber(combo.getValue().getId(), zpIntors.getAvion().getId(), zpIntors.getId()).fire(new Receiver<LocProxy>() {

						@Override
						public void onSuccess(final LocProxy locIntors) {
							requestFactory.rezervareScaffoldRequest().stringGenerator().fire(new Receiver<String>() {

								@Override
								public void onSuccess(String cod) {
									Info.display("Alert", "A intrat in DUS-INTORS");
									if(!isAbonament) {
										ti = new TransactionInfo(requestFactory, eventBus, salveazaBtn, isDusIntors(), nume.getValue(), prenume.getValue(), cod, 
											zpDus, zpIntors, pretCalculat, combo.getValue().getClasa(), locDus, locIntors,"Numerar prin agentie", isEscala(), null);
										ti.getWindow().show();
									}
									else{
										final PromptMessageBox box = new PromptMessageBox("Abonament", "Introduceti seria abonamentului:");
										final String codGenerat = cod;
										box.addHideHandler(new HideHandler() {
											@Override
											public void onHide(HideEvent event) {
												
												requestFactory.abonamentScaffoldRequest().findAbonamentBySerie(box.getValue()).fire(new Receiver<AbonamentProxy>() {

													@Override
													public void onSuccess(AbonamentProxy abonament) {
														if(nrKm <= abonament.getKilometri()) {
															ti = new TransactionInfo(requestFactory, eventBus, salveazaBtn, isDusIntors(), nume.getValue(), prenume.getValue(), codGenerat, 
																zpDus, zpIntors, (double) 0.0, combo.getValue().getClasa(), locDus, locIntors, "Abonament", isEscala(), abonament);
															ti.getWindow().show();
														}
														else {
															Window.alert("Numar km insuficienti!");//+ promt daca doreste sa plateasca numerar
															return;
														}
													}
													
													@Override
													public void onFailure(ServerFailure e){
														AlertMessageBox d = new AlertMessageBox("Atentie", "Seria "+ box.getValue() +" nu a fost gasita");
												        d.addHideHandler(hideHandler);
												        d.show();
												        return;
													}
												});
											}
										});
										box.show();
										
									}
								}
							});

						}
						@Override
						public void onFailure(ServerFailure sf){
							Window.alert("Nu mai sunt locuri disponibile, va rugam incercati la alta clasa");
						}
					});
					else requestFactory.rezervareScaffoldRequest().stringGenerator().fire(new Receiver<String>() {

						@Override
						public void onSuccess(String cod) {
							Info.display("Alert", "A intrat in DUS");
							final String codGenerat = cod;
							if(!isAbonament) {
								ti = new TransactionInfo(requestFactory, eventBus, salveazaBtn, isDusIntors(), nume.getValue(), prenume.getValue(), cod, 
									zpDus, pretCalculat, combo.getValue().getClasa(), locDus,"Numerar prin agentie", isEscala(), null);
								ti.getWindow().show();
							}
							else {
								final PromptMessageBox box = new PromptMessageBox("Abonament", "Introduceti seria abonamentului:");
								box.addHideHandler(new HideHandler() {
									@Override
									public void onHide(HideEvent event) {
										
										requestFactory.abonamentScaffoldRequest().findAbonamentBySerie(box.getValue()).fire(new Receiver<AbonamentProxy>() {

											@Override
											public void onSuccess(AbonamentProxy abonament) {
												Info.display("Alert Abonament", abonament.getKilometri().toString());
												if(nrKm <= abonament.getKilometri()) {
													ti = new TransactionInfo(requestFactory, eventBus, salveazaBtn, isDusIntors(), nume.getValue(), prenume.getValue(), codGenerat, 
														zpDus, (double) 0.0, combo.getValue().getClasa(), locDus, "Abonament", isEscala(), abonament);
												ti.getWindow().show();
												}
												else {
													Window.alert("Numar km insuficienti!");//+ promt daca doreste sa plateasca numerar
													return;
												}
											}
											
											@Override
											public void onFailure(ServerFailure e){
												AlertMessageBox d = new AlertMessageBox("Atentie", "Seria "+ box.getValue() +" nu a fost gasita");
										        d.addHideHandler(hideHandler = new HideHandler() {
													
													@Override
													public void onHide(HideEvent event) {
														return;
													}
												});
										        d.show();
//										        return;
											}
										});
									}
								});
								box.show();
							}
							
//							if (ti == null) Window.alert("TI este null") ;
						}

					});
					
				}
				@Override
				public void onFailure(ServerFailure sf){
					Window.alert("Nu mai sunt locuri disponibile, va rugam incercati la alta clasa");
				}
			});

		}
		else if (tb.getText() == "Anuleaza") {
			Info.display("Click event", "Anuleaza action");
			eventBus.fireEvent(new ClearCenterEvent());
		}
	}


}
