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
import ro.alex.client.managed.ui.AvionEditView;
import ro.alex.client.managed.ui.AvionEditView.Delegate;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.request.AvionRequest;
import ro.alex.client.scaffold.place.AbstractProxyEditActivity;
import ro.alex.client.scaffold.place.ProxyListPlace;

public class AvionEditActivity extends AbstractProxyEditActivity<AvionProxy> implements Delegate {

    private final AvionEditView<?> view;

    private final AvionRequest request;

    public AvionEditActivity(EntityProxyId<AvionProxy> proxyId, ApplicationRequestFactory factory, AvionEditView<?> view, PlaceController placeController) {
        super(proxyId, factory, placeController);
        this.view = view;
        this.request = factory.avionRequest();
    }

    @Override
    protected AvionEditView<?> getView() {
        return view;
    }

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        this.view.setDelegate(this);
        super.start(display, eventBus);
    }

    @Override
    protected AvionProxy createProxy() {
        return request.create(AvionProxy.class);
    }

    @Override
    protected RequestContext createSaveRequest(AvionProxy proxy) {
        request.persist().using(proxy);
        return request;
    }
}
