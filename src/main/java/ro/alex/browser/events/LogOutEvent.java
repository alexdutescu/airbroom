package ro.alex.browser.events;

import ro.alex.browser.handlers.LogOutEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class LogOutEvent  extends GwtEvent<LogOutEventHandler> {

	public static Type<LogOutEventHandler> TYPE = new Type<LogOutEventHandler>(); 
	
	
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LogOutEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(LogOutEventHandler handler) {
		handler.onLogOutEvent(this);
	}

}
