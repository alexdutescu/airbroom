package ro.alex.client.managed.activity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.OrasEditView;
import ro.alex.client.managed.ui.OrasEditView.Delegate;
import ro.alex.client.proxy.OrasProxy;
import ro.alex.client.request.OrasRequest;
import ro.alex.client.scaffold.place.AbstractProxyEditActivity;
import ro.alex.client.scaffold.place.ProxyListPlace;

public class OrasEditActivity extends AbstractProxyEditActivity<OrasProxy> implements Delegate {

    private final OrasEditView<?> view;

    private final OrasRequest request;

    public OrasEditActivity(EntityProxyId<OrasProxy> proxyId, ApplicationRequestFactory factory, OrasEditView<?> view, PlaceController placeController) {
        super(proxyId, factory, placeController);
        this.view = view;
        this.request = factory.orasRequest();
    }

    @Override
    protected OrasEditView<?> getView() {
        return view;
    }

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        this.view.setDelegate(this);
        super.start(display, eventBus);
    }

    @Override
    protected OrasProxy createProxy() {
        return request.create(OrasProxy.class);
    }

    @Override
    protected RequestContext createSaveRequest(OrasProxy proxy) {
        request.persist().using(proxy);
        return request;
    }
}
