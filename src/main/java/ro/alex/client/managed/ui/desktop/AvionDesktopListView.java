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
import ro.alex.client.managed.ui.AvionListView;
import ro.alex.client.managed.ui.AvionListView_Roo_Gwt;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.scaffold.place.AbstractProxyListView;

public class AvionDesktopListView extends AvionListView_Roo_Gwt {

    private static final Binder BINDER = GWT.create(Binder.class);

    private static AvionListView instance;

    @UiField
    Button newButton;

    public AvionListView() {
        init(BINDER.createAndBindUi(this), table, newButton);
        table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
        init();
    }

    public static AvionListView instance() {
        if (instance == null) {
            instance = new AvionListView();
        }
        return instance;
    }

    public String[] getPaths() {
        return paths.toArray(new String[paths.size()]);
    }

    interface Binder extends UiBinder<HTMLPanel, AvionListView> {
    }
}
