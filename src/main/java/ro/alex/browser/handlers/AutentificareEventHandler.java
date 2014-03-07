package ro.alex.browser.handlers;

import ro.alex.browser.events.AutentificareEvent;

import com.google.gwt.event.shared.EventHandler;

public interface AutentificareEventHandler extends EventHandler {

	void onAutentificareEvent(AutentificareEvent autentificareEvent);
}
