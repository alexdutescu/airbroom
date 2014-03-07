package ro.alex.client.managed.ui;
import java.util.Collection;
import java.util.List;
import ro.alex.client.proxy.UserProxy;
import ro.alex.client.scaffold.place.ProxyEditView;

public interface UserEditView<V extends ProxyEditView<UserProxy, V>> extends ProxyEditView<UserProxy, V> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyEditView.Delegate {
    }
}
