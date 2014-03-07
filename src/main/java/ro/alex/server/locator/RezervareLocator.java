package ro.alex.server.locator;
import com.google.web.bindery.requestfactory.shared.Locator;
import org.springframework.roo.addon.gwt.RooGwtLocator;
import org.springframework.stereotype.Component;
import ro.alex.server.model.Rezervare;

@RooGwtLocator("ro.alex.server.model.Rezervare")
@Component
public class RezervareLocator extends Locator<Rezervare, Long> {

    public Rezervare create(Class<? extends ro.alex.server.model.Rezervare> clazz) {
        return new Rezervare();
    }

    public Rezervare find(Class<? extends ro.alex.server.model.Rezervare> clazz, Long id) {
        return Rezervare.findRezervare(id);
    }

    public Class<ro.alex.server.model.Rezervare> getDomainType() {
        return Rezervare.class;
    }

    public Long getId(Rezervare rezervare) {
        return rezervare.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(Rezervare rezervare) {
        return rezervare.getVersion();
    }
}
