package ro.alex.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import ro.alex.client.managed.request.ApplicationEntityTypesProcessor;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.AbonamentListView;
import ro.alex.client.managed.ui.AbonamentMobileListView;
import ro.alex.client.managed.ui.AeroportListView;
import ro.alex.client.managed.ui.AeroportMobileListView;
import ro.alex.client.managed.ui.AvionListView;
import ro.alex.client.managed.ui.AvionMobileListView;
import ro.alex.client.managed.ui.ClasaListView;
import ro.alex.client.managed.ui.ClasaMobileListView;
import ro.alex.client.managed.ui.CompanieListView;
import ro.alex.client.managed.ui.CompanieMobileListView;
import ro.alex.client.managed.ui.ContListView;
import ro.alex.client.managed.ui.ContMobileListView;
import ro.alex.client.managed.ui.LocListView;
import ro.alex.client.managed.ui.LocMobileListView;
import ro.alex.client.managed.ui.OrasListView;
import ro.alex.client.managed.ui.OrasMobileListView;
import ro.alex.client.managed.ui.PersoanaListView;
import ro.alex.client.managed.ui.PersoanaMobileListView;
import ro.alex.client.managed.ui.RezervareListView;
import ro.alex.client.managed.ui.RezervareMobileListView;
import ro.alex.client.managed.ui.UserListView;
import ro.alex.client.managed.ui.UserMobileListView;
import ro.alex.client.managed.ui.ZborListView;
import ro.alex.client.managed.ui.ZborMobileListView;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.AeroportProxy;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.proxy.CompanieProxy;
import ro.alex.client.proxy.ContProxy;
import ro.alex.client.proxy.LocProxy;
import ro.alex.client.proxy.OrasProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.RezervareProxy;
import ro.alex.client.proxy.UserProxy;
import ro.alex.client.proxy.ZborProxy;
import ro.alex.client.scaffold.ScaffoldApp;
import ro.alex.client.scaffold.place.ProxyListPlace;

public final class ApplicationMasterActivities extends ApplicationMasterActivities_Roo_Gwt {

    @Inject
    public ApplicationMasterActivities(ApplicationRequestFactory requests, PlaceController placeController) {
        this.requests = requests;
        this.placeController = placeController;
    }
}
