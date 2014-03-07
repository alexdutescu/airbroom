// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtRequest;

@RooGwtRequest("ro.alex.server.model.Loc")
@ServiceName("ro.alex.server.model.Loc")
public interface LocRequest_Roo_Gwt extends RequestContext {

    abstract Request<java.lang.Long> countLocs();

    abstract Request<java.util.List<ro.alex.client.proxy.LocProxy>> findAllLocs();

    abstract Request<java.util.List<ro.alex.client.proxy.LocProxy>> findLocEntries(int firstResult, int maxResults);

    abstract Request<ro.alex.client.proxy.LocProxy> findLoc(Long id);

    abstract InstanceRequest<ro.alex.client.proxy.LocProxy, java.lang.Void> persist();

    abstract InstanceRequest<ro.alex.client.proxy.LocProxy, java.lang.Void> remove();
}
