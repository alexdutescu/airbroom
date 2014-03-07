package ro.alex.client.managed.ui;
import ro.alex.client.proxy.CompanieProxy;
import ro.alex.client.scaffold.place.ProxyDetailsView;

public interface CompanieDetailsView extends ProxyDetailsView<CompanieProxy> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyDetailsView.Delegate {
    }
}
