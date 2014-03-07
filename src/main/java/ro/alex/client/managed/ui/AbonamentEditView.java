package ro.alex.client.managed.ui;
import java.util.Collection;
import java.util.List;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.scaffold.place.ProxyEditView;

public interface AbonamentEditView<V extends ProxyEditView<AbonamentProxy, V>> extends ProxyEditView<AbonamentProxy, V> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyEditView.Delegate {
    }
}
