package ro.alex.server.locator;
import com.google.web.bindery.requestfactory.shared.Locator;
import org.springframework.roo.addon.gwt.RooGwtLocator;
import org.springframework.stereotype.Component;
import ro.alex.server.model.Aeroport;

@RooGwtLocator("ro.alex.server.model.Aeroport")
@Component
public class AeroportLocator extends Locator<Aeroport, Long> {

    public Aeroport create(Class<? extends ro.alex.server.model.Aeroport> clazz) {
        return new Aeroport();
    }

    public Aeroport find(Class<? extends ro.alex.server.model.Aeroport> clazz, Long id) {
        return Aeroport.findAeroport(id);
    }

    public Class<ro.alex.server.model.Aeroport> getDomainType() {
        return Aeroport.class;
    }

    public Long getId(Aeroport aeroport) {
        return aeroport.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(Aeroport aeroport) {
        return aeroport.getVersion();
    }
}
