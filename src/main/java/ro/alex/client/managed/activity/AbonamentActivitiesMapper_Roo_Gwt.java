// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.managed.activity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.desktop.AbonamentDesktopDetailsView;
import ro.alex.client.managed.ui.desktop.AbonamentDesktopEditView;
import ro.alex.client.managed.ui.desktop.AbonamentDesktopListView;
import ro.alex.client.managed.ui.mobile.AbonamentMobileDetailsView;
import ro.alex.client.managed.ui.mobile.AbonamentMobileEditView;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.request.AbonamentRequest;
import ro.alex.client.scaffold.ScaffoldApp;
import ro.alex.client.scaffold.place.ProxyPlace;

public abstract class AbonamentActivitiesMapper_Roo_Gwt {

    protected ApplicationRequestFactory factory;

    protected PlaceController placeController;

    protected Activity makeCreateActivity() {
        AbonamentDesktopEditView.instance().setCreating(true);
        Activity activity = new AbonamentEditActivity(null, factory, ScaffoldApp.isMobile() ? AbonamentMobileEditView.instance() : AbonamentDesktopEditView.instance(), placeController);
        return new AbonamentEditActivityWrapper(factory, ScaffoldApp.isMobile() ? AbonamentMobileEditView.instance() : AbonamentDesktopEditView.instance(), activity, null);
    }

    @SuppressWarnings("unchecked")
    protected EntityProxyId<AbonamentProxy> coerceId(ProxyPlace place) {
        return (EntityProxyId<AbonamentProxy>) place.getProxyId();
    }

    protected Activity makeEditActivity(ProxyPlace place) {
        AbonamentDesktopEditView.instance().setCreating(false);
        EntityProxyId<AbonamentProxy> proxyId = coerceId(place);
        Activity activity = new AbonamentEditActivity(proxyId, factory, ScaffoldApp.isMobile() ? AbonamentMobileEditView.instance() : AbonamentDesktopEditView.instance(), placeController);
        return new AbonamentEditActivityWrapper(factory, ScaffoldApp.isMobile() ? AbonamentMobileEditView.instance() : AbonamentDesktopEditView.instance(), activity, proxyId);
    }
}
