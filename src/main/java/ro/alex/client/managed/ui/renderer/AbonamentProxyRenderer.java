package ro.alex.client.managed.ui.renderer;
import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;
import ro.alex.client.proxy.AbonamentProxy;

public class AbonamentProxyRenderer extends ProxyRenderer<AbonamentProxy> {

    private static AbonamentProxyRenderer INSTANCE;

    protected AbonamentProxyRenderer() {
        super(new String[] { "serie" });
    }

    public static AbonamentProxyRenderer instance() {
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
