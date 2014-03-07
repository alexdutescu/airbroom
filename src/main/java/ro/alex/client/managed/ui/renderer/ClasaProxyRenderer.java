package ro.alex.client.managed.ui.renderer;
import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;
import ro.alex.client.proxy.ClasaProxy;

public class ClasaProxyRenderer extends ProxyRenderer<ClasaProxy> {

    private static ClasaProxyRenderer INSTANCE;

    protected ClasaProxyRenderer() {
        super(new String[] { "clasa" });
    }

    public static ClasaProxyRenderer instance() {
        if (INSTANCE == null) {
            INSTANCE = new ClasaProxyRenderer();
        }
        return INSTANCE;
    }

    public String render(ClasaProxy object) {
        if (object == null) {
            return "";
        }
        return object.getClasa() + " (" + object.getClasa() + ")";
    }
}
