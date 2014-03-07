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
import ro.alex.client.managed.ui.ContEditView;
import ro.alex.client.managed.ui.ContEditView.Delegate;
import ro.alex.client.proxy.ContProxy;
import ro.alex.client.request.ContRequest;
import ro.alex.client.scaffold.place.AbstractProxyEditActivity;
import ro.alex.client.scaffold.place.ProxyListPlace;

public class ContEditActivity extends AbstractProxyEditActivity<ContProxy> implements Delegate {

    private final ContEditView<?> view;

    private final ContRequest request;

    public ContEditActivity(EntityProxyId<ContProxy> proxyId, ApplicationRequestFactory factory, ContEditView<?> view, PlaceController placeController) {
        super(proxyId, factory, placeController);
        this.view = view;
        this.request = factory.contRequest();
    }

    @Override
    protected ContEditView<?> getView() {
        return view;
    }

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        this.view.setDelegate(this);
        super.start(display, eventBus);
    }

    @Override
    protected ContProxy createProxy() {
        return request.create(ContProxy.class);
    }

    @Override
    protected RequestContext createSaveRequest(ContProxy proxy) {
        request.persist().using(proxy);
        return request;
    }
}
