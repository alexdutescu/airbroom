package ro.alex.client.managed.ui.renderer;
import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.proxy.LocProxy;
import ro.alex.client.proxy.ZborProxy;

public class LocProxyRenderer extends ProxyRenderer<LocProxy> {

    private static LocProxyRenderer INSTANCE;

    protected LocProxyRenderer() {
        super(new String[] { "id" });
    }

    public static LocProxyRenderer instance() {
        if (INSTANCE == null) {
            INSTANCE = new LocProxyRenderer();
        }
        return INSTANCE;
    }

    public String render(LocProxy object) {
        if (object == null) {
            return "";
        }
        return object.getId() + " (" + object.getId() + ")";
    }
}
