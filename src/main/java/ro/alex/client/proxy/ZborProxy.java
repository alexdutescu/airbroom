// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.proxy;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;
import java.util.Date;
import org.springframework.roo.addon.gwt.RooGwtProxy;

@ProxyForName(value = "ro.alex.server.model.Zbor", locator = "ro.alex.server.locator.ZborLocator")
@RooGwtProxy(value = "ro.alex.server.model.Zbor", readOnly = { "version", "id" }, scaffold = true)
public interface ZborProxy extends EntityProxy {

    abstract Long getId();

    abstract AvionProxy getAvion();

    abstract void setAvion(AvionProxy avion);

    abstract String getCod();

    abstract void setCod(String cod);

    abstract Integer getNumarKm();

    abstract void setNumarKm(Integer numarKm);

    abstract Date getData();

    abstract void setData(Date data);

    abstract OrasProxy getOrasp();

    abstract void setOrasp(OrasProxy orasp);

    abstract OrasProxy getOrasd();

    abstract void setOrasd(OrasProxy orasd);

    abstract Boolean getDeschis();

    abstract void setDeschis(Boolean deschis);

    abstract Boolean getEscala();

    abstract void setEscala(Boolean escala);

    abstract Integer getVersion();
}
