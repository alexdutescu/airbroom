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
import ro.alex.client.managed.ui.AbonamentMobileDetailsView;
import ro.alex.client.managed.ui.AbonamentMobileDetailsView_Roo_Gwt;
import ro.alex.client.managed.ui.Delegate;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.scaffold.place.ProxyDetailsView;

public class AbonamentMobileDetailsView extends AbonamentMobileDetailsView_Roo_Gwt {

    private static final Binder BINDER = GWT.create(Binder.class);

    private static ro.alex.client.managed.ui.AbonamentMobileDetailsView instance;

    @UiField
    HasClickHandlers delete;

    private Delegate delegate;

    public AbonamentMobileDetailsView() {
        initWidget(BINDER.createAndBindUi(this));
    }

    public static ro.alex.client.managed.ui.AbonamentMobileDetailsView instance() {
        if (instance == null) {
            instance = new AbonamentMobileDetailsView();
        }
        return instance;
    }

    public Widget asWidget() {
        return this;
    }

    public boolean confirm(String msg) {
        return Window.confirm(msg);
    }

    public AbonamentProxy getValue() {
        return proxy;
    }

    @UiHandler("delete")
    public void onDeleteClicked(ClickEvent e) {
        delegate.deleteClicked();
    }

    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

    interface Binder extends UiBinder<HTMLPanel, AbonamentMobileDetailsView> {
    }
}
