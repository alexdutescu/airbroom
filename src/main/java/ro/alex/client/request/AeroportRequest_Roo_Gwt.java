// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtRequest;

@RooGwtRequest("ro.alex.server.model.Aeroport")
@ServiceName("ro.alex.server.model.Aeroport")
public interface AeroportRequest_Roo_Gwt extends RequestContext {

    abstract Request<java.lang.Long> countAeroports();

    abstract Request<java.util.List<ro.alex.client.proxy.AeroportProxy>> findAllAeroports();

    abstract Request<java.util.List<ro.alex.client.proxy.AeroportProxy>> findAeroportEntries(int firstResult, int maxResults);

    abstract Request<ro.alex.client.proxy.AeroportProxy> findAeroport(Long id);

    abstract InstanceRequest<ro.alex.client.proxy.AeroportProxy, java.lang.Void> persist();

    abstract InstanceRequest<ro.alex.client.proxy.AeroportProxy, java.lang.Void> remove();
}
