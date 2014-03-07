// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.proxy;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;
import org.springframework.roo.addon.gwt.RooGwtProxy;

@ProxyForName(value = "ro.alex.server.model.Avion", locator = "ro.alex.server.locator.AvionLocator")
@RooGwtProxy(value = "ro.alex.server.model.Avion", readOnly = { "version", "id" }, scaffold = true)
public interface AvionProxy extends EntityProxy {

    abstract Long getId();

    abstract Integer getCapacitate();

    abstract void setCapacitate(Integer capacitate);

    abstract String getTip();

    abstract void setTip(String tip);

    abstract Integer getVersion();
}
