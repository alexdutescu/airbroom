package ro.alex.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.PersoanaDetailsView;
import ro.alex.client.managed.ui.PersoanaEditView;
import ro.alex.client.managed.ui.PersoanaListView;
import ro.alex.client.managed.ui.PersoanaMobileDetailsView;
import ro.alex.client.managed.ui.PersoanaMobileEditView;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.UserProxy;
import ro.alex.client.request.PersoanaRequest_Roo_Gwt;
import ro.alex.client.scaffold.ScaffoldApp;
import ro.alex.client.scaffold.place.CreateAndEditProxy;
import ro.alex.client.scaffold.place.FindAndEditProxy;
import ro.alex.client.scaffold.place.ProxyPlace;

public class PersoanaActivitiesMapper extends PersoanaActivitiesMapper_Roo_Gwt {

    public PersoanaActivitiesMapper(ApplicationRequestFactory requests, PlaceController placeController) {
        this.requests = requests;
        this.placeController = placeController;
    }

    public Activity getActivity(ProxyPlace place) {
        switch(place.getOperation()) {
            case DETAILS:
                return new PersoanaDetailsActivity((EntityProxyId<PersoanaProxy>) place.getProxyId(), requests, placeController, ScaffoldApp.isMobile() ? PersoanaMobileDetailsView.instance() : PersoanaDetailsView.instance());
            case EDIT:
                return makeEditActivity(place);
            case CREATE:
                return makeCreateActivity();
        }
        throw new IllegalArgumentException("Unknown operation " + place.getOperation());
    }
}
