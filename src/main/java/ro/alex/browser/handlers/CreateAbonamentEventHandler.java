package ro.alex.browser.handlers;

import ro.alex.browser.events.CreateAbonamentEvent;

import com.google.gwt.event.shared.EventHandler;

public interface CreateAbonamentEventHandler extends EventHandler {

	void onCreateAbonamentEvent(CreateAbonamentEvent event);
}
