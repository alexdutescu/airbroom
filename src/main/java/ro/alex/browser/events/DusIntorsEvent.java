package ro.alex.browser.events;

import ro.alex.browser.handlers.DusIntorsEventHandler;

import com.google.gwt.event.shared.GwtEvent;


public class DusIntorsEvent extends GwtEvent<DusIntorsEventHandler> {

	public static Type<DusIntorsEventHandler> TYPE = new Type<DusIntorsEventHandler>(); 
	
	public int orasp, orasd, oraspi, orasdi;
	public String datap, datai;
	public boolean escala;
	
	
	public int getOraspi() {
		return oraspi;
	}

	public void setOraspi(int oraspi) {
		this.oraspi = oraspi;
	}

	public int getOrasdi() {
		return orasdi;
	}

	public void setOrasdi(int orasdi) {
		this.orasdi = orasdi;
	}

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

	public String getDatai() {
		return datai;
	}

	public void setDatai(String datai) {
		this.datai = datai;
	}

	public boolean isEscala() {
		return escala;
	}

	public void setEscala(boolean escala) {
		this.escala = escala;
	}
	
	@Override
	protected void dispatch(ro.alex.browser.handlers.DusIntorsEventHandler handler) {
		handler.onDusIntorsEvent(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ro.alex.browser.handlers.DusIntorsEventHandler> getAssociatedType() {
		return TYPE;
	}

}
