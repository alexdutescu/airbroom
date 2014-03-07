// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtRequest;

@RooGwtRequest("ro.alex.server.model.Companie")
@ServiceName("ro.alex.server.model.Companie")
public interface CompanieRequest_Roo_Gwt extends RequestContext {

    abstract Request<java.lang.Long> countCompanies();

    abstract Request<java.util.List<ro.alex.client.proxy.CompanieProxy>> findAllCompanies();

    abstract Request<java.util.List<ro.alex.client.proxy.CompanieProxy>> findCompanieEntries(int firstResult, int maxResults);

    abstract Request<ro.alex.client.proxy.CompanieProxy> findCompanie(Long id);

    abstract InstanceRequest<ro.alex.client.proxy.CompanieProxy, java.lang.Void> persist();

    abstract InstanceRequest<ro.alex.client.proxy.CompanieProxy, java.lang.Void> remove();
}
