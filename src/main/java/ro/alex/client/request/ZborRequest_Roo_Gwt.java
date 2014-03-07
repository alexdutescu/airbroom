// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtRequest;

@RooGwtRequest("ro.alex.server.model.Zbor")
@ServiceName("ro.alex.server.model.Zbor")
public interface ZborRequest_Roo_Gwt extends RequestContext {

    abstract Request<java.lang.Long> countZbors();

    abstract Request<java.util.List<ro.alex.client.proxy.ZborProxy>> findAllZbors();

    abstract Request<java.util.List<ro.alex.client.proxy.ZborProxy>> findZborEntries(int firstResult, int maxResults);

    abstract Request<ro.alex.client.proxy.ZborProxy> findZbor(Long id);

    abstract InstanceRequest<ro.alex.client.proxy.ZborProxy, java.lang.Void> persist();

    abstract InstanceRequest<ro.alex.client.proxy.ZborProxy, java.lang.Void> remove();
}
