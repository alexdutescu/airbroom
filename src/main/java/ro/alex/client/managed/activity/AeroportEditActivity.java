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
import ro.alex.client.managed.ui.AeroportEditView;
import ro.alex.client.managed.ui.AeroportEditView.Delegate;
import ro.alex.client.proxy.AeroportProxy;
import ro.alex.client.request.AeroportRequest;
import ro.alex.client.scaffold.place.AbstractProxyEditActivity;
import ro.alex.client.scaffold.place.ProxyListPlace;

public class AeroportEditActivity extends AbstractProxyEditActivity<AeroportProxy> implements Delegate {

    private final AeroportEditView<?> view;

    private final AeroportRequest request;

    public AeroportEditActivity(EntityProxyId<AeroportProxy> proxyId, ApplicationRequestFactory factory, AeroportEditView<?> view, PlaceController placeController) {
        super(proxyId, factory, placeController);
        this.view = view;
        this.request = factory.aeroportRequest();
    }

    @Override
    protected AeroportEditView<?> getView() {
        return view;
    }

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        this.view.setDelegate(this);
        super.start(display, eventBus);
    }

    @Override
    protected AeroportProxy createProxy() {
        return request.create(AeroportProxy.class);
    }

    @Override
    protected RequestContext createSaveRequest(AeroportProxy proxy) {
        request.persist().using(proxy);
        return request;
    }
}
