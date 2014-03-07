// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtRequest;

@RooGwtRequest("ro.alex.server.model.Abonament")
@ServiceName("ro.alex.server.model.Abonament")
public interface AbonamentRequest_Roo_Gwt extends RequestContext {

    abstract Request<java.lang.Long> countAbonaments();

    abstract Request<java.util.List<ro.alex.client.proxy.AbonamentProxy>> findAllAbonaments();

    abstract Request<java.util.List<ro.alex.client.proxy.AbonamentProxy>> findAbonamentEntries(int firstResult, int maxResults);

    abstract Request<ro.alex.client.proxy.AbonamentProxy> findAbonament(Long id);

    abstract InstanceRequest<ro.alex.client.proxy.AbonamentProxy, java.lang.Void> persist();

    abstract InstanceRequest<ro.alex.client.proxy.AbonamentProxy, java.lang.Void> remove();
}
