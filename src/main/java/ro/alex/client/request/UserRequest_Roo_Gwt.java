// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtRequest;

@RooGwtRequest("ro.alex.server.model.User")
@ServiceName("ro.alex.server.model.User")
public interface UserRequest_Roo_Gwt extends RequestContext {

    abstract Request<java.lang.Long> countUsers();

    abstract Request<java.util.List<ro.alex.client.proxy.UserProxy>> findAllUsers();

    abstract Request<java.util.List<ro.alex.client.proxy.UserProxy>> findUserEntries(int firstResult, int maxResults);

    abstract Request<ro.alex.client.proxy.UserProxy> findUser(Long id);

    abstract InstanceRequest<ro.alex.client.proxy.UserProxy, java.lang.Void> persist();

    abstract InstanceRequest<ro.alex.client.proxy.UserProxy, java.lang.Void> remove();
}
