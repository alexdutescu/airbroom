// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.proxy;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;
import org.springframework.roo.addon.gwt.RooGwtProxy;

@ProxyForName(value = "ro.alex.server.model.Rezervare", locator = "ro.alex.server.locator.RezervareLocator")
@RooGwtProxy(value = "ro.alex.server.model.Rezervare", readOnly = { "version", "id" }, scaffold = true)
public interface RezervareProxy extends EntityProxy {

    abstract Long getId();

    abstract Double getCost();

    abstract void setCost(Double cost);

    abstract LocProxy getLocDus();

    abstract void setLocDus(LocProxy locDus);

    abstract LocProxy getLocIntors();

    abstract void setLocIntors(LocProxy locIntors);

    abstract String getNume();

    abstract void setNume(String nume);

    abstract String getPrenume();

    abstract void setPrenume(String prenume);

    abstract String getCod();

    abstract void setCod(String cod);

    abstract ZborProxy getZborDus();

    abstract void setZborDus(ZborProxy zborDus);

    abstract ZborProxy getZborIntors();

    abstract void setZborIntors(ZborProxy zborIntors);

    abstract PersoanaProxy getPersoana();

    abstract void setPersoana(PersoanaProxy persoana);

    abstract Boolean getDusIntors();

    abstract void setDusIntors(Boolean dusIntors);

    abstract Integer getVersion();
}
