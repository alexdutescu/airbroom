package ro.alex.client.managed.ui;

import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;
import ro.alex.client.proxy.UserProxy;

public class UserProxyRenderer extends ProxyRenderer<UserProxy> {

    private static ro.alex.client.managed.ui.UserProxyRenderer INSTANCE;

    protected UserProxyRenderer() {
        super(new String[] { "nick" });
    }

    public static ro.alex.client.managed.ui.UserProxyRenderer instance() {
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
