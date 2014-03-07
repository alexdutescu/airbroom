// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtRequest;

@RooGwtRequest("ro.alex.server.model.Rezervare")
@ServiceName("ro.alex.server.model.Rezervare")
public interface RezervareRequest_Roo_Gwt extends RequestContext {

    abstract Request<java.lang.Long> countRezervares();

    abstract Request<java.util.List<ro.alex.client.proxy.RezervareProxy>> findAllRezervares();

    abstract Request<java.util.List<ro.alex.client.proxy.RezervareProxy>> findRezervareEntries(int firstResult, int maxResults);

    abstract Request<ro.alex.client.proxy.RezervareProxy> findRezervare(Long id);

    abstract InstanceRequest<ro.alex.client.proxy.RezervareProxy, java.lang.Void> persist();

    abstract InstanceRequest<ro.alex.client.proxy.RezervareProxy, java.lang.Void> remove();
}
