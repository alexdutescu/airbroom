package ro.alex.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.ClasaDetailsView;
import ro.alex.client.managed.ui.ClasaEditView;
import ro.alex.client.managed.ui.ClasaListView;
import ro.alex.client.managed.ui.ClasaMobileDetailsView;
import ro.alex.client.managed.ui.ClasaMobileEditView;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.request.ClasaRequest_Roo_Gwt;
import ro.alex.client.scaffold.ScaffoldApp;
import ro.alex.client.scaffold.place.CreateAndEditProxy;
import ro.alex.client.scaffold.place.FindAndEditProxy;
import ro.alex.client.scaffold.place.ProxyPlace;

public class ClasaActivitiesMapper extends ClasaActivitiesMapper_Roo_Gwt {

    public ClasaActivitiesMapper(ApplicationRequestFactory requests, PlaceController placeController) {
        this.requests = requests;
        this.placeController = placeController;
    }

    public Activity getActivity(ProxyPlace place) {
        switch(place.getOperation()) {
            case DETAILS:
                return new ClasaDetailsActivity((EntityProxyId<ClasaProxy>) place.getProxyId(), requests, placeController, ScaffoldApp.isMobile() ? ClasaMobileDetailsView.instance() : ClasaDetailsView.instance());
            case EDIT:
                return makeEditActivity(place);
            case CREATE:
                return makeCreateActivity();
        }
        throw new IllegalArgumentException("Unknown operation " + place.getOperation());
    }
}
