package ro.alex.browser.events;

import ro.alex.browser.handlers.CreateUserEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class CreateUserEvent extends GwtEvent<CreateUserEventHandler> {

	public static Type<CreateUserEventHandler> TYPE = new Type<CreateUserEventHandler>(); 
	
	
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CreateUserEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(CreateUserEventHandler handler) {
		handler.onCreateUserEvent(this);
	}

}
