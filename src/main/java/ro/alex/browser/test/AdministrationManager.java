package ro.alex.browser.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ro.alex.browser.ZborProxyProperties;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.ZborProxy;
import ro.alex.shared.scaffold.ScaffoldRequestFactory.ZborPagingLoadRequest;
import ro.alex.shared.scaffold.StringFilterCustom;

import com.google.gwt.aria.client.RowheaderRole;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.data.shared.loader.RequestFactoryProxy;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.RowMouseDownEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanelHelper;
import com.sencha.gxt.widget.core.client.form.HtmlEditor;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TimeField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class AdministrationManager implements SelectHandler {

	protected HorizontalLayoutContainer hLayout;
	private final ApplicationRequestFactory requestFactory;
	protected PagingLoader<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>> loader;
	private ListStore<ZborProxy> store;
	protected List<FilterConfig> filterConfig;
	protected PagingToolBar toolBar;
	private ColumnModel<ZborProxy> cm;
	private GridFilters<ZborProxy> filters;
	protected VerticalLayoutContainer con;
	private FramedPanel panel;
	protected TextField aeroportPlecareD;
	protected TextField aeroportDestinatieD;
	protected TextField nume;
	protected TextField prenume;
	protected DateField dataPlecare;
	protected Label pretLbl;
	protected TextButton anuleazaBtn;
	protected TextButton salveazaBtn;
	protected FieldLabel plecareFl;
	protected FieldLabel destinatieFl;
	protected ZborProxy zbor;
	protected CheckBox cbEscala;
	protected CheckBox cbDeschis;
	protected TimeField oraPlecare;
	protected FieldLabel oraPlecareFl;
	protected TextField codTf;
	protected FieldLabel codFl;
	protected TextField model;
	protected FieldLabel modelFl;
	private TextField distanta;
	private FieldLabel distantaFl;

	private static final int COLUMN_FORM_WIDTH = 680;


	@Inject
	public AdministrationManager(ApplicationRequestFactory requestFactory, EventBus eventBus){
		this.requestFactory = requestFactory;
	}

//	@Override
//	public Widget asWidget() {
//		hLayout = new HorizontalLayoutContainer();
//		hLayout.add(zboruriGridView());
//		hLayout.add(formDus());
//
//		return hLayout;
//	}

	protected ContentPanel gridView(){

		ContentPanel cp = new FramedPanel();
		cp.setHeaderVisible(false);
		cp.setPixelSize(700, 400);
		cp.addStyleName("margin-10");

		RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>> proxy = new RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>>() {
			@Override
			public void load(FilterPagingLoadConfig loadConfig, Receiver<? super PagingLoadResult<ZborProxy>> receiver) {
				ZborPagingLoadRequest req = requestFactory.zborPagingRequest();
				List<SortInfo> sortInfo = createRequestSortInfo(req, loadConfig.getSortInfo());

				filterConfig = createRequestFilterConfig(req, loadConfig.getFilters());

				req.findAllZborsPF(loadConfig.getOffset(), loadConfig.getLimit(), sortInfo, filterConfig).with("*", "*.*").to(receiver); 
				//				req.findAll(loadConfig.getLimit(), loadConfig.getOffset()).with("categorie.nume").to(receiver).fire();
				req.fire();
			}
		};

		loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<ZborProxy>>(
				proxy) {
			@Override
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(true);

		ZborProxyProperties props = GWT.create(ZborProxyProperties.class);

		store = new ListStore<ZborProxy>(props.id());
		loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, ZborProxy, PagingLoadResult<ZborProxy>>(
				store));

		toolBar = new PagingToolBar(6);
		toolBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolBar.bind(loader);

		ColumnConfig<ZborProxy, String> cod = new ColumnConfig<ZborProxy, String>(props.cod(), 50, "Cod");
		ColumnConfig<ZborProxy, Date> data = new ColumnConfig<ZborProxy, Date>(props.data(), 50, "Data");
		ColumnConfig<ZborProxy, String> orasp = new ColumnConfig<ZborProxy, String>(props.orasp(), 50, "Oras plecare");
		ColumnConfig<ZborProxy, String> orasd = new ColumnConfig<ZborProxy, String>(props.orasd(), 50, "Oras destinatie");
		ColumnConfig<ZborProxy, Integer> numarKm = new ColumnConfig<ZborProxy, Integer>(props.numarKm(), 50, "Distanta");
		ColumnConfig<ZborProxy, String> avionTip = new ColumnConfig<ZborProxy, String>(props.avion(), 50, "Model avion");
		ColumnConfig<ZborProxy, Boolean> escala = new ColumnConfig<ZborProxy, Boolean>(props.escala(), 20, "Escala");
		escala.setCell( new AbstractCell<Boolean>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,	Boolean value, SafeHtmlBuilder sb) {
				if (value) {
					sb.append(SafeHtmlUtils.fromSafeConstant("Da"));
				} else {
					sb.append(SafeHtmlUtils.fromSafeConstant("Nu"));
				}
			}
		});
		ColumnConfig<ZborProxy, Boolean> deschis = new ColumnConfig<ZborProxy, Boolean>(props.deschis(), 20, "Stare");
		deschis.setCell( new AbstractCell<Boolean>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,	Boolean value, SafeHtmlBuilder sb) {
				if (value) {
					sb.append(SafeHtmlUtils.fromSafeConstant("Deschis"));
				} else {
					sb.append(SafeHtmlUtils.fromSafeConstant("Inchis"));
				}
			}
		});

		List<ColumnConfig<ZborProxy, ?>> l = new ArrayList<ColumnConfig<ZborProxy, ?>>();
		l.add(cod);
		l.add(orasp);
		l.add(orasd);
		l.add(data);
		l.add(avionTip);
		l.add(numarKm);
		l.add(escala);
		l.add(deschis);

		cm = new ColumnModel<ZborProxy>(l);

		Grid<ZborProxy> view = new Grid<ZborProxy>(store, cm) {
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

		CustomGridSelectionModel selectionModel = new CustomGridSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		view.setSelectionModel(selectionModel);
		view.getView().setForceFit(true);
		view.setLoadMask(true);
		view.setLoader(loader);

		//leaga grid de form
		view.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<ZborProxy>() {

			@Override
			public void onSelectionChanged(SelectionChangedEvent<ZborProxy> event) {
				zbor = event.getSource().getSelectedItem();
				if(zbor != null){
					aeroportPlecareD.setText(zbor.getOrasp().getDenumire());
					aeroportDestinatieD.setText(zbor.getOrasd().getDenumire());
					dataPlecare.setPropertyEditor(new DateTimePropertyEditor("dd-MM-yyyy"));
					dataPlecare.setValue(zbor.getData());
					oraPlecare.setValue(zbor.getData());
					codTf.setText(zbor.getCod());
					model.setText(zbor.getAvion().getTip());
					distanta.setText(zbor.getNumarKm().toString());
					if (zbor.getDeschis()) cbDeschis.setValue(true);
					else cbDeschis.setValue(false);

					if(zbor.getEscala()) cbEscala.setValue(true);
					else cbEscala.setValue(false);
					
					salveazaBtn.setText("Modifica zbor");
				}else{
					aeroportPlecareD.setText("");
					aeroportDestinatieD.setText("");
					dataPlecare.clear();
					oraPlecare.clear();
					cbDeschis.clear();
					cbEscala.clear();
					codTf.setText("");
					model.setText("");
					distanta.setText("");
					salveazaBtn.setText("Adauga zbor");
				}
			}
		});

		// creez filtrele si le atribui
		filters = new GridFilters<ZborProxy>(loader);
		filters.initPlugin(view);
		filters.setLocal(false);//be sure to be remote, or it will affect the local cached data only

		final StringFilterCustom<ZborProxy> sfCod = new StringFilterCustom<ZborProxy>(props.cod());
		filters.addFilter(sfCod);

		final StringFilterCustom<ZborProxy> sfOrasp = new StringFilterCustom<ZborProxy>(props.orasp());
		filters.addFilter(sfOrasp);

		final StringFilterCustom<ZborProxy> sfOrasd = new StringFilterCustom<ZborProxy>(props.orasd());
		filters.addFilter(sfOrasd);

		ToolBar filterBar = new ToolBar();
		filterBar.setSpacing(1);

		Label lblProdus = new Label("Cod ");
		filterBar.add(lblProdus);


		TextField tfCod = sfCod.getTextField();

		filterBar.add(tfCod);


		filterBar.add(new SeparatorToolItem());

		Label lblOrasp = new Label("Oras plecare ");
		filterBar.add(lblOrasp);

		TextField tfOrasp = sfOrasp.getTextField();
		filterBar.add(tfOrasp);

		filterBar.add(new SeparatorToolItem());

		Label lblOrasd = new Label("Oras destinatie ");
		filterBar.add(lblOrasd);

		TextField tfOrasd = sfOrasd.getTextField();
		filterBar.add(tfOrasd);



		con = new VerticalLayoutContainer();
		con.setBorders(true);
		con.add(filterBar, new VerticalLayoutData(1, -1));
		con.add(view, new VerticalLayoutData(1, 1));
		con.add(toolBar, new VerticalLayoutData(1, -1));

		cp.setWidget(con);

		return cp;
	}

	protected Widget formView(){
		panel = new FramedPanel();
		panel.setHeadingText("Rezerva un zbor");
		panel.setHeaderVisible(false);
		panel.setWidth(COLUMN_FORM_WIDTH);
		panel.getElement().getStyle().setOverflowY(Overflow.AUTO);

		HtmlLayoutContainer con = new HtmlLayoutContainer(getTableMarkup());
		panel.setWidget(con);

		int cw = (COLUMN_FORM_WIDTH / 2) - 12;

		aeroportPlecareD = new TextField();
//		aeroportPlecareD.setAllowBlank(false);
		aeroportPlecareD.setWidth(cw);
		aeroportPlecareD.setEnabled(true);
		con.add(plecareFl = new FieldLabel(aeroportPlecareD, "Aeroport Plecare"), new HtmlData(".ap"));

		aeroportDestinatieD = new TextField();
//		aeroportDestinatieD.setAllowBlank(false);
		aeroportDestinatieD.setWidth(cw);
		aeroportDestinatieD.setEnabled(true);
		con.add(destinatieFl = new FieldLabel(aeroportDestinatieD, "Aeroport Destinatie"), new HtmlData(".ad"));

		dataPlecare = new DateField();
		dataPlecare.setWidth(cw);
		dataPlecare.setEnabled(true);
		//	    dataPlecare.
		con.add(new FieldLabel(dataPlecare, "Data plecare"), new HtmlData(".dp"));

		cbEscala = new CheckBox();
		con.add(new FieldLabel(cbEscala, "Escala"), new HtmlData(".es"));

		cbDeschis = new CheckBox();
		con.add(new FieldLabel(cbDeschis, "Deschis"), new HtmlData(".des"));
		
		oraPlecare = new TimeField();
		con.add(oraPlecareFl = new FieldLabel(oraPlecare, "Ora plecare"), new HtmlData(".ora"));
		
		codTf = new TextField();
		codTf.setAllowBlank(false);
		con.add(codFl = new FieldLabel(codTf, "Cod"), new HtmlData(".cod"));
		
		model = new TextField();
//		model.setAllowBlank(false);
		con.add(modelFl = new FieldLabel(model, "Model avion"), new HtmlData(".model"));
		
		distanta = new TextField();
//		distanta.setAllowBlank(false);
		con.add(distantaFl = new FieldLabel(distanta, "Distanta"), new HtmlData(".distanta"));

		anuleazaBtn = new TextButton("Anuleaza", this);
		panel.addButton(anuleazaBtn);
		salveazaBtn = new TextButton("Adauga zbor", this);

		panel.addButton(salveazaBtn);

		// need to call after everything is constructed
		List<FieldLabel> labels = FormPanelHelper.getFieldLabels(panel);
		for (FieldLabel lbl : labels) {
			lbl.setLabelAlign(LabelAlign.TOP);
		}
		return panel;
	}

	private void rezervariGridView(){

		
	}

	private native String getTableMarkup() /*-{
	  return [ '<table width=100% cellpadding=0 cellspacing=0>',
	      '<tr><td class=ap width=50%></td><td class=ad width=50%></td></tr>',
	      '<tr><td class=dp></td><td class=ora></td></tr>',
	      '<tr><td class=cod></td><td class=model></td></tr>',
	      '<td class=distanta></td><td></td></tr>',
	      '<tr><td class=des></td><td class=es></td></tr>','</table>'

	  ].join("");
	}-*/;

	@Override
	public void onSelect(SelectEvent event) {
		// TODO Auto-generated method stub

	}

	public class CustomGridSelectionModel extends GridSelectionModel<ZborProxy>{

		
		public CustomGridSelectionModel() {
			super();
		}
		
				
		public void handleRowMouseDown(RowMouseDownEvent event){
			int rowIndex = event.getRowIndex();

			ZborProxy sel = this.listStore.get(rowIndex);
//			Info.display("Alert", sel.getCod());
			if(isSelected(sel)) {
//				Info.display("Alert", "Pas3");
				deselect(sel);
				return;
			}
			else {
				super.handleRowMouseDown(event);
			}
		}
	}
	
	
}
