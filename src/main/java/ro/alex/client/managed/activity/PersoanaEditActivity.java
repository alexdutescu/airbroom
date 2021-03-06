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
import ro.alex.client.managed.ui.PersoanaEditView;
import ro.alex.client.managed.ui.PersoanaEditView.Delegate;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.UserProxy;
import ro.alex.client.request.PersoanaRequest;
import ro.alex.client.scaffold.place.AbstractProxyEditActivity;
import ro.alex.client.scaffold.place.ProxyListPlace;

public class PersoanaEditActivity extends AbstractProxyEditActivity<PersoanaProxy> implements Delegate {

    private final PersoanaEditView<?> view;

    private final PersoanaRequest request;

    public PersoanaEditActivity(EntityProxyId<PersoanaProxy> proxyId, ApplicationRequestFactory factory, PersoanaEditView<?> view, PlaceController placeController) {
        super(proxyId, factory, placeController);
        this.view = view;
        this.request = factory.persoanaRequest();
    }

    @Override
    protected PersoanaEditView<?> getView() {
        return view;
    }

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        this.view.setDelegate(this);
        super.start(display, eventBus);
    }

    @Override
    protected PersoanaProxy createProxy() {
        return request.create(PersoanaProxy.class);
    }

    @Override
    protected RequestContext createSaveRequest(PersoanaProxy proxy) {
        request.persist().using(proxy);
        return request;
    }
}
