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
import ro.alex.client.managed.ui.Delegate;
import ro.alex.client.managed.ui.PersoanaDetailsView;
import ro.alex.client.managed.ui.PersoanaDetailsView_Roo_Gwt;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.UserProxy;
import ro.alex.client.scaffold.place.ProxyDetailsView;
import ro.alex.client.scaffold.place.ProxyListView;

public class PersoanaDesktopDetailsView extends PersoanaDetailsView_Roo_Gwt {

    private static final Binder BINDER = GWT.create(Binder.class);

    private static PersoanaDetailsView instance;

    @UiField
    HasClickHandlers edit;

    @UiField
    HasClickHandlers delete;

    private Delegate delegate;

    public PersoanaDetailsView() {
        initWidget(BINDER.createAndBindUi(this));
    }

    public static PersoanaDetailsView instance() {
        if (instance == null) {
            instance = new PersoanaDetailsView();
        }
        return instance;
    }

    public Widget asWidget() {
        return this;
    }

    public boolean confirm(String msg) {
        return Window.confirm(msg);
    }

    public PersoanaProxy getValue() {
        return proxy;
    }

    @UiHandler("delete")
    public void onDeleteClicked(ClickEvent e) {
        delegate.deleteClicked();
    }

    @UiHandler("edit")
    public void onEditClicked(ClickEvent e) {
        delegate.editClicked();
    }

    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

    interface Binder extends UiBinder<HTMLPanel, PersoanaDetailsView> {
    }
}
