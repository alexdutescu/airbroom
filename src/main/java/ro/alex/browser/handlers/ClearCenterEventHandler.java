package ro.alex.browser.handlers;

import ro.alex.browser.events.ClearCenterEvent;

import com.google.gwt.event.shared.EventHandler;

public interface ClearCenterEventHandler extends EventHandler {
	
	void onClearEvent(ClearCenterEvent event);
}
