// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtRequest;

@RooGwtRequest("ro.alex.server.model.Clasa")
@ServiceName("ro.alex.server.model.Clasa")
public interface ClasaRequest_Roo_Gwt extends RequestContext {

    abstract Request<java.lang.Long> countClasas();

    abstract Request<java.util.List<ro.alex.client.proxy.ClasaProxy>> findAllClasas();

    abstract Request<java.util.List<ro.alex.client.proxy.ClasaProxy>> findClasaEntries(int firstResult, int maxResults);

    abstract Request<ro.alex.client.proxy.ClasaProxy> findClasa(Long id);

    abstract InstanceRequest<ro.alex.client.proxy.ClasaProxy, java.lang.Void> persist();

    abstract InstanceRequest<ro.alex.client.proxy.ClasaProxy, java.lang.Void> remove();
}
