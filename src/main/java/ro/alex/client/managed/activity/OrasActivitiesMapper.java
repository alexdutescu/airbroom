package ro.alex.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.OrasDetailsView;
import ro.alex.client.managed.ui.OrasEditView;
import ro.alex.client.managed.ui.OrasListView;
import ro.alex.client.managed.ui.OrasMobileDetailsView;
import ro.alex.client.managed.ui.OrasMobileEditView;
import ro.alex.client.proxy.OrasProxy;
import ro.alex.client.request.OrasRequest_Roo_Gwt;
import ro.alex.client.scaffold.ScaffoldApp;
import ro.alex.client.scaffold.place.CreateAndEditProxy;
import ro.alex.client.scaffold.place.FindAndEditProxy;
import ro.alex.client.scaffold.place.ProxyPlace;

public class OrasActivitiesMapper extends OrasActivitiesMapper_Roo_Gwt {

    public OrasActivitiesMapper(ApplicationRequestFactory requests, PlaceController placeController) {
        this.requests = requests;
        this.placeController = placeController;
    }

    public Activity getActivity(ProxyPlace place) {
        switch(place.getOperation()) {
            case DETAILS:
                return new OrasDetailsActivity((EntityProxyId<OrasProxy>) place.getProxyId(), requests, placeController, ScaffoldApp.isMobile() ? OrasMobileDetailsView.instance() : OrasDetailsView.instance());
            case EDIT:
                return makeEditActivity(place);
            case CREATE:
                return makeCreateActivity();
        }
        throw new IllegalArgumentException("Unknown operation " + place.getOperation());
    }
}
