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
import ro.alex.client.managed.ui.RezervareEditView;
import ro.alex.client.managed.ui.RezervareEditView.Delegate;
import ro.alex.client.proxy.LocProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.RezervareProxy;
import ro.alex.client.proxy.ZborProxy;
import ro.alex.client.request.RezervareRequest;
import ro.alex.client.scaffold.place.AbstractProxyEditActivity;
import ro.alex.client.scaffold.place.ProxyListPlace;

public class RezervareEditActivity extends AbstractProxyEditActivity<RezervareProxy> implements Delegate {

    private final RezervareEditView<?> view;

    private final RezervareRequest request;

    public RezervareEditActivity(EntityProxyId<RezervareProxy> proxyId, ApplicationRequestFactory factory, RezervareEditView<?> view, PlaceController placeController) {
        super(proxyId, factory, placeController);
        this.view = view;
        this.request = factory.rezervareRequest();
    }

    @Override
    protected RezervareEditView<?> getView() {
        return view;
    }

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        this.view.setDelegate(this);
        super.start(display, eventBus);
    }

    @Override
    protected RezervareProxy createProxy() {
        return request.create(RezervareProxy.class);
    }

    @Override
    protected RequestContext createSaveRequest(RezervareProxy proxy) {
        request.persist().using(proxy);
        return request;
    }
}
