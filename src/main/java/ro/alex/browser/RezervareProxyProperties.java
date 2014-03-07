package ro.alex.browser;

import ro.alex.client.proxy.RezervareProxy;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface RezervareProxyProperties extends PropertyAccess<RezervareProxy> {
	ModelKeyProvider<RezervareProxy> id();

	ValueProvider<RezervareProxy, Double> cost();
	
	@Path(value="locDus.numar.toString")
	ValueProvider<RezervareProxy, String> locDus();
	
	@Path(value="locIntors.numar.toString")
	ValueProvider<RezervareProxy, String> locIntors();
	
	@Path(value="zborDus.orasp.denumire.toString")
	ValueProvider<RezervareProxy, String> orasDP();
	
	@Path(value="zborDus.orasd.denumire.toString")
	ValueProvider<RezervareProxy, String> orasDD();
	
//	@Path(value="zborIntors.orasp.denumire.toString")
//	ValueProvider<RezervareProxy, String> orasIP();
//	
//	@Path(value="zborIntors.orasd.denumire.toString")
//	ValueProvider<RezervareProxy, String> orasID();
	
	@Path(value="persoana.nume.toString")
	ValueProvider<RezervareProxy, String> persoanaNume();
	
	@Path(value="persoana.prenume.toString")
	ValueProvider<RezervareProxy, String> persoanaPrenume();
	
	ValueProvider<RezervareProxy, Boolean> dusIntors();
	
	ValueProvider<RezervareProxy, String> nume();
	
	ValueProvider<RezervareProxy, String> prenume();
	
	ValueProvider<RezervareProxy, String> cod();
}
