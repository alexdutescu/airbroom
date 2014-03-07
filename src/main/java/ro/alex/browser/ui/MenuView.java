/**
 * 
 */
package ro.alex.browser.ui;

import ro.alex.browser.events.AutentificareEvent;
import ro.alex.browser.events.CreateUserEvent;
import ro.alex.browser.events.LogInEvent;
import ro.alex.browser.events.LogOutEvent;
import ro.alex.browser.handlers.AutentificareEventHandler;
import ro.alex.browser.handlers.LogOutEventHandler;
import ro.alex.client.managed.request.ApplicationRequestFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.ExpandMode;
import com.sencha.gxt.widget.core.client.event.ExpandEvent;
import com.sencha.gxt.widget.core.client.event.ExpandEvent.ExpandHandler;

/**
 * Alcatuieste meniul
 * @author Alex Dutescu
 *
 * TO DO: 
 * - modifica dimensiunile la buton
 * - adauga functionalitate pentru Rezerva bilet
 */
public class MenuView implements IsWidget{

	private final ApplicationRequestFactory requestFactory;
	private final EventBus eventBus;
	private boolean isLogged=false;
	private final RezervareMenuWidget rmw;

	@Inject
	public MenuView(ApplicationRequestFactory requestFactory, EventBus eventBus, RezervareMenuWidget rmw){
		this.requestFactory = requestFactory;
		this.eventBus = eventBus;
		this.rmw = rmw;
	}

	@Override
	public Widget asWidget() {
		ContentPanel panel = new ContentPanel();
		panel.setHeadingText("Meniu");
		panel.setBodyBorder(false);
		panel.setPixelSize(200, 325);
		panel.addStyleName("margin-10");

		final AccordionLayoutContainer con = new AccordionLayoutContainer();
		con.setExpandMode(ExpandMode.SINGLE);
		con.setHideCollapseTool(true);
		panel.add(con);

		AccordionLayoutAppearance appearance = GWT.<AccordionLayoutAppearance> create(AccordionLayoutAppearance.class);

		final ContentPanel cp = new ContentPanel(appearance);

		cp.setAnimCollapse(false);
		cp.setHeadingText("Home");
		cp.addExpandHandler(new ExpandHandler() {

			@Override
			public void onExpand(ExpandEvent event) {
				cp.setExpanded(false);
			}
		});
		con.add(cp);

		final ContentPanel cpm = new ContentPanel(appearance);
		cpm.setExpanded(false);
		cpm.setBodyStyleName("pad-text");
		cpm.setHeadingText("Rezerva bilet");
		cpm.addExpandHandler(new ExpandHandler() {

			@Override
			public void onExpand(ExpandEvent event) {
//				cpm.setExpanded(false);
				//adauga functionalitate
				

			}
		});
		cpm.add(rmw);
		con.add(cpm);

		final ContentPanel cpr = new ContentPanel(appearance);
		final String string = isLogged ? "Log out" : "Autentificare";
		cpr.setHeadingText(string);
		cpr.setAnimCollapse(false);
		cpr.setBodyStyleName("pad-text");
		cpr.setHeight(100);
		cpr.addExpandHandler(new ExpandHandler() {

			@Override
			public void onExpand(ExpandEvent event) {
				cpr.setExpanded(false);
				if(!isLogged) eventBus.fireEvent(new LogInEvent());
				else eventBus.fireEvent(new LogOutEvent());

			}
		});
		con.add(cpr);
		
		final ContentPanel cpc = new ContentPanel(appearance);
		cpc.setHeadingText("Creeaza utilizator");
		cpc.setAnimCollapse(false);
		cpc.setBodyStyleName("pad-text");
		cpc.setHeight(100);
		cpc.addExpandHandler(new ExpandHandler() {

			@Override
			public void onExpand(ExpandEvent event) {
				cpc.setExpanded(false);
				//adauga functionalitate - deschide formular
				eventBus.fireEvent(new CreateUserEvent());
			}
		});
		con.add(cpc);

		//init handlers
		eventBus.addHandler(AutentificareEvent.TYPE, new AutentificareEventHandler() {

			@Override
			public void onAutentificareEvent(AutentificareEvent autentificareEvent) {
				isLogged = true;
				String string = isLogged ? "Log out" : "Autentificare";
				cpr.setHeadingText(string);
				cpm.setVisible(false);
				cpm.setEnabled(false);
			}
		});
		
		eventBus.addHandler(LogOutEvent.TYPE, new LogOutEventHandler() {
			
			@Override
			public void onLogOutEvent(LogOutEvent autentificareEvent) {
				cpm.setVisible(true);
				cpm.setEnabled(true);
				isLogged = false;
				String string = isLogged ? "Log out" : "Autentificare";
				cpr.setHeadingText(string);
			}
		});

		return panel;
	}

	public boolean isLogged() {
		return isLogged;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}


}
