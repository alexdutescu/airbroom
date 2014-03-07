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
import ro.alex.client.managed.ui.LocEditView;
import ro.alex.client.managed.ui.LocEditView.Delegate;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.proxy.LocProxy;
import ro.alex.client.proxy.ZborProxy;
import ro.alex.client.request.LocRequest;
import ro.alex.client.scaffold.place.AbstractProxyEditActivity;
import ro.alex.client.scaffold.place.ProxyListPlace;

public class LocEditActivity extends AbstractProxyEditActivity<LocProxy> implements Delegate {

    private final LocEditView<?> view;

    private final LocRequest request;

    public LocEditActivity(EntityProxyId<LocProxy> proxyId, ApplicationRequestFactory factory, LocEditView<?> view, PlaceController placeController) {
        super(proxyId, factory, placeController);
        this.view = view;
        this.request = factory.locRequest();
    }

    @Override
    protected LocEditView<?> getView() {
        return view;
    }

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        this.view.setDelegate(this);
        super.start(display, eventBus);
    }

    @Override
    protected LocProxy createProxy() {
        return request.create(LocProxy.class);
    }

    @Override
    protected RequestContext createSaveRequest(LocProxy proxy) {
        request.persist().using(proxy);
        return request;
    }
}
