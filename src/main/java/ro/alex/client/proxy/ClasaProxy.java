// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.proxy;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;
import org.springframework.roo.addon.gwt.RooGwtProxy;

@ProxyForName(value = "ro.alex.server.model.Clasa", locator = "ro.alex.server.locator.ClasaLocator")
@RooGwtProxy(value = "ro.alex.server.model.Clasa", readOnly = { "version", "id" }, scaffold = true)
public interface ClasaProxy extends EntityProxy {

    abstract Long getId();

    abstract String getClasa();

    abstract void setClasa(String clasa);

    abstract Integer getVersion();
}
