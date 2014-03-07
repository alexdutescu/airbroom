package ro.alex.client.managed.ui.renderer;
import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;
import ro.alex.client.proxy.AeroportProxy;

public class AeroportProxyRenderer extends ProxyRenderer<AeroportProxy> {

    private static AeroportProxyRenderer INSTANCE;

    protected AeroportProxyRenderer() {
        super(new String[] { "denumire" });
    }

    public static AeroportProxyRenderer instance() {
        if (INSTANCE == null) {
            INSTANCE = new AeroportProxyRenderer();
        }
        return INSTANCE;
    }

    public String render(AeroportProxy object) {
        if (object == null) {
            return "";
        }
        return object.getDenumire() + " (" + object.getDenumire() + ")";
    }
}
