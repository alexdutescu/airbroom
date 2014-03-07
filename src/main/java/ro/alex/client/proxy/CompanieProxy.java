// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.proxy;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;
import org.springframework.roo.addon.gwt.RooGwtProxy;

@ProxyForName(value = "ro.alex.server.model.Companie", locator = "ro.alex.server.locator.CompanieLocator")
@RooGwtProxy(value = "ro.alex.server.model.Companie", readOnly = { "version", "id" }, scaffold = true)
public interface CompanieProxy extends EntityProxy {

    abstract Long getId();

    abstract String getDenumire();

    abstract void setDenumire(String denumire);

    abstract Integer getVersion();
}
