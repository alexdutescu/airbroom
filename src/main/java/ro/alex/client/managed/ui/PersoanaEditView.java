package ro.alex.client.managed.ui;
import java.util.Collection;
import java.util.List;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.UserProxy;
import ro.alex.client.scaffold.place.ProxyEditView;

public interface PersoanaEditView<V extends ProxyEditView<PersoanaProxy, V>> extends ProxyEditView<PersoanaProxy, V> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyEditView.Delegate {
    }
}
