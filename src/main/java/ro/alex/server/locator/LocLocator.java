package ro.alex.server.locator;
import com.google.web.bindery.requestfactory.shared.Locator;
import org.springframework.roo.addon.gwt.RooGwtLocator;
import org.springframework.stereotype.Component;
import ro.alex.server.model.Loc;

@RooGwtLocator("ro.alex.server.model.Loc")
@Component
public class LocLocator extends Locator<Loc, Long> {

    public Loc create(Class<? extends ro.alex.server.model.Loc> clazz) {
        return new Loc();
    }

    public Loc find(Class<? extends ro.alex.server.model.Loc> clazz, Long id) {
        return Loc.findLoc(id);
    }

    public Class<ro.alex.server.model.Loc> getDomainType() {
        return Loc.class;
    }

    public Long getId(Loc loc) {
        return loc.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(Loc loc) {
        return loc.getVersion();
    }
}
