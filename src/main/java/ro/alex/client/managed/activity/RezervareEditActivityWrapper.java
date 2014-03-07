package ro.alex.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.LocProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.RezervareProxy;
import ro.alex.client.proxy.ZborProxy;
import ro.alex.client.scaffold.activity.IsScaffoldMobileActivity;
import ro.alex.client.scaffold.place.ProxyEditView;
import ro.alex.client.scaffold.place.ProxyListPlace;
import ro.alex.client.scaffold.place.ProxyPlace;

public class RezervareEditActivityWrapper extends RezervareEditActivityWrapper_Roo_Gwt {

    private final EntityProxyId<RezervareProxy> proxyId;

    public RezervareEditActivityWrapper(ApplicationRequestFactory requests, View<?> view, Activity activity, EntityProxyId<ro.alex.client.proxy.RezervareProxy> proxyId) {
        this.requests = requests;
        this.view = view;
        this.wrapped = activity;
        this.proxyId = proxyId;
    }

    public Place getBackButtonPlace() {
        return (proxyId == null) ? new ProxyListPlace(RezervareProxy.class) : new ProxyPlace(proxyId, ProxyPlace.Operation.DETAILS);
    }

    public String getBackButtonText() {
        return "Cancel";
    }

    public Place getEditButtonPlace() {
        return null;
    }

    public String getTitleText() {
        return (proxyId == null) ? "New Rezervare" : "Edit Rezervare";
    }

    public boolean hasEditButton() {
        return false;
    }

    @Override
    public String mayStop() {
        return wrapped.mayStop();
    }

    @Override
    public void onCancel() {
        wrapped.onCancel();
    }

    @Override
    public void onStop() {
        wrapped.onStop();
    }

    public interface View<V extends ro.alex.client.scaffold.place.ProxyEditView<ro.alex.client.proxy.RezervareProxy, V>> extends ProxyEditView<RezervareProxy, V> {

        void setLocDusPickerValues(Collection<ro.alex.client.proxy.LocProxy> values);

        void setLocIntorsPickerValues(Collection<ro.alex.client.proxy.LocProxy> values);

        void setZborDusPickerValues(Collection<ro.alex.client.proxy.ZborProxy> values);

        void setZborIntorsPickerValues(Collection<ro.alex.client.proxy.ZborProxy> values);

        void setPersoanaPickerValues(Collection<ro.alex.client.proxy.PersoanaProxy> values);
    }
}
