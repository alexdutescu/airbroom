package ro.alex.client.managed.ui;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.UserProxy;
import ro.alex.client.scaffold.place.ProxyDetailsView;

public interface PersoanaDetailsView extends ProxyDetailsView<PersoanaProxy> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyDetailsView.Delegate {
    }
}
