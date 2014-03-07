package ro.alex.client.managed.ui;
import ro.alex.client.proxy.ContProxy;
import ro.alex.client.scaffold.place.ProxyDetailsView;

public interface ContDetailsView extends ProxyDetailsView<ContProxy> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyDetailsView.Delegate {
    }
}
