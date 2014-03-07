// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtRequest;

@RooGwtRequest("ro.alex.server.model.Oras")
@ServiceName("ro.alex.server.model.Oras")
public interface OrasRequest_Roo_Gwt extends RequestContext {

    abstract Request<java.lang.Long> countOrases();

    abstract Request<java.util.List<ro.alex.client.proxy.OrasProxy>> findAllOrases();

    abstract Request<java.util.List<ro.alex.client.proxy.OrasProxy>> findOrasEntries(int firstResult, int maxResults);

    abstract Request<ro.alex.client.proxy.OrasProxy> findOras(Long id);

    abstract InstanceRequest<ro.alex.client.proxy.OrasProxy, java.lang.Void> persist();

    abstract InstanceRequest<ro.alex.client.proxy.OrasProxy, java.lang.Void> remove();
}
