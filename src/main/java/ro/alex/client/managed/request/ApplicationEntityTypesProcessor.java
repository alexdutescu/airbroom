// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.managed.request;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.AeroportProxy;
import ro.alex.client.proxy.AvionProxy;
import ro.alex.client.proxy.ClasaProxy;
import ro.alex.client.proxy.CompanieProxy;
import ro.alex.client.proxy.ContProxy;
import ro.alex.client.proxy.LocProxy;
import ro.alex.client.proxy.OrasProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.RezervareProxy;
import ro.alex.client.proxy.UserProxy;
import ro.alex.client.proxy.ZborProxy;

public abstract class ApplicationEntityTypesProcessor<T> {

    private final T defaultValue;

    private T result;

    public ApplicationEntityTypesProcessor() {
        defaultValue = null;
    }

    public ApplicationEntityTypesProcessor(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public static Set<Class<? extends com.google.web.bindery.requestfactory.shared.EntityProxy>> getAll() {
        Set<Class<? extends EntityProxy>> rtn = new HashSet<Class<? extends EntityProxy>>();
        rtn.add(AbonamentProxy.class);
        rtn.add(AeroportProxy.class);
        rtn.add(AvionProxy.class);
        rtn.add(ClasaProxy.class);
        rtn.add(CompanieProxy.class);
        rtn.add(ContProxy.class);
        rtn.add(LocProxy.class);
        rtn.add(OrasProxy.class);
        rtn.add(PersoanaProxy.class);
        rtn.add(RezervareProxy.class);
        rtn.add(UserProxy.class);
        rtn.add(ZborProxy.class);
        return Collections.unmodifiableSet(rtn);
    }

    private static void process(ApplicationEntityTypesProcessor<?> processor, Class<?> clazz) {
        if (AbonamentProxy.class.equals(clazz)) {
            processor.handleAbonament((AbonamentProxy) null);
            return;
        }
        if (AeroportProxy.class.equals(clazz)) {
            processor.handleAeroport((AeroportProxy) null);
            return;
        }
        if (AvionProxy.class.equals(clazz)) {
            processor.handleAvion((AvionProxy) null);
            return;
        }
        if (ClasaProxy.class.equals(clazz)) {
            processor.handleClasa((ClasaProxy) null);
            return;
        }
        if (CompanieProxy.class.equals(clazz)) {
            processor.handleCompanie((CompanieProxy) null);
            return;
        }
        if (ContProxy.class.equals(clazz)) {
            processor.handleCont((ContProxy) null);
            return;
        }
        if (LocProxy.class.equals(clazz)) {
            processor.handleLoc((LocProxy) null);
            return;
        }
        if (OrasProxy.class.equals(clazz)) {
            processor.handleOras((OrasProxy) null);
            return;
        }
        if (PersoanaProxy.class.equals(clazz)) {
            processor.handlePersoana((PersoanaProxy) null);
            return;
        }
        if (RezervareProxy.class.equals(clazz)) {
            processor.handleRezervare((RezervareProxy) null);
            return;
        }
        if (UserProxy.class.equals(clazz)) {
            processor.handleUser((UserProxy) null);
            return;
        }
        if (ZborProxy.class.equals(clazz)) {
            processor.handleZbor((ZborProxy) null);
            return;
        }
        processor.handleNonProxy(null);
    }

    private static void process(ApplicationEntityTypesProcessor<?> processor, Object proxy) {
        if (proxy instanceof AbonamentProxy) {
            processor.handleAbonament((AbonamentProxy) proxy);
            return;
        }
        if (proxy instanceof AeroportProxy) {
            processor.handleAeroport((AeroportProxy) proxy);
            return;
        }
        if (proxy instanceof AvionProxy) {
            processor.handleAvion((AvionProxy) proxy);
            return;
        }
        if (proxy instanceof ClasaProxy) {
            processor.handleClasa((ClasaProxy) proxy);
            return;
        }
        if (proxy instanceof CompanieProxy) {
            processor.handleCompanie((CompanieProxy) proxy);
            return;
        }
        if (proxy instanceof ContProxy) {
            processor.handleCont((ContProxy) proxy);
            return;
        }
        if (proxy instanceof LocProxy) {
            processor.handleLoc((LocProxy) proxy);
            return;
        }
        if (proxy instanceof OrasProxy) {
            processor.handleOras((OrasProxy) proxy);
            return;
        }
        if (proxy instanceof PersoanaProxy) {
            processor.handlePersoana((PersoanaProxy) proxy);
            return;
        }
        if (proxy instanceof RezervareProxy) {
            processor.handleRezervare((RezervareProxy) proxy);
            return;
        }
        if (proxy instanceof UserProxy) {
            processor.handleUser((UserProxy) proxy);
            return;
        }
        if (proxy instanceof ZborProxy) {
            processor.handleZbor((ZborProxy) proxy);
            return;
        }
        processor.handleNonProxy(proxy);
    }

    public void handleNonProxy(Object object) {
    }

    public abstract void handleAbonament(AbonamentProxy proxy);

    public abstract void handleAeroport(AeroportProxy proxy);

    public abstract void handleAvion(AvionProxy proxy);

    public abstract void handleClasa(ClasaProxy proxy);

    public abstract void handleCompanie(CompanieProxy proxy);

    public abstract void handleCont(ContProxy proxy);

    public abstract void handleLoc(LocProxy proxy);

    public abstract void handleOras(OrasProxy proxy);

    public abstract void handlePersoana(PersoanaProxy proxy);

    public abstract void handleRezervare(RezervareProxy proxy);

    public abstract void handleUser(UserProxy proxy);

    public abstract void handleZbor(ZborProxy proxy);

    public T process(Class<?> clazz) {
        setResult(defaultValue);
        ApplicationEntityTypesProcessor.process(this, clazz);
        return result;
    }

    public T process(Object proxy) {
        setResult(defaultValue);
        ApplicationEntityTypesProcessor.process(this, proxy);
        return result;
    }

    protected void setResult(T result) {
        this.result = result;
    }
}