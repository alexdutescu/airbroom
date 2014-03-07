package ro.alex.client.managed.ui.renderer;
import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;
import ro.alex.client.proxy.OrasProxy;

public class OrasProxyRenderer extends ProxyRenderer<OrasProxy> {

    private static OrasProxyRenderer INSTANCE;

    protected OrasProxyRenderer() {
        super(new String[] { "denumire" });
    }

    public static OrasProxyRenderer instance() {
        if (INSTANCE == null) {
            INSTANCE = new OrasProxyRenderer();
        }
        return INSTANCE;
    }

    public String render(OrasProxy object) {
        if (object == null) {
            return "";
        }
        return object.getDenumire() + " (" + object.getDenumire() + ")";
    }
}
