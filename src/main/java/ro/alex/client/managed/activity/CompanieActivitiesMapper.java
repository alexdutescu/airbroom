package ro.alex.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.CompanieDetailsView;
import ro.alex.client.managed.ui.CompanieEditView;
import ro.alex.client.managed.ui.CompanieListView;
import ro.alex.client.managed.ui.CompanieMobileDetailsView;
import ro.alex.client.managed.ui.CompanieMobileEditView;
import ro.alex.client.proxy.CompanieProxy;
import ro.alex.client.request.CompanieRequest_Roo_Gwt;
import ro.alex.client.scaffold.ScaffoldApp;
import ro.alex.client.scaffold.place.CreateAndEditProxy;
import ro.alex.client.scaffold.place.FindAndEditProxy;
import ro.alex.client.scaffold.place.ProxyPlace;

public class CompanieActivitiesMapper extends CompanieActivitiesMapper_Roo_Gwt {

    public CompanieActivitiesMapper(ApplicationRequestFactory requests, PlaceController placeController) {
        this.requests = requests;
        this.placeController = placeController;
    }

    public Activity getActivity(ProxyPlace place) {
        switch(place.getOperation()) {
            case DETAILS:
                return new CompanieDetailsActivity((EntityProxyId<CompanieProxy>) place.getProxyId(), requests, placeController, ScaffoldApp.isMobile() ? CompanieMobileDetailsView.instance() : CompanieDetailsView.instance());
            case EDIT:
                return makeEditActivity(place);
            case CREATE:
                return makeCreateActivity();
        }
        throw new IllegalArgumentException("Unknown operation " + place.getOperation());
    }
}
