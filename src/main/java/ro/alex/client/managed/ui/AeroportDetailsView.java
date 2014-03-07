package ro.alex.client.managed.ui;
import ro.alex.client.proxy.AeroportProxy;
import ro.alex.client.scaffold.place.ProxyDetailsView;

public interface AeroportDetailsView extends ProxyDetailsView<AeroportProxy> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyDetailsView.Delegate {
    }
}
