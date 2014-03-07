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
import ro.alex.client.managed.ui.UserEditView;
import ro.alex.client.managed.ui.UserEditView.Delegate;
import ro.alex.client.proxy.UserProxy;
import ro.alex.client.request.UserRequest;
import ro.alex.client.scaffold.place.AbstractProxyEditActivity;
import ro.alex.client.scaffold.place.ProxyListPlace;

public class UserEditActivity extends AbstractProxyEditActivity<UserProxy> implements Delegate {

    private final UserEditView<?> view;

    private final UserRequest request;

    public UserEditActivity(EntityProxyId<UserProxy> proxyId, ApplicationRequestFactory factory, UserEditView<?> view, PlaceController placeController) {
        super(proxyId, factory, placeController);
        this.view = view;
        this.request = factory.userRequest();
    }

    @Override
    protected UserEditView<?> getView() {
        return view;
    }

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        this.view.setDelegate(this);
        super.start(display, eventBus);
    }

    @Override
    protected UserProxy createProxy() {
        return request.create(UserProxy.class);
    }

    @Override
    protected RequestContext createSaveRequest(UserProxy proxy) {
        request.persist().using(proxy);
        return request;
    }
}
