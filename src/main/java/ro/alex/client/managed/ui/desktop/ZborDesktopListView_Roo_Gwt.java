// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.managed.ui.desktop;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import java.util.HashSet;
import java.util.Set;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.proxy.OrasProxy;
import ro.alex.client.proxy.ZborProxy;
import ro.alex.client.scaffold.place.AbstractProxyListView;

public abstract class ZborDesktopListView_Roo_Gwt extends AbstractProxyListView<ZborProxy> {

    @UiField
    CellTable<ZborProxy> table;

    protected Set<String> paths = new HashSet<String>();

    public void init() {
        paths.add("id");
        table.addColumn(new TextColumn<ZborProxy>() {

            Renderer<java.lang.Long> renderer = new AbstractRenderer<java.lang.Long>() {

                public String render(java.lang.Long obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(ZborProxy object) {
                return renderer.render(object.getId());
            }
        }, "Id");
        paths.add("avion");
        table.addColumn(new TextColumn<ZborProxy>() {

            Renderer<ro.alex.client.proxy.AvionProxy> renderer = ro.alex.client.managed.ui.renderer.AvionProxyRenderer.instance();

            @Override
            public String getValue(ZborProxy object) {
                return renderer.render(object.getAvion());
            }
        }, "Avion");
        paths.add("cod");
        table.addColumn(new TextColumn<ZborProxy>() {

            Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

                public String render(java.lang.String obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(ZborProxy object) {
                return renderer.render(object.getCod());
            }
        }, "Cod");
        paths.add("numarKm");
        table.addColumn(new TextColumn<ZborProxy>() {

            Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {

                public String render(java.lang.Integer obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(ZborProxy object) {
                return renderer.render(object.getNumarKm());
            }
        }, "Numar Km");
        paths.add("data");
        table.addColumn(new TextColumn<ZborProxy>() {

            Renderer<java.util.Date> renderer = new DateTimeFormatRenderer(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT));

            @Override
            public String getValue(ZborProxy object) {
                return renderer.render(object.getData());
            }
        }, "Data");
        paths.add("orasp");
        table.addColumn(new TextColumn<ZborProxy>() {

            Renderer<ro.alex.client.proxy.OrasProxy> renderer = ro.alex.client.managed.ui.renderer.OrasProxyRenderer.instance();

            @Override
            public String getValue(ZborProxy object) {
                return renderer.render(object.getOrasp());
            }
        }, "Orasp");
        paths.add("orasd");
        table.addColumn(new TextColumn<ZborProxy>() {

            Renderer<ro.alex.client.proxy.OrasProxy> renderer = ro.alex.client.managed.ui.renderer.OrasProxyRenderer.instance();

            @Override
            public String getValue(ZborProxy object) {
                return renderer.render(object.getOrasd());
            }
        }, "Orasd");
        paths.add("deschis");
        table.addColumn(new TextColumn<ZborProxy>() {

            Renderer<java.lang.Boolean> renderer = new AbstractRenderer<java.lang.Boolean>() {

                public String render(java.lang.Boolean obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(ZborProxy object) {
                return renderer.render(object.getDeschis());
            }
        }, "Deschis");
        paths.add("escala");
        table.addColumn(new TextColumn<ZborProxy>() {

            Renderer<java.lang.Boolean> renderer = new AbstractRenderer<java.lang.Boolean>() {

                public String render(java.lang.Boolean obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(ZborProxy object) {
                return renderer.render(object.getEscala());
            }
        }, "Escala");
        paths.add("version");
        table.addColumn(new TextColumn<ZborProxy>() {

            Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {

                public String render(java.lang.Integer obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(ZborProxy object) {
                return renderer.render(object.getVersion());
            }
        }, "Version");
    }
}
