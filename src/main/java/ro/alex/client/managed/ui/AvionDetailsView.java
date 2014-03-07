package ro.alex.client.managed.ui;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.scaffold.place.ProxyDetailsView;

public interface AvionDetailsView extends ProxyDetailsView<AvionProxy> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyDetailsView.Delegate {
    }
}
