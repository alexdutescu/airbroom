package ro.alex.client.managed.ui;

import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;
import ro.alex.client.proxy.LocProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.RezervareProxy;
import ro.alex.client.proxy.ZborProxy;

public class RezervareProxyRenderer extends ProxyRenderer<RezervareProxy> {

    private static ro.alex.client.managed.ui.RezervareProxyRenderer INSTANCE;

    protected RezervareProxyRenderer() {
        super(new String[] { "nume" });
    }

    public static ro.alex.client.managed.ui.RezervareProxyRenderer instance() {
        if (INSTANCE == null) {
            INSTANCE = new RezervareProxyRenderer();
        }
        return INSTANCE;
    }

    public String render(RezervareProxy object) {
        if (object == null) {
            return "";
        }
        return object.getNume() + " (" + object.getNume() + ")";
    }
}
