package ro.alex.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.ContDetailsView;
import ro.alex.client.managed.ui.ContEditView;
import ro.alex.client.managed.ui.ContListView;
import ro.alex.client.managed.ui.ContMobileDetailsView;
import ro.alex.client.managed.ui.ContMobileEditView;
import ro.alex.client.proxy.ContProxy;
import ro.alex.client.request.ContRequest_Roo_Gwt;
import ro.alex.client.scaffold.ScaffoldApp;
import ro.alex.client.scaffold.place.CreateAndEditProxy;
import ro.alex.client.scaffold.place.FindAndEditProxy;
import ro.alex.client.scaffold.place.ProxyPlace;

public class ContActivitiesMapper extends ContActivitiesMapper_Roo_Gwt {

    public ContActivitiesMapper(ApplicationRequestFactory requests, PlaceController placeController) {
        this.requests = requests;
        this.placeController = placeController;
    }

    public Activity getActivity(ProxyPlace place) {
        switch(place.getOperation()) {
            case DETAILS:
                return new ContDetailsActivity((EntityProxyId<ContProxy>) place.getProxyId(), requests, placeController, ScaffoldApp.isMobile() ? ContMobileDetailsView.instance() : ContDetailsView.instance());
            case EDIT:
                return makeEditActivity(place);
            case CREATE:
                return makeCreateActivity();
        }
        throw new IllegalArgumentException("Unknown operation " + place.getOperation());
    }
}
