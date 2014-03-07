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
import ro.alex.client.managed.ui.ZborEditView;
import ro.alex.client.managed.ui.ZborEditView.Delegate;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.proxy.OrasProxy;
import ro.alex.client.proxy.ZborProxy;
import ro.alex.client.request.ZborRequest;
import ro.alex.client.scaffold.place.AbstractProxyEditActivity;
import ro.alex.client.scaffold.place.ProxyListPlace;

public class ZborEditActivity extends AbstractProxyEditActivity<ZborProxy> implements Delegate {

    private final ZborEditView<?> view;

    private final ZborRequest request;

    public ZborEditActivity(EntityProxyId<ZborProxy> proxyId, ApplicationRequestFactory factory, ZborEditView<?> view, PlaceController placeController) {
        super(proxyId, factory, placeController);
        this.view = view;
        this.request = factory.zborRequest();
    }

    @Override
    protected ZborEditView<?> getView() {
        return view;
    }

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        this.view.setDelegate(this);
        super.start(display, eventBus);
    }

    @Override
    protected ZborProxy createProxy() {
        return request.create(ZborProxy.class);
    }

    @Override
    protected RequestContext createSaveRequest(ZborProxy proxy) {
        request.persist().using(proxy);
        return request;
    }
}
