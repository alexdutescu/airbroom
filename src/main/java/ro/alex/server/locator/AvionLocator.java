package ro.alex.server.locator;
import com.google.web.bindery.requestfactory.shared.Locator;
import org.springframework.roo.addon.gwt.RooGwtLocator;
import org.springframework.stereotype.Component;
import ro.alex.server.model.Avion;

@RooGwtLocator("ro.alex.server.model.Avion")
@Component
public class AvionLocator extends Locator<Avion, Long> {

    public Avion create(Class<? extends ro.alex.server.model.Avion> clazz) {
        return new Avion();
    }

    public Avion find(Class<? extends ro.alex.server.model.Avion> clazz, Long id) {
        return Avion.findAvion(id);
    }

    public Class<ro.alex.server.model.Avion> getDomainType() {
        return Avion.class;
    }

    public Long getId(Avion avion) {
        return avion.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(Avion avion) {
        return avion.getVersion();
    }
}
