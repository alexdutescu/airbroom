package ro.alex.client.managed.ui.renderer;
import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;
import ro.alex.client.proxy.AvionProxy;

public class AvionProxyRenderer extends ProxyRenderer<AvionProxy> {

    private static AvionProxyRenderer INSTANCE;

    protected AvionProxyRenderer() {
        super(new String[] { "tip" });
    }

    public static AvionProxyRenderer instance() {
        if (INSTANCE == null) {
            INSTANCE = new AvionProxyRenderer();
        }
        return INSTANCE;
    }

    public String render(AvionProxy object) {
        if (object == null) {
            return "";
        }
        return object.getTip() + " (" + object.getTip() + ")";
    }
}
