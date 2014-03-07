// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.proxy;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;
import org.springframework.roo.addon.gwt.RooGwtProxy;

@ProxyForName(value = "ro.alex.server.model.Aeroport", locator = "ro.alex.server.locator.AeroportLocator")
@RooGwtProxy(value = "ro.alex.server.model.Aeroport", readOnly = { "version", "id" }, scaffold = true)
public interface AeroportProxy extends EntityProxy {

    abstract Long getId();

    abstract String getDenumire();

    abstract void setDenumire(String denumire);

    abstract Integer getVersion();
}
