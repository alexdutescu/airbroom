package ro.alex.browser.ui.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ro.alex.browser.ZborProxyProperties;
import ro.alex.browser.ui.ClasaProperties;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.proxy.ZborProxy;
import ro.alex.shared.scaffold.ScaffoldRequestFactory.ClasaPagingLoadRequest;
import ro.alex.shared.scaffold.ScaffoldRequestFactory.ZborPagingLoadRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.cell.core.client.form.TextInputCell;
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
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.FormPanelHelper;
import com.sencha.gxt.widget.core.client.form.HtmlEditor;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/*
 * -permite utilizatorului sa procure bilet in baza unui abonament(doar daca este autentificat si are abonament valid), 
 * sau cu plata la agentie(locul in avion fiind rezervat(ocupat) pentru 48 de ore sau pana la confirmarea platii devenind apoi ocupat/liber)
 * -Widgetul se creeaza in functie de contextul (logat/nelogat) 
 */
public class CreateRezervareView implements IsWidget, SelectHandler {


	private HorizontalPanel vp;
	private static final int COLUMN_FORM_WIDTH = 680;
	private FieldLabel plecareFl;
	private FieldLabel destinatieFl;
	private TextField aeroportPlecare;
	private TextField aeroportDestinatie;
	private DateField dataPlecare;
	private String codGenerat;
	private Label pretLbl;
	private BigDecimal pretCalculat;
	private TextField nume;
	private TextField prenume;
	private HideHandler hideHandler;
	private boolean isAbonament = false;
	private Integer nrKm = 0;
	private MainPanel mp;
	private final ApplicationRequestFactory rf;

	@Inject
	public CreateRezervareView(ApplicationRequestFactory rf){
		this.rf = rf;
	}

	@Override
	public Widget asWidget() {

		mp = new MainPanel();
		createFiltersTab();

		return mp;
	}

	@Override
	public void onSelect(SelectEvent event) {

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
				ClasaPagingLoadRequest reqClasa = rf.clasaPagingRequest();

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

	/*
	 * creeaza un menu tab cu 2 filtre(plecare si destinatie) + buton
	 */
	private void createFiltersTab(){
		Label lblPlecare = new Label("Oras plecare");
		mp.getToolBar().add(lblPlecare);

		TextField tfPlecare = new TextField(new TextInputCell());
		mp.getToolBar().add(tfPlecare);

		Label lblDestinatie = new Label("Oras destinatie");
		mp.getToolBar().add(lblDestinatie);

		TextField tf = new TextField(new TextInputCell());
		mp.getToolBar().add(tf);

		TextButton goBtn = new TextButton("Go");
		mp.getToolBar().add(goBtn);
	}

	/*
	 * incarca zborurile deschise intr-un grid
	 */
	private Widget createGrid(){

		RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>> proxy = new RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>>() {
			@Override
			public void load(FilterPagingLoadConfig loadConfig, Receiver<? super PagingLoadResult<ZborProxy>> receiver) {
				ZborPagingLoadRequest req = rf.zborPagingRequest();
				List<SortInfo> sortInfo = createRequestSortInfo(req, loadConfig.getSortInfo());
				//								List<FilterConfig> filterConfig = createRequestFilterConfig(req, loadConfig.getFilters());
				//				req.findAllZborsPaginat(loadConfig.getOffset(), loadConfig.getLimit(), sortInfo).to(receiver);
				//				req.findAll(loadConfig.getLimit(), loadConfig.getOffset()).with("categorie.nume").to(receiver).fire();
				//				req.fire();
//				req.findAllZborsPaginat(loadConfig.getOffset(), loadConfig.getLimit(), sortInfo).with("data.orasd").with("data.orasp").fire(receiver);
//				req.sortFlights(1, 1, "", "", loadConfig.getOffset(), loadConfig.getLimit(), sortInfo).with("data.orasd").with("data.orasp").fire(receiver);
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

		ColumnConfig<ZborProxy, String> cod = new ColumnConfig<ZborProxy, String>(props.cod(), 50, "Cod zbor");
		ColumnConfig<ZborProxy, String> orasp = new ColumnConfig<ZborProxy, String>(props.orasp(), 50, "Oras plecare");
		ColumnConfig<ZborProxy, String> orasd = new ColumnConfig<ZborProxy, String>(props.orasd(), 50, "Oras destinatie");
		ColumnConfig<ZborProxy, Integer> km = new ColumnConfig<ZborProxy, Integer>(props.numarKm(), 50, "Numar km");
		ColumnConfig<ZborProxy, Date> data = new ColumnConfig<ZborProxy, Date>(props.data(), 50, "Data plecare");
		//		ColumnConfig<ZborProxy, Long> numarLocuri = new ColumnConfig<ZborProxy, Long>(props.numarLocuri(), 50, "Numar locuri");
		//		ColumnConfig<ZborProxy, String> aeroportP = new ColumnConfig<ZborProxy, String>(props.aeroportP(), 150, "AeroportP");
		//		ColumnConfig<ZborProxy, String> plecare = new ColumnConfig<ZborProxy, String>(props.plecare(), 50, "Plecare");
		//		ColumnConfig<ZborProxy, String> destinatie = new ColumnConfig<ZborProxy, String>(props.destinatie(), 50, "Destinatie");
		//		ColumnConfig<ZborProxy, Boolean> escala = new ColumnConfig<ZborProxy, Boolean>(props.escala() ,50, "Escala");
		//		escala.setCell( new AbstractCell<Boolean>() {
		//
		//			@Override
		//			public void render(com.google.gwt.cell.client.Cell.Context context,	Boolean value, SafeHtmlBuilder sb) {
		//				if (value) {
		//					sb.append(SafeHtmlUtils.fromSafeConstant("Da"));
		//				} else {
		//					sb.append(SafeHtmlUtils.fromSafeConstant("Nu"));
		//				}
		//			}
		//		});

		List<ColumnConfig<ZborProxy, ?>> l = new ArrayList<ColumnConfig<ZborProxy, ?>>();
		l.add(cod);
		l.add(orasp);
		l.add(orasd);
		l.add(data);
		l.add(km);

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
		//		view.setSelectionModel(sm);
		view.getView().setForceFit(true);
		view.setLoadMask(true);
		view.setLoader(loader);
		view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		view.setWidth(COLUMN_FORM_WIDTH);
		view.setHeight(300);
		FramedPanel panel = new FramedPanel();
		panel.setHeaderVisible(false);
		panel.setWidth(COLUMN_FORM_WIDTH);
		panel.getElement().getStyle().setOverflowY(Overflow.AUTO);
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		panel.setWidget(con);

		con.add(new Label("Lista zborurilor deschise"), new VerticalLayoutData(1, -1));
		con.add(view, new VerticalLayoutData(1, 1));

		return panel;
	}

	/*
	 * creeaza un formular legat de selectia din grid
	 */
	private Widget createForm(){
		FramedPanel panel = new FramedPanel();
		panel.setHeadingText("Rezerva un zbor");
		panel.setHeaderVisible(false);
		panel.setWidth(COLUMN_FORM_WIDTH);
		panel.getElement().getStyle().setOverflowY(Overflow.AUTO);

		HtmlLayoutContainer con = new HtmlLayoutContainer(getTableMarkup());
		panel.setWidget(con);

		int cw = (COLUMN_FORM_WIDTH / 2) - 12;

		aeroportPlecare = new TextField();
		aeroportPlecare.setAllowBlank(false);
		aeroportPlecare.setWidth(cw);
		aeroportPlecare.setEnabled(false);
		con.add(plecareFl = new FieldLabel(aeroportPlecare, "Aeroport Plecare"), new HtmlData(".ap"));

		aeroportDestinatie = new TextField();
		aeroportDestinatie.setAllowBlank(false);
		aeroportDestinatie.setWidth(cw);
		aeroportDestinatie.setEnabled(false);
		con.add(destinatieFl = new FieldLabel(aeroportDestinatie, "Aeroport Destinatie"), new HtmlData(".ad"));

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

		ComboBox<ClasaProxy> combo = createClasaCombo();
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

		panel.addButton(new TextButton("Anuleaza", this));
		panel.addButton(new TextButton("Salveaza", this));

		// need to call after everything is constructed
		List<FieldLabel> labels = FormPanelHelper.getFieldLabels(panel);
		for (FieldLabel lbl : labels) {
			lbl.setLabelAlign(LabelAlign.TOP);
		}

		//		vp.add(panel);
		return panel;
	}

	class MainPanel extends ContentPanel {

		private VerticalLayoutContainer con = new VerticalLayoutContainer();

		private ToolBar toolBar = new ToolBar();

		public MainPanel() {
			con.setScrollMode(ScrollMode.ALWAYS);
			setPixelSize(1100, 900);
			toolBar.setSpacing(2);

			con.add(toolBar,  new VerticalLayoutData(1, -1));
			HorizontalLayoutContainer hlc = new HorizontalLayoutContainer();


			//			HTML text = new HTML("<h1>Proba<h2><p>Test1<p></br><h2>Proba2</h2>");
			//			text.getElement().getStyle().setOverflowY(Overflow.AUTO);
			//			con.add(text, new VerticalLayoutData(1, 1));
			hlc.add(createGrid());
			hlc.add(createForm());
			con.add(hlc);

			add(con);
		}

		public ToolBar getToolBar() {
			return toolBar;
		}
	}

	private native String getTableMarkup() /*-{
  return [ '<table width=100% cellpadding=0 cellspacing=0>',
      '<tr><td class=ap width=50%></td><td class=ad width=50%></td></tr>',
      '<tr><td class=nume></td><td class=prenume></td></tr>',
      '<tr><td class=dp></td><td class=cod></td></tr>',
      '<tr><td class=plata></td> <tr><td class=combo></td></tr>',
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
				//				rf.rezervareRequest().calculPret(labelProvider.getLabel(event.getSelectedItem()).toString()).fire(new Receiver<BigDecimal>() {
				//
				//					@Override
				//					public void onSuccess(BigDecimal response) {
				//						pretLbl.setText(response.toString());
				//					}
				//				});
				//				Window.alert(labelProvider.getLabel(event.getSelectedItem()));
			}
		});
	}

	private void addGroupRadioHandler(ToggleGroup toggle){
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
}
