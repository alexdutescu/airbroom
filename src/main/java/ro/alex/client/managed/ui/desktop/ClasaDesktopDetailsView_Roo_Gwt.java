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
import ro.alex.client.managed.ui.ClasaDetailsView;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.scaffold.place.ProxyListView;

public abstract class ClasaDesktopDetailsView_Roo_Gwt extends Composite implements ClasaDetailsView {

    @UiField
    SpanElement id;

    @UiField
    SpanElement clasa;

    @UiField
    SpanElement version;

    ClasaProxy proxy;

    @UiField
    SpanElement displayRenderer;

    public void setValue(ClasaProxy proxy) {
        this.proxy = proxy;
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        clasa.setInnerText(proxy.getClasa() == null ? "" : String.valueOf(proxy.getClasa()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        displayRenderer.setInnerText(ro.alex.client.managed.ui.renderer.ClasaProxyRenderer.instance().render(proxy));
    }
}