package ro.alex.browser.handlers;

import ro.alex.browser.events.DusIntorsEvent;

import com.google.gwt.event.shared.EventHandler;

public interface DusIntorsEventHandler extends EventHandler {

	void onDusIntorsEvent(DusIntorsEvent dusIntorsEvent);
	
}
