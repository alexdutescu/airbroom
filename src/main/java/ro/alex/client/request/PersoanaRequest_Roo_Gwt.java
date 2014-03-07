// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtRequest;

@RooGwtRequest("ro.alex.server.model.Persoana")
@ServiceName("ro.alex.server.model.Persoana")
public interface PersoanaRequest_Roo_Gwt extends RequestContext {

    abstract Request<java.lang.Long> countPersoanas();

    abstract Request<java.util.List<ro.alex.client.proxy.PersoanaProxy>> findAllPersoanas();

    abstract Request<java.util.List<ro.alex.client.proxy.PersoanaProxy>> findPersoanaEntries(int firstResult, int maxResults);

    abstract Request<ro.alex.client.proxy.PersoanaProxy> findPersoana(Long id);

    abstract InstanceRequest<ro.alex.client.proxy.PersoanaProxy, java.lang.Void> persist();

    abstract InstanceRequest<ro.alex.client.proxy.PersoanaProxy, java.lang.Void> remove();
}
