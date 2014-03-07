package ro.alex.browser.test;

import java.util.ArrayList;
import java.util.List;

import ro.alex.browser.RezervareProxyProperties;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.RezervareProxy;
import ro.alex.shared.scaffold.ScaffoldRequestFactory.RezervarePagingLoadRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.data.shared.loader.RequestFactoryProxy;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

public class AdministrationView extends AdministrationManager implements IsWidget{

	private final ApplicationRequestFactory requestFactory;
	private final EventBus eventBus;
	private boolean isRezervareEdit = false;
	
	@Inject
	public AdministrationView(ApplicationRequestFactory requestFactory,
			EventBus eventBus) {
		super(requestFactory, eventBus);
		this.eventBus = eventBus;
		this.requestFactory = requestFactory;
		
	}

	@Override
	public Widget asWidget() {
		hLayout = new HorizontalLayoutContainer();
		if(!isRezervareEdit)hLayout.add(super.gridView());
		else hLayout.add(gridView());
		hLayout.add(super.formView());

		return hLayout;
	}
	
	public ContentPanel gridView(){
			ContentPanel cp = new FramedPanel();
			cp.setHeaderVisible(false);
			cp.setPixelSize(700, 400);
			cp.addStyleName("margin-10");

			RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<RezervareProxy>> proxy = new RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<RezervareProxy>>() {
				@Override
				public void load(FilterPagingLoadConfig loadConfig, Receiver<? super PagingLoadResult<RezervareProxy>> receiver) {
					RezervarePagingLoadRequest req = requestFactory.rezervarePagingLoadRequest();
					List<SortInfo> sortInfo = createRequestSortInfo(req, loadConfig.getSortInfo());

					filterConfig = createRequestFilterConfig(req, loadConfig.getFilters());

					req.findAllRezervariPaginat(loadConfig.getOffset(), loadConfig.getLimit(), sortInfo, filterConfig).with("*", "*.*", "*.*.*", "*.*.*.*").to(receiver); 
					//				req.findAll(loadConfig.getLimit(), loadConfig.getOffset()).with("categorie.nume").to(receiver).fire();
					req.fire();
				}
			};

			final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<RezervareProxy>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<RezervareProxy>>(
					proxy) {
				@Override
				protected FilterPagingLoadConfig newLoadConfig() {
					return new FilterPagingLoadConfigBean();
				}
			};
			loader.setRemoteSort(true);

			RezervareProxyProperties props = GWT.create(RezervareProxyProperties.class);
			
			ListStore<RezervareProxy> store = new ListStore<RezervareProxy>(props.id());
			loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, RezervareProxy, PagingLoadResult<RezervareProxy>>(
					store));

			toolBar = new PagingToolBar(6);
			toolBar.getElement().getStyle().setProperty("borderBottom", "none");
			toolBar.bind(loader);
			
			ColumnConfig<RezervareProxy, String> cod = new ColumnConfig<RezervareProxy, String>(props.cod(), 50, "Cod");
			ColumnConfig<RezervareProxy, Double> cost = new ColumnConfig<RezervareProxy, Double>(props.cost(), 50, "Cost");
			ColumnConfig<RezervareProxy, String> locDus = new ColumnConfig<RezervareProxy, String>(props.locDus(), 50, "NrLocDus");
			ColumnConfig<RezervareProxy, String> locIntors = new ColumnConfig<RezervareProxy, String>(props.locIntors(), 50, "NrLocIntors");
			ColumnConfig<RezervareProxy, String> orasdp = new ColumnConfig<RezervareProxy, String>(props.orasDP(), 50, "OrasPlecareDus");
			ColumnConfig<RezervareProxy, String> orasdd = new ColumnConfig<RezervareProxy, String>(props.orasDD(), 50, "OrasDestinatieDus");
//			ColumnConfig<RezervareProxy, String> orasip = new ColumnConfig<RezervareProxy, String>(props.orasIP(), 50, "OrasPlecareIntors");
//			ColumnConfig<RezervareProxy, String> orasid = new ColumnConfig<RezervareProxy, String>(props.orasID(), 50, "OrasDestinatieIntors");
			ColumnConfig<RezervareProxy, String> nume = new ColumnConfig<RezervareProxy, String>(props.nume(), 50, "Nume");
			ColumnConfig<RezervareProxy, String> prenume = new ColumnConfig<RezervareProxy, String>(props.prenume(), 50, "Prenume");
			
			List<ColumnConfig<RezervareProxy, ?>> l = new ArrayList<ColumnConfig<RezervareProxy, ?>>();
			l.add(cod);
			l.add(orasdp);
			l.add(orasdd);
//			l.add(orasip);
//			l.add(orasid);
			l.add(cost);
			l.add(locDus);
			l.add(locIntors);
			l.add(nume);
			l.add(prenume);
			
			ColumnModel<RezervareProxy> cm = new ColumnModel<RezervareProxy>(l);
			
			Grid<RezervareProxy> view = new Grid<RezervareProxy>(store, cm) {
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

//			CustomGridSelectionModel selectionModel = new CustomGridSelectionModel();
//			selectionModel.setSelectionMode(SelectionMode.SINGLE);
//			view.setSelectionModel(selectionModel);
			view.getView().setForceFit(true);
			view.setLoadMask(true);
			view.setLoader(loader);
			
			view.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<RezervareProxy>() {

				private RezervareProxy rezervare;

				@Override
				public void onSelectionChanged(SelectionChangedEvent<RezervareProxy> event) {
					rezervare = event.getSource().getSelectedItem();
					
				}
			});
			
			con = new VerticalLayoutContainer();
			con.setBorders(true);
//			con.add(filterBar, new VerticalLayoutData(1, -1));
			con.add(view, new VerticalLayoutData(1, 1));
			con.add(toolBar, new VerticalLayoutData(1, -1));

			cp.setWidget(con);
			
			return cp;
	}

}
