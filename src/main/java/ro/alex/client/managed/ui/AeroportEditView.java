package ro.alex.client.managed.ui;
import java.util.Collection;
import java.util.List;
import ro.alex.client.proxy.AeroportProxy;
import ro.alex.client.scaffold.place.ProxyEditView;

public interface AeroportEditView<V extends ProxyEditView<AeroportProxy, V>> extends ProxyEditView<AeroportProxy, V> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyEditView.Delegate {
    }
}
