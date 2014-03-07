package ro.alex.server.locator;
import com.google.web.bindery.requestfactory.shared.Locator;
import org.springframework.roo.addon.gwt.RooGwtLocator;
import org.springframework.stereotype.Component;
import ro.alex.server.model.Companie;

@RooGwtLocator("ro.alex.server.model.Companie")
@Component
public class CompanieLocator extends Locator<Companie, Long> {

    public Companie create(Class<? extends ro.alex.server.model.Companie> clazz) {
        return new Companie();
    }

    public Companie find(Class<? extends ro.alex.server.model.Companie> clazz, Long id) {
        return Companie.findCompanie(id);
    }

    public Class<ro.alex.server.model.Companie> getDomainType() {
        return Companie.class;
    }

    public Long getId(Companie companie) {
        return companie.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(Companie companie) {
        return companie.getVersion();
    }
}
