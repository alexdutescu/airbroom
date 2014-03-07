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
import ro.alex.client.managed.ui.AbonamentEditView;
import ro.alex.client.managed.ui.AbonamentEditView.Delegate;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.request.AbonamentRequest;
import ro.alex.client.scaffold.place.AbstractProxyEditActivity;
import ro.alex.client.scaffold.place.ProxyListPlace;

public class AbonamentEditActivity extends AbstractProxyEditActivity<AbonamentProxy> implements Delegate {

    private final AbonamentEditView<?> view;

    private final AbonamentRequest request;

    public AbonamentEditActivity(EntityProxyId<AbonamentProxy> proxyId, ApplicationRequestFactory factory, AbonamentEditView<?> view, PlaceController placeController) {
        super(proxyId, factory, placeController);
        this.view = view;
        this.request = factory.abonamentRequest();
    }

    @Override
    protected AbonamentEditView<?> getView() {
        return view;
    }

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        this.view.setDelegate(this);
        super.start(display, eventBus);
    }

    @Override
    protected AbonamentProxy createProxy() {
        return request.create(AbonamentProxy.class);
    }

    @Override
    protected RequestContext createSaveRequest(AbonamentProxy proxy) {
        request.persist().using(proxy);
        return request;
    }
}
