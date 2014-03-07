package ro.alex.client.managed.ui;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.scaffold.place.ProxyDetailsView;

public interface AbonamentDetailsView extends ProxyDetailsView<AbonamentProxy> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyDetailsView.Delegate {
    }
}
