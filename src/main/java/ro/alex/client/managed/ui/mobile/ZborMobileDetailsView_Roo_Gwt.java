// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.managed.ui.mobile;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
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
import ro.alex.client.managed.ui.ZborDetailsView;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.proxy.OrasProxy;
import ro.alex.client.proxy.ZborProxy;

public abstract class ZborMobileDetailsView_Roo_Gwt extends Composite implements ZborDetailsView {

    @UiField
    Element id;

    @UiField
    Element avion;

    @UiField
    Element cod;

    @UiField
    Element numarKm;

    @UiField
    Element data;

    @UiField
    Element orasp;

    @UiField
    Element orasd;

    @UiField
    Element deschis;

    @UiField
    Element escala;

    @UiField
    Element version;

    ZborProxy proxy;

    public void setValue(ZborProxy proxy) {
        this.proxy = proxy;
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        avion.setInnerText(proxy.getAvion() == null ? "" : ro.alex.client.managed.ui.renderer.AvionProxyRenderer.instance().render(proxy.getAvion()));
        cod.setInnerText(proxy.getCod() == null ? "" : String.valueOf(proxy.getCod()));
        numarKm.setInnerText(proxy.getNumarKm() == null ? "" : String.valueOf(proxy.getNumarKm()));
        data.setInnerText(proxy.getData() == null ? "" : DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).format(proxy.getData()));
        orasp.setInnerText(proxy.getOrasp() == null ? "" : ro.alex.client.managed.ui.renderer.OrasProxyRenderer.instance().render(proxy.getOrasp()));
        orasd.setInnerText(proxy.getOrasd() == null ? "" : ro.alex.client.managed.ui.renderer.OrasProxyRenderer.instance().render(proxy.getOrasd()));
        deschis.setInnerText(proxy.getDeschis() == null ? "" : String.valueOf(proxy.getDeschis()));
        escala.setInnerText(proxy.getEscala() == null ? "" : String.valueOf(proxy.getEscala()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
    }
}
