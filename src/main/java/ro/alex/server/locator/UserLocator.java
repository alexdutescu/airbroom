package ro.alex.server.locator;
import com.google.web.bindery.requestfactory.shared.Locator;
import org.springframework.roo.addon.gwt.RooGwtLocator;
import org.springframework.stereotype.Component;
import ro.alex.server.model.User;

@RooGwtLocator("ro.alex.server.model.User")
@Component
public class UserLocator extends Locator<User, Long> {

    public User create(Class<? extends ro.alex.server.model.User> clazz) {
        return new User();
    }

    public User find(Class<? extends ro.alex.server.model.User> clazz, Long id) {
        return User.findUser(id);
    }

    public Class<ro.alex.server.model.User> getDomainType() {
        return User.class;
    }

    public Long getId(User user) {
        return user.getId();
    }

    public Class<java.lang.Long> getIdType() {
        return Long.class;
    }

    public Object getVersion(User user) {
        return user.getVersion();
    }
}
