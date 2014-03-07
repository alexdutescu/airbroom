// WARNING: THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.proxy;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;
import org.springframework.roo.addon.gwt.RooGwtProxy;

@ProxyForName(value = "ro.alex.server.model.Persoana", locator = "ro.alex.server.locator.PersoanaLocator")
@RooGwtProxy(value = "ro.alex.server.model.Persoana", readOnly = { "version", "id" }, scaffold = true)
public interface PersoanaProxy extends EntityProxy {

    abstract Long getId();

    abstract String getNume();

    abstract void setNume(String nume);

    abstract String getPrenume();

    abstract void setPrenume(String prenume);

    abstract String getAdresa();

    abstract void setAdresa(String adresa);

    abstract UserProxy getUser();

    abstract void setUser(UserProxy user);

    abstract AbonamentProxy getAbonament();

    abstract void setAbonament(AbonamentProxy abonament);

    abstract Integer getVersion();
}
