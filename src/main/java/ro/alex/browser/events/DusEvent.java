package ro.alex.browser.events;

import ro.alex.browser.handlers.DusEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class DusEvent extends GwtEvent<DusEventHandler> { 

	public static Type<DusEventHandler> TYPE = new Type<DusEventHandler>(); 
	
	public int orasp, orasd;
	public String datap;
	public boolean escala;
	
	public int getOrasp() {
		return orasp;
	}

	public void setOrasp(int orasp) {
		this.orasp = orasp;
	}

	public int getOrasd() {
		return orasd;
	}

	public void setOrasd(int orasd) {
		this.orasd = orasd;
	}

	public String getDatap() {
		return datap;
	}

	public void setDatap(String datap) {
		this.datap = datap;
	}

	public boolean isEscala() {
		return escala;
	}

	public void setEscala(boolean escala) {
		this.escala = escala;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DusEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(DusEventHandler handler) {
		handler.onDusEvent(this);
	}
}
