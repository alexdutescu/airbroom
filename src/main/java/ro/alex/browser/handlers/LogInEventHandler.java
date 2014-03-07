package ro.alex.browser.handlers;

import ro.alex.browser.events.LogInEvent;

import com.google.gwt.event.shared.EventHandler;

public interface LogInEventHandler extends EventHandler {

	void onLogInEvent(LogInEvent autentificareEvent);
	
}
