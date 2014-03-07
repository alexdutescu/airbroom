package ro.alex.client.managed.ui;

import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;
import ro.alex.client.proxy.AbonamentProxy;

public class AbonamentProxyRenderer extends ProxyRenderer<AbonamentProxy> {

    private static ro.alex.client.managed.ui.AbonamentProxyRenderer INSTANCE;

    protected AbonamentProxyRenderer() {
        super(new String[] { "serie" });
    }

    public static ro.alex.client.managed.ui.AbonamentProxyRenderer instance() {
        if (INSTANCE == null) {
            INSTANCE = new AbonamentProxyRenderer();
        }
        return INSTANCE;
    }

    public String render(AbonamentProxy object) {
        if (object == null) {
            return "";
        }
        return object.getSerie() + " (" + object.getSerie() + ")";
    }
}
