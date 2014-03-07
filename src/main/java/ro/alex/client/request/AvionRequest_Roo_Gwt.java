// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtRequest;

@RooGwtRequest("ro.alex.server.model.Avion")
@ServiceName("ro.alex.server.model.Avion")
public interface AvionRequest_Roo_Gwt extends RequestContext {

    abstract Request<java.lang.Long> countAvions();

    abstract Request<java.util.List<ro.alex.client.proxy.AvionProxy>> findAllAvions();

    abstract Request<java.util.List<ro.alex.client.proxy.AvionProxy>> findAvionEntries(int firstResult, int maxResults);

    abstract Request<ro.alex.client.proxy.AvionProxy> findAvion(Long id);

    abstract InstanceRequest<ro.alex.client.proxy.AvionProxy, java.lang.Void> persist();

    abstract InstanceRequest<ro.alex.client.proxy.AvionProxy, java.lang.Void> remove();
}
