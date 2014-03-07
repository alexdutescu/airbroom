package ro.alex.client.managed.ui;

import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;
import ro.alex.client.proxy.CompanieProxy;

public class CompanieProxyRenderer extends ProxyRenderer<CompanieProxy> {

    private static ro.alex.client.managed.ui.CompanieProxyRenderer INSTANCE;

    protected CompanieProxyRenderer() {
        super(new String[] { "denumire" });
    }

    public static ro.alex.client.managed.ui.CompanieProxyRenderer instance() {
        if (INSTANCE == null) {
            INSTANCE = new CompanieProxyRenderer();
        }
        return INSTANCE;
    }

    public String render(CompanieProxy object) {
        if (object == null) {
            return "";
        }
        return object.getDenumire() + " (" + object.getDenumire() + ")";
    }
}
