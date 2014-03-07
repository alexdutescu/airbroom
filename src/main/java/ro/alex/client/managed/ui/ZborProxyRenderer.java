package ro.alex.client.managed.ui;

import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.proxy.OrasProxy;
import ro.alex.client.proxy.ZborProxy;

public class ZborProxyRenderer extends ProxyRenderer<ZborProxy> {

    private static ro.alex.client.managed.ui.ZborProxyRenderer INSTANCE;

    protected ZborProxyRenderer() {
        super(new String[] { "cod" });
    }

    public static ro.alex.client.managed.ui.ZborProxyRenderer instance() {
        if (INSTANCE == null) {
            INSTANCE = new ZborProxyRenderer();
        }
        return INSTANCE;
    }

    public String render(ZborProxy object) {
        if (object == null) {
            return "";
        }
        return object.getCod() + " (" + object.getCod() + ")";
    }
}
