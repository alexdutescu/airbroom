// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.managed.activity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.desktop.AvionDesktopDetailsView;
import ro.alex.client.managed.ui.desktop.AvionDesktopEditView;
import ro.alex.client.managed.ui.desktop.AvionDesktopListView;
import ro.alex.client.managed.ui.mobile.AvionMobileDetailsView;
import ro.alex.client.managed.ui.mobile.AvionMobileEditView;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.request.AvionRequest;
import ro.alex.client.scaffold.ScaffoldApp;
import ro.alex.client.scaffold.place.ProxyPlace;

public abstract class AvionActivitiesMapper_Roo_Gwt {

    protected ApplicationRequestFactory factory;

    protected PlaceController placeController;

    protected Activity makeCreateActivity() {
        AvionDesktopEditView.instance().setCreating(true);
        Activity activity = new AvionEditActivity(null, factory, ScaffoldApp.isMobile() ? AvionMobileEditView.instance() : AvionDesktopEditView.instance(), placeController);
        return new AvionEditActivityWrapper(factory, ScaffoldApp.isMobile() ? AvionMobileEditView.instance() : AvionDesktopEditView.instance(), activity, null);
    }

    @SuppressWarnings("unchecked")
    protected EntityProxyId<AvionProxy> coerceId(ProxyPlace place) {
        return (EntityProxyId<AvionProxy>) place.getProxyId();
    }

    protected Activity makeEditActivity(ProxyPlace place) {
        AvionDesktopEditView.instance().setCreating(false);
        EntityProxyId<AvionProxy> proxyId = coerceId(place);
        Activity activity = new AvionEditActivity(proxyId, factory, ScaffoldApp.isMobile() ? AvionMobileEditView.instance() : AvionDesktopEditView.instance(), placeController);
        return new AvionEditActivityWrapper(factory, ScaffoldApp.isMobile() ? AvionMobileEditView.instance() : AvionDesktopEditView.instance(), activity, proxyId);
    }
}
