package ro.alex.client.managed.ui.mobile;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import java.util.Collection;
import java.util.List;
import ro.alex.client.managed.activity.ClasaEditActivityWrapper;
import ro.alex.client.managed.ui.ClasaMobileEditView;
import ro.alex.client.managed.ui.ClasaMobileEditView_Roo_Gwt;
import ro.alex.client.managed.ui.Delegate;
import ro.alex.client.managed.ui.Override;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.scaffold.place.ProxyEditView;
import ro.alex.client.scaffold.ui.*;

public class ClasaMobileEditView extends ClasaMobileEditView_Roo_Gwt {

    private static final Binder BINDER = GWT.create(Binder.class);

    private static ro.alex.client.managed.ui.ClasaMobileEditView instance;

    @UiField
    Button save;

    @UiField
    DivElement errors;

    private Delegate delegate;

    public ClasaMobileEditView() {
        initWidget(BINDER.createAndBindUi(this));
    }

    public static ro.alex.client.managed.ui.ClasaMobileEditView instance() {
        if (instance == null) {
            instance = new ClasaMobileEditView();
        }
        return instance;
    }

    @Override
    public RequestFactoryEditorDriver<ClasaProxy, ro.alex.client.managed.ui.ClasaMobileEditView> createEditorDriver() {
        RequestFactoryEditorDriver<ClasaProxy, ClasaMobileEditView> driver = GWT.create(Driver.class);
        driver.initialize(this);
        return driver;
    }

    public void setCreating(boolean creating) {
    }

    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

    public void setEnabled(boolean enabled) {
        save.setEnabled(enabled);
    }

    public void showErrors(List<EditorError> errors) {
        SafeHtmlBuilder b = new SafeHtmlBuilder();
        for (EditorError error : errors) {
            b.appendEscaped(error.getPath()).appendEscaped(": ");
            b.appendEscaped(error.getMessage()).appendHtmlConstant("<br>");
        }
        this.errors.setInnerHTML(b.toSafeHtml().asString());
    }

    @UiHandler("save")
    void onSave(ClickEvent event) {
        delegate.saveClicked();
    }

    interface Binder extends UiBinder<HTMLPanel, ClasaMobileEditView> {
    }

    interface Driver extends RequestFactoryEditorDriver<ClasaProxy, ClasaMobileEditView> {
    }
}
