// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtRequest;

@RooGwtRequest("ro.alex.server.model.Cont")
@ServiceName("ro.alex.server.model.Cont")
public interface ContRequest_Roo_Gwt extends RequestContext {

    abstract Request<java.lang.Long> countConts();

    abstract Request<java.util.List<ro.alex.client.proxy.ContProxy>> findAllConts();

    abstract Request<java.util.List<ro.alex.client.proxy.ContProxy>> findContEntries(int firstResult, int maxResults);

    abstract Request<ro.alex.client.proxy.ContProxy> findCont(Long id);

    abstract InstanceRequest<ro.alex.client.proxy.ContProxy, java.lang.Void> persist();

    abstract InstanceRequest<ro.alex.client.proxy.ContProxy, java.lang.Void> remove();
}
