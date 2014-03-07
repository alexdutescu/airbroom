package ro.alex.client.managed.activity;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import java.util.List;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.AeroportProxy;
import ro.alex.client.scaffold.ScaffoldMobileApp;
import ro.alex.client.scaffold.activity.IsScaffoldMobileActivity;
import ro.alex.client.scaffold.place.AbstractProxyListActivity;
import ro.alex.client.scaffold.place.ProxyListView;

public class AeroportListActivity extends AeroportListActivity_Roo_Gwt {

    public AeroportListActivity(ApplicationRequestFactory requests, ProxyListView<ro.alex.client.proxy.AeroportProxy> view, PlaceController placeController) {
        super(placeController, view, AeroportProxy.class);
        this.requests = requests;
    }

    public Place getBackButtonPlace() {
        return ScaffoldMobileApp.ROOT_PLACE;
    }

    public String getBackButtonText() {
        return "Entities";
    }

    public Place getEditButtonPlace() {
        return null;
    }

    public String getTitleText() {
        return "Aeroports";
    }

    public boolean hasEditButton() {
        return false;
    }

    protected Request<java.util.List<ro.alex.client.proxy.AeroportProxy>> createRangeRequest(Range range) {
        return requests.aeroportRequest().findAeroportEntries(range.getStart(), range.getLength());
    }
}
