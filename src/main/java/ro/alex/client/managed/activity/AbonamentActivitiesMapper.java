package ro.alex.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.AbonamentDetailsView;
import ro.alex.client.managed.ui.AbonamentEditView;
import ro.alex.client.managed.ui.AbonamentListView;
import ro.alex.client.managed.ui.AbonamentMobileDetailsView;
import ro.alex.client.managed.ui.AbonamentMobileEditView;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.request.AbonamentRequest_Roo_Gwt;
import ro.alex.client.scaffold.ScaffoldApp;
import ro.alex.client.scaffold.place.CreateAndEditProxy;
import ro.alex.client.scaffold.place.FindAndEditProxy;
import ro.alex.client.scaffold.place.ProxyPlace;

public class AbonamentActivitiesMapper extends AbonamentActivitiesMapper_Roo_Gwt {

    public AbonamentActivitiesMapper(ApplicationRequestFactory requests, PlaceController placeController) {
        this.requests = requests;
        this.placeController = placeController;
    }

    public Activity getActivity(ProxyPlace place) {
        switch(place.getOperation()) {
            case DETAILS:
                return new AbonamentDetailsActivity((EntityProxyId<AbonamentProxy>) place.getProxyId(), requests, placeController, ScaffoldApp.isMobile() ? AbonamentMobileDetailsView.instance() : AbonamentDetailsView.instance());
            case EDIT:
                return makeEditActivity(place);
            case CREATE:
                return makeCreateActivity();
        }
        throw new IllegalArgumentException("Unknown operation " + place.getOperation());
    }
}
