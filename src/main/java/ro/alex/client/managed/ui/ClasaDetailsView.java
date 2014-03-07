package ro.alex.client.managed.ui;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.scaffold.place.ProxyDetailsView;

public interface ClasaDetailsView extends ProxyDetailsView<ClasaProxy> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyDetailsView.Delegate {
    }
}
