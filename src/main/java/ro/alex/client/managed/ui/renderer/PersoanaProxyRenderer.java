package ro.alex.client.managed.ui.renderer;
import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.UserProxy;

public class PersoanaProxyRenderer extends ProxyRenderer<PersoanaProxy> {

    private static PersoanaProxyRenderer INSTANCE;

    protected PersoanaProxyRenderer() {
        super(new String[] { "nume" });
    }

    public static PersoanaProxyRenderer instance() {
        if (INSTANCE == null) {
            INSTANCE = new PersoanaProxyRenderer();
        }
        return INSTANCE;
    }

    public String render(PersoanaProxy object) {
        if (object == null) {
            return "";
        }
        return object.getNume() + " (" + object.getNume() + ")";
    }
}
