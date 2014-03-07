package ro.alex.client.managed.ui;
import java.util.Collection;
import java.util.List;
import ro.alex.client.proxy.LocProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.RezervareProxy;
import ro.alex.client.proxy.ZborProxy;
import ro.alex.client.scaffold.place.ProxyEditView;

public interface RezervareEditView<V extends ProxyEditView<RezervareProxy, V>> extends ProxyEditView<RezervareProxy, V> {

    void setDelegate(Delegate delegate);

    interface Delegate extends ro.alex.client.scaffold.place.ProxyEditView.Delegate {
    }
}
