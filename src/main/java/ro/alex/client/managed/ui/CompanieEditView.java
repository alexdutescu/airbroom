package ro.alex.client.managed.ui;
import java.util.Collection;
import java.util.List;
import ro.alex.client.proxy.CompanieProxy;
import ro.alex.client.scaffold.place.ProxyEditView;

public interface CompanieEditView<V extends ProxyEditView<CompanieProxy, V>> extends ProxyEditView<CompanieProxy, V> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyEditView.Delegate {
    }
}
