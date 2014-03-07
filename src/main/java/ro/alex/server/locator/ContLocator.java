package ro.alex.server.locator;
import com.google.web.bindery.requestfactory.shared.Locator;
import org.springframework.roo.addon.gwt.RooGwtLocator;
import org.springframework.stereotype.Component;
import ro.alex.server.model.Cont;

@RooGwtLocator("ro.alex.server.model.Cont")
@Component
public class ContLocator extends Locator<Cont, Long> {

    public Cont create(Class<? extends ro.alex.server.model.Cont> clazz) {
        return new Cont();
    }

    public Cont find(Class<? extends ro.alex.server.model.Cont> clazz, Long id) {
        return Cont.findCont(id);
    }

    public Class<ro.alex.server.model.Cont> getDomainType() {
        return Cont.class;
    }

    public Long getId(Cont cont) {
        return cont.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(Cont cont) {
        return cont.getVersion();
    }
}
