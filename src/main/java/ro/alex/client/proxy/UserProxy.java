// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.proxy;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;
import org.springframework.roo.addon.gwt.RooGwtProxy;

@ProxyForName(value = "ro.alex.server.model.User", locator = "ro.alex.server.locator.UserLocator")
@RooGwtProxy(value = "ro.alex.server.model.User", readOnly = { "version", "id" }, scaffold = true)
public interface UserProxy extends EntityProxy {

    abstract Long getId();

    abstract String getNick();

    abstract void setNick(String nick);

    abstract String getParola();

    abstract void setParola(String parola);

    abstract Integer getVersion();
}
