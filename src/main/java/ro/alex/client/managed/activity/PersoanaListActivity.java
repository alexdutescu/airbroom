package ro.alex.client.managed.activity;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import java.util.List;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.UserProxy;
import ro.alex.client.scaffold.ScaffoldMobileApp;
import ro.alex.client.scaffold.activity.IsScaffoldMobileActivity;
import ro.alex.client.scaffold.place.AbstractProxyListActivity;
import ro.alex.client.scaffold.place.ProxyListView;

public class PersoanaListActivity extends PersoanaListActivity_Roo_Gwt {

    public PersoanaListActivity(ApplicationRequestFactory requests, ProxyListView<ro.alex.client.proxy.PersoanaProxy> view, PlaceController placeController) {
        super(placeController, view, PersoanaProxy.class);
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
        return "Persoanas";
    }

    public boolean hasEditButton() {
        return false;
    }

    protected Request<java.util.List<ro.alex.client.proxy.PersoanaProxy>> createRangeRequest(Range range) {
        return requests.persoanaRequest().findPersoanaEntries(range.getStart(), range.getLength());
    }
}
