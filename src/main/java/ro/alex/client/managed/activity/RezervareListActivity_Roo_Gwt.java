// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.managed.activity;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import java.util.List;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.LocProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.RezervareProxy;
import ro.alex.client.proxy.ZborProxy;
import ro.alex.client.scaffold.ScaffoldMobileApp;
import ro.alex.client.scaffold.activity.IsScaffoldMobileActivity;
import ro.alex.client.scaffold.place.AbstractProxyListActivity;
import ro.alex.client.scaffold.place.ProxyListView;

public abstract class RezervareListActivity_Roo_Gwt extends AbstractProxyListActivity<RezervareProxy> implements IsScaffoldMobileActivity {

    protected ApplicationRequestFactory requests;

    public RezervareListActivity_Roo_Gwt(PlaceController placeController, ProxyListView<RezervareProxy> view, Class<RezervareProxy> proxyType) {
        super(placeController, view, proxyType);
    }

    protected void fireCountRequest(Receiver<Long> callback) {
        requests.rezervareRequest().countRezervares().fire(callback);
    }
}
