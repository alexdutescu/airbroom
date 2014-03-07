// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.managed.activity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import ro.alex.client.managed.request.ApplicationEntityTypesProcessor;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.managed.ui.desktop.AbonamentDesktopListView;
import ro.alex.client.managed.ui.desktop.AeroportDesktopListView;
import ro.alex.client.managed.ui.desktop.AvionDesktopListView;
import ro.alex.client.managed.ui.desktop.ClasaDesktopListView;
import ro.alex.client.managed.ui.desktop.CompanieDesktopListView;
import ro.alex.client.managed.ui.desktop.ContDesktopListView;
import ro.alex.client.managed.ui.desktop.LocDesktopListView;
import ro.alex.client.managed.ui.desktop.OrasDesktopListView;
import ro.alex.client.managed.ui.desktop.PersoanaDesktopListView;
import ro.alex.client.managed.ui.desktop.RezervareDesktopListView;
import ro.alex.client.managed.ui.desktop.UserDesktopListView;
import ro.alex.client.managed.ui.desktop.ZborDesktopListView;
import ro.alex.client.managed.ui.mobile.AbonamentMobileListView;
import ro.alex.client.managed.ui.mobile.AeroportMobileListView;
import ro.alex.client.managed.ui.mobile.AvionMobileListView;
import ro.alex.client.managed.ui.mobile.ClasaMobileListView;
import ro.alex.client.managed.ui.mobile.CompanieMobileListView;
import ro.alex.client.managed.ui.mobile.ContMobileListView;
import ro.alex.client.managed.ui.mobile.LocMobileListView;
import ro.alex.client.managed.ui.mobile.OrasMobileListView;
import ro.alex.client.managed.ui.mobile.PersoanaMobileListView;
import ro.alex.client.managed.ui.mobile.RezervareMobileListView;
import ro.alex.client.managed.ui.mobile.UserMobileListView;
import ro.alex.client.managed.ui.mobile.ZborMobileListView;
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

public abstract class ApplicationMasterActivities_Roo_Gwt implements ActivityMapper {

    protected ApplicationRequestFactory requests;

    protected PlaceController placeController;

    public Activity getActivity(Place place) {
        if (!(place instanceof ProxyListPlace)) {
            return null;
        }
        ProxyListPlace listPlace = (ProxyListPlace) place;
        return new ApplicationEntityTypesProcessor<Activity>() {

            @Override
            public void handleAbonament(AbonamentProxy isNull) {
                setResult(new AbonamentListActivity(requests, ScaffoldApp.isMobile() ? AbonamentMobileListView.instance() : AbonamentDesktopListView.instance(), placeController));
            }

            @Override
            public void handleAeroport(AeroportProxy isNull) {
                setResult(new AeroportListActivity(requests, ScaffoldApp.isMobile() ? AeroportMobileListView.instance() : AeroportDesktopListView.instance(), placeController));
            }

            @Override
            public void handleAvion(AvionProxy isNull) {
                setResult(new AvionListActivity(requests, ScaffoldApp.isMobile() ? AvionMobileListView.instance() : AvionDesktopListView.instance(), placeController));
            }

            @Override
            public void handleClasa(ClasaProxy isNull) {
                setResult(new ClasaListActivity(requests, ScaffoldApp.isMobile() ? ClasaMobileListView.instance() : ClasaDesktopListView.instance(), placeController));
            }

            @Override
            public void handleCompanie(CompanieProxy isNull) {
                setResult(new CompanieListActivity(requests, ScaffoldApp.isMobile() ? CompanieMobileListView.instance() : CompanieDesktopListView.instance(), placeController));
            }

            @Override
            public void handleCont(ContProxy isNull) {
                setResult(new ContListActivity(requests, ScaffoldApp.isMobile() ? ContMobileListView.instance() : ContDesktopListView.instance(), placeController));
            }

            @Override
            public void handleLoc(LocProxy isNull) {
                setResult(new LocListActivity(requests, ScaffoldApp.isMobile() ? LocMobileListView.instance() : LocDesktopListView.instance(), placeController));
            }

            @Override
            public void handleOras(OrasProxy isNull) {
                setResult(new OrasListActivity(requests, ScaffoldApp.isMobile() ? OrasMobileListView.instance() : OrasDesktopListView.instance(), placeController));
            }

            @Override
            public void handlePersoana(PersoanaProxy isNull) {
                setResult(new PersoanaListActivity(requests, ScaffoldApp.isMobile() ? PersoanaMobileListView.instance() : PersoanaDesktopListView.instance(), placeController));
            }

            @Override
            public void handleRezervare(RezervareProxy isNull) {
                setResult(new RezervareListActivity(requests, ScaffoldApp.isMobile() ? RezervareMobileListView.instance() : RezervareDesktopListView.instance(), placeController));
            }

            @Override
            public void handleUser(UserProxy isNull) {
                setResult(new UserListActivity(requests, ScaffoldApp.isMobile() ? UserMobileListView.instance() : UserDesktopListView.instance(), placeController));
            }

            @Override
            public void handleZbor(ZborProxy isNull) {
                setResult(new ZborListActivity(requests, ScaffoldApp.isMobile() ? ZborMobileListView.instance() : ZborDesktopListView.instance(), placeController));
            }
        }.process(listPlace.getProxyClass());
    }
}