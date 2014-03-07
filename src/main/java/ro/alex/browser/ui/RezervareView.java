package ro.alex.browser.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.proxy.RezervareProxy;
import ro.alex.client.proxy.ZborProxy;
import ro.alex.shared.scaffold.ScaffoldRequestFactory.ClasaPagingLoadRequest;
import ro.alex.shared.scaffold.ScaffoldRequestFactory.ZborPagingLoadRequest;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Format;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.data.shared.loader.RequestFactoryProxy;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.HideEvent;
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
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

/**
 * Afiseaza un grid pentru toate zborurile cu filtre pe AeroportP si AeroportD, un formular de completare a rezervarii </br>
 * TO DO: </br>
 * - implementeaza modelul gridului </br> - done
 * - implementeaza un formular: AeroportD, AeroportP, Data plecare, Pret bilet, check box "Abonament" si "Prin agentie", combo box clasa zbor - done
 * - implementeaza binding intre grid si urmatoarele campuri din formular: AeroportD, AeroportP, Data plecare - done
 * - calcul pret in functie de clasa selectata, generator coduri
 * - implementeaza functionalitate butoane Salveaza si Anuleaza 
 * 
 * @author Alex Dutescu
 *
 */
public class RezervareView implements IsWidget, SelectHandler {

	@Override
	public void onSelect(SelectEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}

//	private HorizontalPanel vp;
//	private static final int COLUMN_FORM_WIDTH = 680;
//	private FieldLabel plecareFl;
//	private FieldLabel destinatieFl;
//	private TextField aeroportPlecare;
//	private TextField aeroportDestinatie;
//	private DateField dataPlecare;
//	private String codGenerat;
//	private Label pretLbl;
//	private BigDecimal pretCalculat;
//	private TextField nume;
//	private TextField prenume;
//	private HideHandler hideHandler;
//	private boolean isAbonament = false;
//	private Integer nrKm = 0;
//
//	interface ClasaProperties extends PropertyAccess<ClasaProxy> {
//		ModelKeyProvider<ClasaProxy> id();
//
//		LabelProvider<ClasaProxy> clasa();
//	}
//
//	interface ZborProxyProperties extends PropertyAccess<ZborProxy> {
//		ModelKeyProvider<ZborProxy> id();
//
//		ValueProvider<ZborProxy, Long> numarLocuri(); 
//
//		ValueProvider<ZborProxy, String> plecare();
//
//		ValueProvider<ZborProxy, String> destinatie();
//
//		ValueProvider<ZborProxy, Boolean> escala();
//	}
//
//	private final ApplicationRequestFactory rf;
//
//
//	@Inject
//	public RezervareView(ApplicationRequestFactory rf){
//		this.rf = rf;
//		getCodGenerat();
//	}
//
//	@Override
//	public Widget asWidget() {
//
//		//hide handler
//		hideHandler = new HideHandler() {
//			@Override
//			public void onHide(HideEvent event) {
//				Dialog btn = (Dialog) event.getSource();
//				String msg = Format.substitute("The '{0}' button was pressed", btn.getHideButton().getText());
//				Info.display("MessageBox", msg);
//			}
//		};
//
//		if (vp == null) {
//			vp = new HorizontalPanel();
//			vp.setSpacing(10);
//			createGrid();
//			createForm();
//		}
//		return vp;
//	}
//
//	private ComboBox<ClasaProxy> createClasaCombo(){
//		ClasaProperties props = GWT.create(ClasaProperties.class);
//		final ListStore<ClasaProxy> clase = new ListStore<ClasaProxy>(props.id());
//		ComboBox<ClasaProxy> combo = new ComboBox<ClasaProxy>(clase, props.clasa());
//
//		//		addHandlersForEventObservation(combo, props.clasa());
//
//
//
//		combo.setEmptyText("Selectati o clasa");
//		combo.setWidth(150);
//		combo.setTypeAhead(true);
//		combo.setTriggerAction(TriggerAction.ALL);
//		combo.setEditable(false);
//		combo.setExpanded(false);
//
//		RequestFactoryProxy<PagingLoadConfig, PagingLoadResult<ClasaProxy>> proxy = new RequestFactoryProxy<PagingLoadConfig, PagingLoadResult<ClasaProxy>>() {
//			@Override
//			public void load(PagingLoadConfig loadConfig, Receiver<? super PagingLoadResult<ClasaProxy>> receiver) {
//				ClasaPagingLoadRequest reqClasa = rf.clasaPagingRequest();
//
//				//				List<SortInfo> sortInfo = createRequestSortInfo(reqClasa, loadConfig.getSortInfo());
//				reqClasa.findAllClasePaginat(loadConfig.getOffset(), loadConfig.getLimit()).with("clasa").to(receiver).fire();
//			}
//		};
//
//		final PagingLoader<PagingLoadConfig, PagingLoadResult<ClasaProxy>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<ClasaProxy>>(proxy);
//		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, ClasaProxy, PagingLoadResult<ClasaProxy>>(clase));
//		loader.setRemoteSort(true);
//
//		//		combo.setPageSize(3);
//		combo.setLoader(loader);
//		combo.setTriggerAction(ComboBoxCell.TriggerAction.QUERY);
//		combo.setForceSelection(true);
//
//		//handler for combo
//		addHandlersForEventObservation(combo, props.clasa());
//
//		return combo;
//	}
//
//
//
//	private void createGrid(){
//		//		IdentityValueProvider<ZborProxy> identity = new IdentityValueProvider<ZborProxy>();
//
//		RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>> proxy = new RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>>() {
//			@Override
//			public void load(FilterPagingLoadConfig loadConfig, Receiver<? super PagingLoadResult<ZborProxy>> receiver) {
//				ZborPagingLoadRequest req = rf.zborPagingRequest();
//				List<SortInfo> sortInfo = createRequestSortInfo(req, loadConfig.getSortInfo());
//				//								List<FilterConfig> filterConfig = createRequestFilterConfig(req, loadConfig.getFilters());
//				//				req.findAllZborsPaginat(loadConfig.getOffset(), loadConfig.getLimit(), sortInfo).to(receiver);
//				//				req.findAll(loadConfig.getLimit(), loadConfig.getOffset()).with("categorie.nume").to(receiver).fire();
//				//				req.fire();
//				req.findAllZborsPaginat(loadConfig.getOffset(), loadConfig.getLimit(), sortInfo).with("zbor").fire(receiver);
//			}
//		};
//		final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>>(proxy) {
//			@Override
//			protected FilterPagingLoadConfig newLoadConfig() {
//				return new FilterPagingLoadConfigBean();
//			}
//		};
//		loader.setRemoteSort(true);
//
//		ZborProxyProperties props = GWT.create(ZborProxyProperties.class);
//
//		ListStore<ZborProxy> store = new ListStore<ZborProxy>(props.id());
//		loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, ZborProxy, PagingLoadResult<ZborProxy>>(
//				store));
//
//		final PagingToolBar toolBar = new PagingToolBar(50);
//		toolBar.getElement().getStyle().setProperty("borderBottom", "none");
//		toolBar.bind(loader);
//
//		ColumnConfig<ZborProxy, Long> numarLocuri = new ColumnConfig<ZborProxy, Long>(props.numarLocuri(), 50, "Numar locuri");
//		//		ColumnConfig<ZborProxy, String> aeroportP = new ColumnConfig<ZborProxy, String>(props.aeroportP(), 150, "AeroportP");
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
//
//		List<ColumnConfig<ZborProxy, ?>> l = new ArrayList<ColumnConfig<ZborProxy, ?>>();
//		//		l.add(sm.getColumn());
//
//		l.add(plecare);
//		l.add(destinatie);
//		l.add(numarLocuri);
//		l.add(escala);
//		//		l.add(aeroportP);
//
//		ColumnModel<ZborProxy> cm = new ColumnModel<ZborProxy>(l);
//
//		final Grid<ZborProxy> view = new Grid<ZborProxy>(store, cm) {
//			@Override
//			protected void onAfterFirstAttach() {
//				super.onAfterFirstAttach();
//				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
//					@Override
//					public void execute() {
//						loader.load();
//					}
//				});
//			}
//		};
//		//		view.setSelectionModel(sm);
//		view.getView().setForceFit(true);
//		view.setLoadMask(true);
//		view.setLoader(loader);
//		view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//
//		//bind grid vs form
//		view.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<ZborProxy>() {
//
//			@Override
//			public void onSelectionChanged(SelectionChangedEvent<ZborProxy> event) {
//				nrKm = event.getSource().getSelectedItem().getNumarKm();
//				Date data = event.getSource().getSelectedItem().getDataPlecare();
//				String day, month, year;
//				day = DateTimeFormat.getFormat(PredefinedFormat.DAY).format(data);
//				month = DateTimeFormat.getFormat(PredefinedFormat.MONTH).format(data);
//				year = DateTimeFormat.getFormat(PredefinedFormat.YEAR).format(data);
//				if(aeroportPlecare != null && aeroportDestinatie != null){
//					aeroportPlecare.setText(event.getSource().getSelectedItem().getPlecare());
//					aeroportDestinatie.setText(event.getSource().getSelectedItem().getDestinatie());
//					dataPlecare.setText(day +"-"+ month +"-"+ year);
//				}
//			}
//		});
//
//
//
//		// Create the filters, and hook them to the loader and grid
//		GridFilters<ZborProxy> filters = new GridFilters<ZborProxy>(loader);
//		filters.initPlugin(view);
//		filters.setLocal(false);//be sure to be remote, or it will affect the local cached data only
//
//		//		filters.addFilter(new StringFilter<ZborProxy>(props.aeroportP()));
//		//		filters.addFilter(new LongFilter<ZborProxy>(props.numarLocuri().toString()));
//
//		FramedPanel cp = new FramedPanel();
//		cp.setHeadingText("Lista zboruri deschise");
//		cp.setWidth(COLUMN_FORM_WIDTH);
//		cp.setHeight(600);
//		cp.setHeaderVisible(true);
//		//		cp.setPixelSize(500, 400);
//		//		cp.addStyleName("margin-10");
//
//		VerticalLayoutContainer con = new VerticalLayoutContainer();
//		con.setBorders(true);
//		con.add(view, new VerticalLayoutData(1, 1));
//		//		con.add(toolBar, new VerticalLayoutData(1, -1));
//		cp.setWidget(con);
//
//		vp.add(cp);
//	}
//
//	private void createForm() {
//		FramedPanel panel = new FramedPanel();
//		panel.setHeadingText("Rezerva un zbor");
//		panel.setWidth(COLUMN_FORM_WIDTH);
//
//		HtmlLayoutContainer con = new HtmlLayoutContainer(getTableMarkup());
//		panel.setWidget(con);
//
//		int cw = (COLUMN_FORM_WIDTH / 2) - 12;
//
//		aeroportPlecare = new TextField();
//		aeroportPlecare.setAllowBlank(false);
//		aeroportPlecare.setWidth(cw);
//		aeroportPlecare.setEnabled(false);
//		con.add(plecareFl = new FieldLabel(aeroportPlecare, "Aeroport Plecare"), new HtmlData(".ap"));
//
//		aeroportDestinatie = new TextField();
//		aeroportDestinatie.setAllowBlank(false);
//		aeroportDestinatie.setWidth(cw);
//		aeroportDestinatie.setEnabled(false);
//		con.add(destinatieFl = new FieldLabel(aeroportDestinatie, "Aeroport Destinatie"), new HtmlData(".ad"));
//
//		nume = new TextField();
//		nume.setWidth(cw);
//		con.add(new FieldLabel(nume, "Nume"), new HtmlData(".nume"));
//
//		prenume = new TextField();
//		prenume.setWidth(cw);
//		con.add(new FieldLabel(prenume, "Prenume"), new HtmlData(".prenume"));
//
//		dataPlecare = new DateField();
//		dataPlecare.setWidth(cw);
//		dataPlecare.setEnabled(false);
//		//	    dataPlecare.
//		con.add(new FieldLabel(dataPlecare, "Data plecare"), new HtmlData(".dp"));
//
//		//modalitate plata
//		Radio radio1 = new Radio();
//		radio1.setBoxLabel("Abonament");
//
//		Radio radio2 = new Radio();
//		radio2.setBoxLabel("Prin agentie");
//
//		pretLbl = new Label("Selectati o categorie pentru a calcula pretul!");
//		//		codLbl.setText(getCodGenerat());
//		con.add(new FieldLabel(pretLbl, "Pretul calculat"), new HtmlData(".cod"));
//
//		HorizontalPanel hp = new HorizontalPanel();
//		hp.add(radio1);
//		hp.add(radio2);
//
//		con.add(new FieldLabel(hp, "Modalitate de plata"), new HtmlData(".plata"));
//
//		ToggleGroup group = new ToggleGroup();
//		group.add(radio1);
//		group.add(radio2);
//		addGroupRadioHandler(group);
//
//		ComboBox<ClasaProxy> combo = createClasaCombo();
//		con.add(combo, new HtmlData(".combo"));
//
//		HtmlEditor a = new HtmlEditor();
//		a.setWidth(COLUMN_FORM_WIDTH - 25);
//		a.setEnableAlignments(false);
//		a.setEnableColors(false);
//		a.setEnableFont(false);
//		a.setEnableFontSize(false);
//		a.setEnableLinks(false);
//		a.setEnableLists(false);
//		con.add(new FieldLabel(a, "Observatii"), new HtmlData(".editor"));
//
//		panel.addButton(new TextButton("Anuleaza", this));
//		panel.addButton(new TextButton("Salveaza", this));
//
//		// need to call after everything is constructed
//		List<FieldLabel> labels = FormPanelHelper.getFieldLabels(panel);
//		for (FieldLabel lbl : labels) {
//			lbl.setLabelAlign(LabelAlign.TOP);
//		}
//
//		vp.add(panel);
//	}
//
//	private RezervareProxy setRezervare(ZborProxy zbor, String modPlata, BigDecimal pretTotal){
//		RezervareProxy rp = rf.rezervareRequest().create(RezervareProxy.class);
//
//		rp.setCod(null);
//		rp.setZbor(zbor);
//		rp.setModPlata(modPlata);
//		rp.setPretTotal(pretTotal);
//
//		return rp;
//	}
//
//	private void saveRezervare(RezervareProxy rp){
//		rf.rezervareRequest().persist().using(rp).fire();
//	}
//
//
//	private void getCodGenerat(){
//		rf.rezervareRequest().stringGenerator().fire(new Receiver<String>() {
//
//			@Override
//			public void onSuccess(String response) {
//				//				codLbl.setText(response);
//			}
//		});
//
//	}
//
//	private native String getTableMarkup() /*-{
//    return [ '<table width=100% cellpadding=0 cellspacing=0>',
//        '<tr><td class=ap width=50%></td><td class=ad width=50%></td></tr>',
//        '<tr><td class=nume></td><td class=prenume></td></tr>',
//        '<tr><td class=dp></td><td class=cod></td></tr>',
//        '<tr><td class=plata></td> <tr><td class=combo></td></tr>',
//        '<tr><td class=editor colspan=2></td></tr>', '</table>'
//
//    ].join("");
//  }-*/;
//
//	//implement handlers
//	private <T> void addHandlersForEventObservation(ComboBox<T> combo, final LabelProvider<T> labelProvider) {
//		combo.addValueChangeHandler(new ValueChangeHandler<T>() {
//			@Override
//			public void onValueChange(ValueChangeEvent<T> event) {
//				Info.display("Valoare schimbata", "Valoare noua: "
//						+ (event.getValue() == null ? "nimic" : labelProvider.getLabel(event.getValue()) + "!"));
//				//				rf.rezervareRequest().calculPret(labelProvider.getLabel(event.getValue())).fire(new Receiver<BigDecimal>() {
//				//
//				//					@Override
//				//					public void onSuccess(BigDecimal response) {
//				//						codLbl.setText(response.toString());
//				//					}
//				//				});
//				//				Window.alert(labelProvider.getLabel(event.getValue()));
//			}
//		});
//		combo.addSelectionHandler(new SelectionHandler<T>() {
//			@Override
//			public void onSelection(SelectionEvent<T> event) {
//				Info.display("State Selected", "You selected "
//						+ (event.getSelectedItem() == null ? "nothing" : labelProvider.getLabel(event.getSelectedItem()) + "!"));
//				rf.rezervareRequest().calculPret(labelProvider.getLabel(event.getSelectedItem()).toString()).fire(new Receiver<BigDecimal>() {
//
//					@Override
//					public void onSuccess(BigDecimal response) {
//						pretLbl.setText(response.toString());
//					}
//				});
//				//				Window.alert(labelProvider.getLabel(event.getSelectedItem()));
//			}
//		});
//	}
//
//	private void addGroupRadioHandler(ToggleGroup toggle){
//		toggle.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
//				ToggleGroup group = (ToggleGroup)event.getSource();
//				Radio radio = (Radio)group.getValue();
//				Info.display("Mod plata selectat", "Ati ales: " + radio.getBoxLabel());
//				if("Abonament".equals(radio.getBoxLabel())) isAbonament = true;
//				else isAbonament = false;
//			}
//		});
//	}
//
//	@Override
//	public void onSelect(SelectEvent event) {
//		TextButton tb = (TextButton)event.getSource();
//		if(tb.getText() == "Salveaza") {
////			Window.alert("Saving data");//do save thingy
//			
//			if(validareCampuri() && isAbonament){
//				final PromptMessageBox box = new PromptMessageBox("Abonament", "Introduceti seria abonamentului:");
//				box.addHideHandler(new HideHandler() {
//					@Override
//					public void onHide(HideEvent event) {
//						Info.display("MessageBox", "You entered " + box.getValue());
//						rf.abonamentScaffoldRequest().findAbonamentBySerie(Integer.parseInt(box.getValue())).fire(new Receiver<AbonamentProxy>() {
//
//							@Override
//							public void onSuccess(AbonamentProxy response) {
//								if(nrKm <= response.getKilometri()) Window.alert("Aveti suficienti km");//afisare display confirmare rezervare
//								else {
//									Window.alert("Numar km insuficienti!");//+ promt daca doreste sa plateasca numerar
//								}
//							}
//							
//							@Override
//							public void onFailure(ServerFailure e){
//								AlertMessageBox d = new AlertMessageBox("Atentie", "Seria "+ box.getValue() +"nu a fost gasita");
//						        d.addHideHandler(hideHandler);
//						        d.show();
//							}
//						});
//					}
//				});
//				box.show();
//			}
//		}
//		if(tb.getText() == "Anuleaza") Window.alert("Cancel everything and move on");//do cancel thingy
//
//	}
//
//	private boolean validareCampuri(){
//		boolean isValid = true;
//		if(nume.getValue() != null && prenume.getValue() != null){
//			if (nume.getValue().length() < 3 && prenume.getValue().length() < 3) {
//				AlertMessageBox d = new AlertMessageBox("Atentie", "Numele si Prenumele introduse sunt prea mici");
//				d.addHideHandler(hideHandler);
//				d.show();
//				nume.reset(); prenume.reset();
//				isValid = false;
//			}
//			else if(nume.getValue().length() < 3) {
//				AlertMessageBox d = new AlertMessageBox("Atentie", "Numele introdus este prea mic");
//				d.addHideHandler(hideHandler);
//				d.show();
//				nume.reset();
//				isValid = false;
//			}
//			else if(prenume.getValue().length() < 3){
//				AlertMessageBox d = new AlertMessageBox("Atentie", "Prenumele introdus este prea mic");
//				d.addHideHandler(hideHandler);
//				d.show();
//				prenume.reset();
//				isValid = false;
//			}
//		}
//		else if(nume.getValue() == null && prenume.getValue() == null){
//			AlertMessageBox d = new AlertMessageBox("Atentie", "Numele si Prenumele introduse sunt prea mici");
//			d.addHideHandler(hideHandler);
//			d.show();
//			nume.reset(); prenume.reset();
//			isValid = false;
//		}
//		else if(nume.getValue() == null){
//			AlertMessageBox d = new AlertMessageBox("Atentie", "Numele introdus este prea mic");
//			d.addHideHandler(hideHandler);
//			d.show();
//			nume.reset();
//			isValid = false;
//		}else{
//			AlertMessageBox d = new AlertMessageBox("Atentie", "Prenumele introdus este prea mic");
//			d.addHideHandler(hideHandler);
//			d.show();
//			prenume.reset();
//			isValid = false;
//		}
//
//		return isValid;
//	}

}
