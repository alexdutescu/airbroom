package ro.alex.browser.events;

import ro.alex.browser.handlers.RezervaEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class RezervaEvent extends GwtEvent<RezervaEventHandler> {

	public static Type<RezervaEventHandler> TYPE = new Type<RezervaEventHandler>(); 
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<RezervaEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RezervaEventHandler handler) {
		handler.onRezervaEvent(this);
	}

}
