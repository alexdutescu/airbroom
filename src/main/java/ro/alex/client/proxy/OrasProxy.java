// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.proxy;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;
import org.springframework.roo.addon.gwt.RooGwtProxy;

@ProxyForName(value = "ro.alex.server.model.Oras", locator = "ro.alex.server.locator.OrasLocator")
@RooGwtProxy(value = "ro.alex.server.model.Oras", readOnly = { "version", "id" }, scaffold = true)
public interface OrasProxy extends EntityProxy {

    abstract Long getId();

    abstract String getDenumire();

    abstract void setDenumire(String denumire);

    abstract Integer getVersion();
}
