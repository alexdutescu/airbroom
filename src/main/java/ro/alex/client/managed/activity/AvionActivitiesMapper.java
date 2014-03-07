package ro.alex.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.AvionDetailsView;
import ro.alex.client.managed.ui.AvionEditView;
import ro.alex.client.managed.ui.AvionListView;
import ro.alex.client.managed.ui.AvionMobileDetailsView;
import ro.alex.client.managed.ui.AvionMobileEditView;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.request.AvionRequest_Roo_Gwt;
import ro.alex.client.scaffold.ScaffoldApp;
import ro.alex.client.scaffold.place.CreateAndEditProxy;
import ro.alex.client.scaffold.place.FindAndEditProxy;
import ro.alex.client.scaffold.place.ProxyPlace;

public class AvionActivitiesMapper extends AvionActivitiesMapper_Roo_Gwt {

    public AvionActivitiesMapper(ApplicationRequestFactory requests, PlaceController placeController) {
        this.requests = requests;
        this.placeController = placeController;
    }

    public Activity getActivity(ProxyPlace place) {
        switch(place.getOperation()) {
            case DETAILS:
                return new AvionDetailsActivity((EntityProxyId<AvionProxy>) place.getProxyId(), requests, placeController, ScaffoldApp.isMobile() ? AvionMobileDetailsView.instance() : AvionDetailsView.instance());
            case EDIT:
                return makeEditActivity(place);
            case CREATE:
                return makeCreateActivity();
        }
        throw new IllegalArgumentException("Unknown operation " + place.getOperation());
    }
}
