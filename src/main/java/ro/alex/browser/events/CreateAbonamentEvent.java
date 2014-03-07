package ro.alex.browser.events;

import ro.alex.browser.handlers.CreateAbonamentEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class CreateAbonamentEvent extends GwtEvent<CreateAbonamentEventHandler> {

	public static Type<CreateAbonamentEventHandler> TYPE = new Type<CreateAbonamentEventHandler>(); 
	
	private boolean salvat;
	
	public boolean isSalvat() {
		return salvat;
	}

	public CreateAbonamentEvent(boolean salvat){
		this.salvat = salvat;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CreateAbonamentEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreateAbonamentEventHandler handler) {
		handler.onCreateAbonamentEvent(this);
	}

}
