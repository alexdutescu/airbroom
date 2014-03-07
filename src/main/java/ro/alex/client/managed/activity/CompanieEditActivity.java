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
import ro.alex.client.managed.ui.CompanieEditView;
import ro.alex.client.managed.ui.CompanieEditView.Delegate;
import ro.alex.client.proxy.CompanieProxy;
import ro.alex.client.request.CompanieRequest;
import ro.alex.client.scaffold.place.AbstractProxyEditActivity;
import ro.alex.client.scaffold.place.ProxyListPlace;

public class CompanieEditActivity extends AbstractProxyEditActivity<CompanieProxy> implements Delegate {

    private final CompanieEditView<?> view;

    private final CompanieRequest request;

    public CompanieEditActivity(EntityProxyId<CompanieProxy> proxyId, ApplicationRequestFactory factory, CompanieEditView<?> view, PlaceController placeController) {
        super(proxyId, factory, placeController);
        this.view = view;
        this.request = factory.companieRequest();
    }

    @Override
    protected CompanieEditView<?> getView() {
        return view;
    }

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        this.view.setDelegate(this);
        super.start(display, eventBus);
    }

    @Override
    protected CompanieProxy createProxy() {
        return request.create(CompanieProxy.class);
    }

    @Override
    protected RequestContext createSaveRequest(CompanieProxy proxy) {
        request.persist().using(proxy);
        return request;
    }
}
