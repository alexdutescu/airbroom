// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.proxy;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;
import org.springframework.roo.addon.gwt.RooGwtProxy;

@ProxyForName(value = "ro.alex.server.model.Loc", locator = "ro.alex.server.locator.LocLocator")
@RooGwtProxy(value = "ro.alex.server.model.Loc", readOnly = { "version", "id" }, scaffold = true)
public interface LocProxy extends EntityProxy {

    abstract Long getId();

    abstract Integer getNumar();

    abstract void setNumar(Integer numar);

    abstract ClasaProxy getClasa();

    abstract void setClasa(ClasaProxy clasa);

    abstract AvionProxy getAvion();

    abstract void setAvion(AvionProxy avion);

    abstract ZborProxy getZbor();

    abstract void setZbor(ZborProxy zbor);

    abstract Boolean getOcupat();

    abstract void setOcupat(Boolean ocupat);

    abstract Integer getVersion();
}
