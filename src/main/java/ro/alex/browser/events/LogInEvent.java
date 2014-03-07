package ro.alex.browser.events;

import ro.alex.browser.handlers.LogInEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class LogInEvent extends GwtEvent<LogInEventHandler> {

	public static Type<LogInEventHandler> TYPE = new Type<LogInEventHandler>(); 
	
	
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LogInEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(LogInEventHandler handler) {
		handler.onLogInEvent(this);
	}

}
