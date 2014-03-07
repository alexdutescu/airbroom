package ro.alex.client.managed.ui;
import java.util.Collection;
import java.util.List;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.scaffold.place.ProxyEditView;

public interface AvionEditView<V extends ProxyEditView<AvionProxy, V>> extends ProxyEditView<AvionProxy, V> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyEditView.Delegate {
    }
}
