package ro.alex.server.locator;
import com.google.web.bindery.requestfactory.shared.Locator;
import org.springframework.roo.addon.gwt.RooGwtLocator;
import org.springframework.stereotype.Component;
import ro.alex.server.model.Oras;

@RooGwtLocator("ro.alex.server.model.Oras")
@Component
public class OrasLocator extends Locator<Oras, Long> {

    public Oras create(Class<? extends ro.alex.server.model.Oras> clazz) {
        return new Oras();
    }

    public Oras find(Class<? extends ro.alex.server.model.Oras> clazz, Long id) {
        return Oras.findOras(id);
    }

    public Class<ro.alex.server.model.Oras> getDomainType() {
        return Oras.class;
    }

    public Long getId(Oras oras) {
        return oras.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(Oras oras) {
        return oras.getVersion();
    }
}
