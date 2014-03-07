package ro.alex.browser.handlers;

import ro.alex.browser.events.DusEvent;

import com.google.gwt.event.shared.EventHandler;

public interface DusEventHandler  extends EventHandler {

	void onDusEvent(DusEvent autentificareEvent);
	
}