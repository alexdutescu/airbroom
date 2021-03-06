// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.managed.ui.desktop;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import ro.alex.client.managed.ui.AvionDetailsView;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.scaffold.place.ProxyListView;

public abstract class AvionDesktopDetailsView_Roo_Gwt extends Composite implements AvionDetailsView {

    @UiField
    SpanElement id;

    @UiField
    SpanElement capacitate;

    @UiField
    SpanElement tip;

    @UiField
    SpanElement version;

    AvionProxy proxy;

    @UiField
    SpanElement displayRenderer;

    public void setValue(AvionProxy proxy) {
        this.proxy = proxy;
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        capacitate.setInnerText(proxy.getCapacitate() == null ? "" : String.valueOf(proxy.getCapacitate()));
        tip.setInnerText(proxy.getTip() == null ? "" : String.valueOf(proxy.getTip()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        displayRenderer.setInnerText(ro.alex.client.managed.ui.renderer.AvionProxyRenderer.instance().render(proxy));
    }
}
