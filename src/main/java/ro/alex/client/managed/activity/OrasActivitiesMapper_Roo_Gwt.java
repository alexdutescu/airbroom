// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.managed.activity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.desktop.OrasDesktopDetailsView;
import ro.alex.client.managed.ui.desktop.OrasDesktopEditView;
import ro.alex.client.managed.ui.desktop.OrasDesktopListView;
import ro.alex.client.managed.ui.mobile.OrasMobileDetailsView;
import ro.alex.client.managed.ui.mobile.OrasMobileEditView;
import ro.alex.client.proxy.OrasProxy;
import ro.alex.client.request.OrasRequest;
import ro.alex.client.scaffold.ScaffoldApp;
import ro.alex.client.scaffold.place.ProxyPlace;

public abstract class OrasActivitiesMapper_Roo_Gwt {

    protected ApplicationRequestFactory factory;

    protected PlaceController placeController;

    protected Activity makeCreateActivity() {
        OrasDesktopEditView.instance().setCreating(true);
        Activity activity = new OrasEditActivity(null, factory, ScaffoldApp.isMobile() ? OrasMobileEditView.instance() : OrasDesktopEditView.instance(), placeController);
        return new OrasEditActivityWrapper(factory, ScaffoldApp.isMobile() ? OrasMobileEditView.instance() : OrasDesktopEditView.instance(), activity, null);
    }

    @SuppressWarnings("unchecked")
    protected EntityProxyId<OrasProxy> coerceId(ProxyPlace place) {
        return (EntityProxyId<OrasProxy>) place.getProxyId();
    }

    protected Activity makeEditActivity(ProxyPlace place) {
        OrasDesktopEditView.instance().setCreating(false);
        EntityProxyId<OrasProxy> proxyId = coerceId(place);
        Activity activity = new OrasEditActivity(proxyId, factory, ScaffoldApp.isMobile() ? OrasMobileEditView.instance() : OrasDesktopEditView.instance(), placeController);
        return new OrasEditActivityWrapper(factory, ScaffoldApp.isMobile() ? OrasMobileEditView.instance() : OrasDesktopEditView.instance(), activity, proxyId);
    }
}