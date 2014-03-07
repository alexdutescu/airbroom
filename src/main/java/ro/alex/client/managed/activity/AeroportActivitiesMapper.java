package ro.alex.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.AeroportDetailsView;
import ro.alex.client.managed.ui.AeroportEditView;
import ro.alex.client.managed.ui.AeroportListView;
import ro.alex.client.managed.ui.AeroportMobileDetailsView;
import ro.alex.client.managed.ui.AeroportMobileEditView;
import ro.alex.client.proxy.AeroportProxy;
import ro.alex.client.request.AeroportRequest_Roo_Gwt;
import ro.alex.client.scaffold.ScaffoldApp;
import ro.alex.client.scaffold.place.CreateAndEditProxy;
import ro.alex.client.scaffold.place.FindAndEditProxy;
import ro.alex.client.scaffold.place.ProxyPlace;

public class AeroportActivitiesMapper extends AeroportActivitiesMapper_Roo_Gwt {

    public AeroportActivitiesMapper(ApplicationRequestFactory requests, PlaceController placeController) {
        this.requests = requests;
        this.placeController = placeController;
    }

    public Activity getActivity(ProxyPlace place) {
        switch(place.getOperation()) {
            case DETAILS:
                return new AeroportDetailsActivity((EntityProxyId<AeroportProxy>) place.getProxyId(), requests, placeController, ScaffoldApp.isMobile() ? AeroportMobileDetailsView.instance() : AeroportDetailsView.instance());
            case EDIT:
                return makeEditActivity(place);
            case CREATE:
                return makeCreateActivity();
        }
        throw new IllegalArgumentException("Unknown operation " + place.getOperation());
    }
}
