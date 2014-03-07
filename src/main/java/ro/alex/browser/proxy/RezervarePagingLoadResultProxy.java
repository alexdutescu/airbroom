package ro.alex.browser.proxy;

import java.util.List;

import ro.alex.client.proxy.RezervareProxy;

import com.google.web.bindery.requestfactory.shared.ProxyForName;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@ProxyForName(value = "ro.alex.server.model.paginare.RezervarePagingLoadResult")
public interface RezervarePagingLoadResultProxy extends ValueProxy, PagingLoadResult<RezervareProxy> {


    @Override
    List<RezervareProxy> getData();

    @Override
    int getOffset();


    @Override
    int getTotalLength();


    @Override
    void setOffset(int i);


    @Override
    void setTotalLength(int i);
}
