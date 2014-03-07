// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.proxy;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;
import org.springframework.roo.addon.gwt.RooGwtProxy;

@ProxyForName(value = "ro.alex.server.model.Abonament", locator = "ro.alex.server.locator.AbonamentLocator")
@RooGwtProxy(value = "ro.alex.server.model.Abonament", readOnly = { "version", "id" }, scaffold = true)
public interface AbonamentProxy extends EntityProxy {

    abstract Long getId();

    abstract String getSerie();

    abstract void setSerie(String serie);

    abstract Integer getKilometri();

    abstract void setKilometri(Integer kilometri);

    abstract Double getCost();

    abstract void setCost(Double cost);

    abstract Integer getVersion();
}
