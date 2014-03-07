package ro.alex.client.managed.ui.renderer;
import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;
import ro.alex.client.proxy.UserProxy;

public class UserProxyRenderer extends ProxyRenderer<UserProxy> {

    private static UserProxyRenderer INSTANCE;

    protected UserProxyRenderer() {
        super(new String[] { "nick" });
    }

    public static UserProxyRenderer instance() {
        if (INSTANCE == null) {
            INSTANCE = new UserProxyRenderer();
        }
        return INSTANCE;
    }

    public String render(UserProxy object) {
        if (object == null) {
            return "";
        }
        return object.getNick() + " (" + object.getNick() + ")";
    }
}
