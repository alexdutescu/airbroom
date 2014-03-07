package ro.alex.client.managed.ui;
import java.util.Collection;
import java.util.List;
import ro.alex.client.proxy.OrasProxy;
import ro.alex.client.scaffold.place.ProxyEditView;

public interface OrasEditView<V extends ProxyEditView<OrasProxy, V>> extends ProxyEditView<OrasProxy, V> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyEditView.Delegate {
    }
}
