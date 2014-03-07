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
import ro.alex.client.managed.ui.ClasaEditView;
import ro.alex.client.managed.ui.ClasaEditView.Delegate;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.request.ClasaRequest;
import ro.alex.client.scaffold.place.AbstractProxyEditActivity;
import ro.alex.client.scaffold.place.ProxyListPlace;

public class ClasaEditActivity extends AbstractProxyEditActivity<ClasaProxy> implements Delegate {

    private final ClasaEditView<?> view;

    private final ClasaRequest request;

    public ClasaEditActivity(EntityProxyId<ClasaProxy> proxyId, ApplicationRequestFactory factory, ClasaEditView<?> view, PlaceController placeController) {
        super(proxyId, factory, placeController);
        this.view = view;
        this.request = factory.clasaRequest();
    }

    @Override
    protected ClasaEditView<?> getView() {
        return view;
    }

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        this.view.setDelegate(this);
        super.start(display, eventBus);
    }

    @Override
    protected ClasaProxy createProxy() {
        return request.create(ClasaProxy.class);
    }

    @Override
    protected RequestContext createSaveRequest(ClasaProxy proxy) {
        request.persist().using(proxy);
        return request;
    }
}
