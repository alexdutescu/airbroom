package ro.alex.browser.events;

import ro.alex.browser.handlers.CreateDusIntorsFormEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class CreateDusIntorsFormEvent extends GwtEvent<CreateDusIntorsFormEventHandler> { 

	public static Type<CreateDusIntorsFormEventHandler> TYPE = new Type<CreateDusIntorsFormEventHandler>(); 
		
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CreateDusIntorsFormEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(CreateDusIntorsFormEventHandler handler) {
		handler.onDusIntorsFormEvent(this);
	}
}