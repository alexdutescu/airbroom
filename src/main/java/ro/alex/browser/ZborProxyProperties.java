package ro.alex.browser;

import java.util.Date;

import ro.alex.client.proxy.ZborProxy;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ZborProxyProperties extends PropertyAccess<ZborProxy> {
	ModelKeyProvider<ZborProxy> id();

	ValueProvider<ZborProxy, String> cod();

	@Path(value="orasp.denumire")
	ValueProvider<ZborProxy, String> orasp();

	@Path(value="orasd.denumire")
	ValueProvider<ZborProxy, String> orasd();

	ValueProvider<ZborProxy, Integer> numarKm();

	ValueProvider<ZborProxy, Date> data();
	
	@Path(value = "avion.tip")
	ValueProvider<ZborProxy, String> avion();
	
	ValueProvider<ZborProxy, Boolean> deschis();
	
	ValueProvider<ZborProxy, Boolean> escala();

	//		ValueProvider<ZborProxy, Long> numarLocuri(); 
	//
	//		ValueProvider<ZborProxy, String> plecare();
	//
	//		ValueProvider<ZborProxy, String> destinatie();
	//
	//		ValueProvider<ZborProxy, Boolean> escala();
}