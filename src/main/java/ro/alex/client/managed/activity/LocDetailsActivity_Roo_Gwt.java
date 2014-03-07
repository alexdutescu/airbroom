// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.managed.activity;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import java.util.Set;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.LocDetailsView;
import ro.alex.client.managed.ui.LocDetailsView.Delegate;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.proxy.LocProxy;
import ro.alex.client.proxy.ZborProxy;
import ro.alex.client.scaffold.activity.IsScaffoldMobileActivity;
import ro.alex.client.scaffold.place.ProxyListPlace;
import ro.alex.client.scaffold.place.ProxyPlace;
import ro.alex.client.scaffold.place.ProxyPlace.Operation;

public abstract class LocDetailsActivity_Roo_Gwt extends AbstractActivity implements Delegate, IsScaffoldMobileActivity {

    protected ApplicationRequestFactory requests;

    protected EntityProxyId<LocProxy> proxyId;

    protected PlaceController placeController;

    protected AcceptsOneWidget display;

    protected LocDetailsView view;

    public void deleteClicked() {
        if (!view.confirm("Really delete this entry? You cannot undo this change.")) {
            return;
        }
        requests.locRequest().remove().using(view.getValue()).fire(new Receiver<Void>() {

            public void onSuccess(Void ignore) {
                if (display == null) {
                    // This activity is dead
                    return;
                }
                // Go back to the previous place.
                placeController.goTo(getBackButtonPlace());
            }
        });
    }

    protected void find(Receiver<EntityProxy> callback) {
        requests.find(proxyId).with("clasa", "avion", "zbor").fire(callback);
    }
}
