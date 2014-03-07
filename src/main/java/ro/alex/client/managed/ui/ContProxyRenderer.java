package ro.alex.client.managed.ui;

import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;
import ro.alex.client.proxy.ContProxy;

public class ContProxyRenderer extends ProxyRenderer<ContProxy> {

    private static ro.alex.client.managed.ui.ContProxyRenderer INSTANCE;

    protected ContProxyRenderer() {
        super(new String[] { "numar" });
    }

    public static ro.alex.client.managed.ui.ContProxyRenderer instance() {
        if (INSTANCE == null) {
            INSTANCE = new ContProxyRenderer();
        }
        return INSTANCE;
    }

    public String render(ContProxy object) {
        if (object == null) {
            return "";
        }
        return object.getNumar() + " (" + object.getNumar() + ")";
    }
}
