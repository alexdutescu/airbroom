// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.proxy;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;
import org.springframework.roo.addon.gwt.RooGwtProxy;

@ProxyForName(value = "ro.alex.server.model.Cont", locator = "ro.alex.server.locator.ContLocator")
@RooGwtProxy(value = "ro.alex.server.model.Cont", readOnly = { "version", "id" }, scaffold = true)
public interface ContProxy extends EntityProxy {

    abstract Long getId();

    abstract String getNumar();

    abstract void setNumar(String numar);

    abstract Double getSuma();

    abstract void setSuma(Double suma);

    abstract Integer getVersion();
}
