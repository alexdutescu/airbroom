package ro.alex.browser.handlers;

import ro.alex.browser.events.RezervaEvent;

import com.google.gwt.event.shared.EventHandler;

public interface RezervaEventHandler extends EventHandler {

	void onRezervaEvent(RezervaEvent rezervaEvent);
	
}