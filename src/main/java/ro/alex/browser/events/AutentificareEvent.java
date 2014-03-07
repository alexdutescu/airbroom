package ro.alex.browser.events;

import ro.alex.browser.handlers.AutentificareEventHandler;
import ro.alex.client.proxy.PersoanaProxy;

import com.google.gwt.event.shared.GwtEvent;

public class AutentificareEvent extends GwtEvent<AutentificareEventHandler> {

	public static Type<AutentificareEventHandler> TYPE = new Type<AutentificareEventHandler>(); 
	
	private PersoanaProxy pp;
	
	public AutentificareEvent(PersoanaProxy pp){
		this.pp = pp;
	}
	
	public AutentificareEvent(){}
	
	public PersoanaProxy getPersoana(){
		return pp;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AutentificareEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(AutentificareEventHandler handler) {
		handler.onAutentificareEvent(this);
	}

}
