package ro.alex.client.managed.ui;
import java.util.Collection;
import java.util.List;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.scaffold.place.ProxyEditView;

public interface ClasaEditView<V extends ProxyEditView<ClasaProxy, V>> extends ProxyEditView<ClasaProxy, V> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyEditView.Delegate {
    }
}
