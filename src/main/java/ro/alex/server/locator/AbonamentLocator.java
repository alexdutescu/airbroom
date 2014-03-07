package ro.alex.server.locator;
import com.google.web.bindery.requestfactory.shared.Locator;
import org.springframework.roo.addon.gwt.RooGwtLocator;
import org.springframework.stereotype.Component;
import ro.alex.server.model.Abonament;

@RooGwtLocator("ro.alex.server.model.Abonament")
@Component
public class AbonamentLocator extends Locator<Abonament, Long> {

    public Abonament create(Class<? extends ro.alex.server.model.Abonament> clazz) {
        return new Abonament();
    }

    public Abonament find(Class<? extends ro.alex.server.model.Abonament> clazz, Long id) {
        return Abonament.findAbonament(id);
    }

    public Class<ro.alex.server.model.Abonament> getDomainType() {
        return Abonament.class;
    }

    public Long getId(Abonament abonament) {
        return abonament.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(Abonament abonament) {
        return abonament.getVersion();
    }
}
