package ro.alex.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.UserDetailsView;
import ro.alex.client.managed.ui.UserEditView;
import ro.alex.client.managed.ui.UserListView;
import ro.alex.client.managed.ui.UserMobileDetailsView;
import ro.alex.client.managed.ui.UserMobileEditView;
import ro.alex.client.proxy.UserProxy;
import ro.alex.client.request.UserRequest_Roo_Gwt;
import ro.alex.client.scaffold.ScaffoldApp;
import ro.alex.client.scaffold.place.CreateAndEditProxy;
import ro.alex.client.scaffold.place.FindAndEditProxy;
import ro.alex.client.scaffold.place.ProxyPlace;

public class UserActivitiesMapper extends UserActivitiesMapper_Roo_Gwt {

    public UserActivitiesMapper(ApplicationRequestFactory requests, PlaceController placeController) {
        this.requests = requests;
        this.placeController = placeController;
    }

    public Activity getActivity(ProxyPlace place) {
        switch(place.getOperation()) {
            case DETAILS:
                return new UserDetailsActivity((EntityProxyId<UserProxy>) place.getProxyId(), requests, placeController, ScaffoldApp.isMobile() ? UserMobileDetailsView.instance() : UserDetailsView.instance());
            case EDIT:
                return makeEditActivity(place);
            case CREATE:
                return makeCreateActivity();
        }
        throw new IllegalArgumentException("Unknown operation " + place.getOperation());
    }
}
