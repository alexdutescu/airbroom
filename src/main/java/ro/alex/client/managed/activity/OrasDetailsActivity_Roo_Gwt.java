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
import ro.alex.client.managed.ui.OrasDetailsView;
import ro.alex.client.managed.ui.OrasDetailsView.Delegate;
import ro.alex.client.proxy.OrasProxy;
import ro.alex.client.scaffold.activity.IsScaffoldMobileActivity;
import ro.alex.client.scaffold.place.ProxyListPlace;
import ro.alex.client.scaffold.place.ProxyPlace;
import ro.alex.client.scaffold.place.ProxyPlace.Operation;

public abstract class OrasDetailsActivity_Roo_Gwt extends AbstractActivity implements Delegate, IsScaffoldMobileActivity {

    protected ApplicationRequestFactory requests;

    protected EntityProxyId<OrasProxy> proxyId;

    protected PlaceController placeController;

    protected AcceptsOneWidget display;

    protected OrasDetailsView view;

    public void deleteClicked() {
        if (!view.confirm("Really delete this entry? You cannot undo this change.")) {
            return;
        }
        requests.orasRequest().remove().using(view.getValue()).fire(new Receiver<Void>() {

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
        requests.find(proxyId).with().fire(callback);
    }
}