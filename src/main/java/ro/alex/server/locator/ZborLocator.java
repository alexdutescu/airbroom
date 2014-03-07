package ro.alex.server.locator;
import com.google.web.bindery.requestfactory.shared.Locator;
import org.springframework.roo.addon.gwt.RooGwtLocator;
import org.springframework.stereotype.Component;
import ro.alex.server.model.Zbor;

@RooGwtLocator("ro.alex.server.model.Zbor")
@Component
public class ZborLocator extends Locator<Zbor, Long> {

    public Zbor create(Class<? extends ro.alex.server.model.Zbor> clazz) {
        return new Zbor();
    }

    public Zbor find(Class<? extends ro.alex.server.model.Zbor> clazz, Long id) {
        return Zbor.findZbor(id);
    }

    public Class<ro.alex.server.model.Zbor> getDomainType() {
        return Zbor.class;
    }

    public Long getId(Zbor zbor) {
        return zbor.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(Zbor zbor) {
        return zbor.getVersion();
    }
}
