package ro.alex.browser;

import ro.alex.browser.events.AutentificareEvent;
import ro.alex.browser.events.ClearCenterEvent;
import ro.alex.browser.events.CreateUserEvent;
import ro.alex.browser.events.DusEvent;
import ro.alex.browser.events.DusIntorsEvent;
import ro.alex.browser.events.LogInEvent;
import ro.alex.browser.events.LogOutEvent;
import ro.alex.browser.handlers.AutentificareEventHandler;
import ro.alex.browser.handlers.ClearCenterEventHandler;
import ro.alex.browser.handlers.CreateUserEventHandler;
import ro.alex.browser.handlers.DusEventHandler;
import ro.alex.browser.handlers.DusIntorsEventHandler;
import ro.alex.browser.handlers.LogInEventHandler;
import ro.alex.browser.handlers.LogOutEventHandler;
import ro.alex.browser.test.AdministrationView;
import ro.alex.browser.ui.MenuView;
import ro.alex.browser.ui.view.CreateRezervareView;
import ro.alex.browser.ui.view.CreateUserFormView;
import ro.alex.browser.ui.view.LogInView;
import ro.alex.browser.ui.view.PersoanaView;
import ro.alex.browser.ui.view.RezervareDusIntorsView;
import ro.alex.browser.ui.view.UserView;
import ro.alex.browser.ui.window.DisplayTransactionInfo;
import ro.alex.client.managed.request.ApplicationRequestFactory;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.info.Info;
/**
 * Imparte interfata in 5 zone: Nord, Sud, Est, Vest si Centru
 * @author Alex Dutescu
 *
 */
public class BorderedLayoutWidgetContainer implements IsWidget {

	
	public ContentPanel center;

	private final ApplicationRequestFactory requestFactory;
	private final EventBus eventBus;
	private final MenuView menu;
	private RezervareDusIntorsView rv;
	private PersoanaView uv;
	private final LogInView logInView;
	private CreateRezervareView crv;
	private final CreateUserFormView userFormView;
	private final DisplayTransactionInfo dti;
	
	@Inject
	public BorderedLayoutWidgetContainer(CreateUserFormView userFormView, ApplicationRequestFactory requestFactory, MenuView menu, EventBus eventBus, LogInView logInView, CreateRezervareView crv, DisplayTransactionInfo dti){
		this.requestFactory = requestFactory;
		this.crv = crv;
		this.eventBus = eventBus;
		this.menu = menu;
		this.dti = dti;
//		this.rv = rv;
		
		this.logInView = logInView;
		this.userFormView = userFormView;
		initHandlers();
	}


	public void initHandlers(){
		eventBus.addHandler(LogInEvent.TYPE, new LogInEventHandler() {
			
			@Override
			public void onLogInEvent(LogInEvent autentificareEvent) {
				center.add(logInView);
//				center.add(crv);
				menu.setLogged(false);
			}
		});
		eventBus.addHandler(AutentificareEvent.TYPE, new AutentificareEventHandler() {
			
			@Override
			public void onAutentificareEvent(AutentificareEvent autentificareEvent) {
				center.clear();
				Info.display("Admin", autentificareEvent.getPersoana().getUser().getNick());
//				center.add(uv = new UserView(autentificareEvent.getPersoana(), requestFactory));
				if(autentificareEvent.getPersoana().getUser().getNick().equals("Administrator")) center.add(new AdministrationView(requestFactory, eventBus));
				else center.add(uv = new PersoanaView(autentificareEvent.getPersoana(), requestFactory));
				menu.setLogged(true);
			}
		});
		eventBus.addHandler(LogOutEvent.TYPE, new LogOutEventHandler() {
			
			@Override
			public void onLogOutEvent(LogOutEvent autentificareEvent) {
//				center.remove(uv);
				center.clear();
				center.add(logInView);
			}
		});
		eventBus.addHandler(DusIntorsEvent.TYPE, new DusIntorsEventHandler() {
			
			@Override
			public void onDusIntorsEvent(DusIntorsEvent dusIntorsEvent) {
				rv = null;
				rv = new RezervareDusIntorsView(requestFactory, eventBus, dti);
				rv.setDusIntors(true);
				rv.setDatap(dusIntorsEvent.getDatap());
				rv.setDatai(dusIntorsEvent.getDatai());
				rv.setEscala(dusIntorsEvent.isEscala());
				rv.setOrasp(dusIntorsEvent.getOrasp());
				rv.setOrasd(dusIntorsEvent.getOrasd());
				rv.setOraspi(dusIntorsEvent.getOraspi());
				rv.setOrasdi(dusIntorsEvent.getOrasdi());
				rv.setLogged(menu.isLogged());
				center.clear();
				center.add(rv);
			}
		});
		eventBus.addHandler(DusEvent.TYPE, new DusEventHandler() {
			
			@Override
			public void onDusEvent(DusEvent dusEvent) {
				rv = null;
				rv = new RezervareDusIntorsView(requestFactory, eventBus, dti);
				rv.setDusIntors(false);
				rv.setDatap(dusEvent.getDatap());
				rv.setEscala(dusEvent.isEscala());
				rv.setOrasp(dusEvent.getOrasp());
				rv.setOrasd(dusEvent.getOrasd());
				center.clear();
				center.add(rv);
			}
		});
		eventBus.addHandler(CreateUserEvent.TYPE, new CreateUserEventHandler() {
			
			@Override
			public void onCreateUserEvent(CreateUserEvent createUserEvent) {
				center.clear();
				center.add(userFormView);
			}
		});
		eventBus.addHandler(ClearCenterEvent.TYPE, new ClearCenterEventHandler() {
			
			@Override
			public void onClearEvent(ClearCenterEvent event) {
				center.clear();
			}
		});
	}
	
	@Override
	public Widget asWidget() {

		final BorderLayoutContainer con = new BorderLayoutContainer();
		con.setBorders(false);

		ContentPanel north = new ContentPanel();
		north.setHeaderVisible(false);
		north.setCollapsible(false);

		ContentPanel west = new ContentPanel();
		west.setHeaderVisible(false);
		west.setCollapsible(false);
		west.add( menu );

		center = new ContentPanel();
		center.setHeaderVisible(false);
		center.setCollapsible(false);
		center.setResize(true);
//		center.add(rv);

		ContentPanel east = new ContentPanel();
		east.setHeaderVisible(false);
		east.setCollapsible(false);

		ContentPanel south = new ContentPanel();
		south.setHeaderVisible(false);
		south.setCollapsible(false);

		BorderLayoutData northData = new BorderLayoutData(100);
		northData.setMargins(new Margins(5));
		northData.setCollapsible(false);
		northData.setSplit(false);


		BorderLayoutData westData = new BorderLayoutData(150);
		westData.setCollapsible(false);
		westData.setSplit(true);
		westData.setCollapseMini(false);
		westData.setMargins(new Margins(0, 5, 0, 5));

		MarginData centerData = new MarginData();

		BorderLayoutData eastData = new BorderLayoutData(150);
		eastData.setMargins(new Margins(0, 5, 0, 5));
		eastData.setCollapsible(false);
		eastData.setSplit(true);

		BorderLayoutData southData = new BorderLayoutData(100);
		southData.setMargins(new Margins(5));
		southData.setCollapsible(false);
		southData.setCollapseMini(false);

		con.setNorthWidget(north, northData);
		con.setWestWidget(west, westData);
		con.setCenterWidget(center, centerData);
		con.setEastWidget(east, eastData);
		con.setSouthWidget(south, southData);

		SimpleContainer simple = new SimpleContainer();
		simple.add(con, new MarginData(10));

		return simple;
	}
}




