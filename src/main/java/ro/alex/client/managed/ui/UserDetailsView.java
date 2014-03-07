package ro.alex.client.managed.ui;
import ro.alex.client.proxy.UserProxy;
import ro.alex.client.scaffold.place.ProxyDetailsView;

public interface UserDetailsView extends ProxyDetailsView<UserProxy> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyDetailsView.Delegate {
    }
}
