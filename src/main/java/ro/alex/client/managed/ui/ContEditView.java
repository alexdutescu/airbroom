package ro.alex.client.managed.ui;
import java.util.Collection;
import java.util.List;
import ro.alex.client.proxy.ContProxy;
import ro.alex.client.scaffold.place.ProxyEditView;

public interface ContEditView<V extends ProxyEditView<ContProxy, V>> extends ProxyEditView<ContProxy, V> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyEditView.Delegate {
    }
}
