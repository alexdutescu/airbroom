package ro.alex.browser.events;

import ro.alex.browser.handlers.ClearCenterEventHandler;

import com.google.gwt.event.shared.GwtEvent;


public class ClearCenterEvent extends GwtEvent<ClearCenterEventHandler> {

	public static Type<ClearCenterEventHandler> TYPE = new Type<ClearCenterEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ClearCenterEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ClearCenterEventHandler handler) {
		handler.onClearEvent(this);
	} 
 
}
