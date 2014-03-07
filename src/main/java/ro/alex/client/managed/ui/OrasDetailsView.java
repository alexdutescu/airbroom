package ro.alex.client.managed.ui;
import ro.alex.client.proxy.OrasProxy;
import ro.alex.client.scaffold.place.ProxyDetailsView;

public interface OrasDetailsView extends ProxyDetailsView<OrasProxy> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyDetailsView.Delegate {
    }
}
