package ro.alex.browser.handlers;

import ro.alex.browser.events.CreateUserEvent;

import com.google.gwt.event.shared.EventHandler;

public interface CreateUserEventHandler extends EventHandler {

	void onCreateUserEvent(CreateUserEvent createUserEvent);
	
}
