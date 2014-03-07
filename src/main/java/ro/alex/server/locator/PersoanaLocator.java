package ro.alex.server.locator;
import com.google.web.bindery.requestfactory.shared.Locator;
import org.springframework.roo.addon.gwt.RooGwtLocator;
import org.springframework.stereotype.Component;
import ro.alex.server.model.Persoana;

@RooGwtLocator("ro.alex.server.model.Persoana")
@Component
public class PersoanaLocator extends Locator<Persoana, Long> {

    public Persoana create(Class<? extends ro.alex.server.model.Persoana> clazz) {
        return new Persoana();
    }

    public Persoana find(Class<? extends ro.alex.server.model.Persoana> clazz, Long id) {
        return Persoana.findPersoana(id);
    }

    public Class<ro.alex.server.model.Persoana> getDomainType() {
        return Persoana.class;
    }

    public Long getId(Persoana persoana) {
        return persoana.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(Persoana persoana) {
        return persoana.getVersion();
    }
}
