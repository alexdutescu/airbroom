// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.managed.ui.mobile;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import java.util.HashSet;
import java.util.Set;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.scaffold.ScaffoldMobileApp;
import ro.alex.client.scaffold.ui.MobileProxyListView;

public abstract class ClasaMobileListView_Roo_Gwt extends MobileProxyListView<ClasaProxy> {

    protected Set<String> paths = new HashSet<String>();

    public ClasaMobileListView_Roo_Gwt(String buttonText, SafeHtmlRenderer<ClasaProxy> renderer) {
        super(buttonText, renderer);
    }

    public void init() {
        paths.add("id");
    }
}
