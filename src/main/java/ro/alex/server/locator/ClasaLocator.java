package ro.alex.server.locator;
import com.google.web.bindery.requestfactory.shared.Locator;
import org.springframework.roo.addon.gwt.RooGwtLocator;
import org.springframework.stereotype.Component;
import ro.alex.server.model.Clasa;

@RooGwtLocator("ro.alex.server.model.Clasa")
@Component
public class ClasaLocator extends Locator<Clasa, Long> {

    public Clasa create(Class<? extends ro.alex.server.model.Clasa> clazz) {
        return new Clasa();
    }

    public Clasa find(Class<? extends ro.alex.server.model.Clasa> clazz, Long id) {
        return Clasa.findClasa(id);
    }

    public Class<ro.alex.server.model.Clasa> getDomainType() {
        return Clasa.class;
    }

    public Long getId(Clasa clasa) {
        return clasa.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(Clasa clasa) {
        return clasa.getVersion();
    }
}
