package ro.alex.browser.handlers;

import ro.alex.browser.events.LogOutEvent;

import com.google.gwt.event.shared.EventHandler;

public interface LogOutEventHandler extends EventHandler {

	void onLogOutEvent(LogOutEvent autentificareEvent);
	
}
