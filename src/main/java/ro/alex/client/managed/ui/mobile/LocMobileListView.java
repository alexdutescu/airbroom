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
import java.util.HashSet;
import java.util.Set;
import ro.alex.client.managed.ui.LocMobileListView_Roo_Gwt;
import ro.alex.client.managed.ui.Override;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.proxy.LocProxy;
import ro.alex.client.proxy.ZborProxy;
import ro.alex.client.scaffold.ScaffoldMobileApp;
import ro.alex.client.scaffold.ui.MobileProxyListView;

public class LocMobileListView extends LocMobileListView_Roo_Gwt {

    private static ro.alex.client.managed.ui.LocMobileListView instance;

    public LocMobileListView() {
        super("New Loc", new CellRenderer());
        init();
    }

    public static ro.alex.client.managed.ui.LocMobileListView instance() {
        if (instance == null) {
            instance = new LocMobileListView();
        }
        return instance;
    }

    public String[] getPaths() {
        return paths.toArray(new String[paths.size()]);
    }

    private static class CellRenderer extends AbstractSafeHtmlRenderer<LocProxy> {

        private final String dateStyle = ScaffoldMobileApp.getMobileListResources().cellListStyle().dateProp();

        private final String secondaryStyle = ScaffoldMobileApp.getMobileListResources().cellListStyle().secondaryProp();

        private final Renderer<Long> primaryRenderer = new AbstractRenderer<Long>() {

            public String render(java.lang.Long obj) {
                return obj == null ? "" : String.valueOf(obj);
            }
        };

        private final Renderer<Integer> secondaryRenderer = new AbstractRenderer<Integer>() {

            public String render(java.lang.Integer obj) {
                return obj == null ? "" : String.valueOf(obj);
            }
        };

        @Override
        public SafeHtml render(LocProxy value) {
            if (value == null) {
                return SafeHtmlUtils.EMPTY_SAFE_HTML;
            }
            SafeHtmlBuilder sb = new SafeHtmlBuilder();
            if (value.getId() != null) {
                sb.appendEscaped(primaryRenderer.render(value.getId()));
            }
            sb.appendHtmlConstant("<div style=\"position:relative;\">");
            sb.appendHtmlConstant("<div class=\"" + secondaryStyle + "\">");
            if (value.getNumar() != null) {
                sb.appendEscaped(secondaryRenderer.render(value.getNumar()));
            }
            sb.appendHtmlConstant("</div>");
            sb.appendHtmlConstant("<div class=\"" + dateStyle + "\">");
            sb.appendHtmlConstant("</div>");
            sb.appendHtmlConstant("</div>");
            return sb.toSafeHtml();
        }
    }
}
