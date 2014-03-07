package ro.alex.browser.proxy;

import java.util.List;

import ro.alex.client.proxy.ZborProxy;
import ro.alex.server.model.paginare.ZboruriPagingLoadResult;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ProxyForName;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@ProxyForName(value = "ro.alex.server.model.paginare.ZboruriPagingLoadResult")
public interface ZboruriPagingLoadResultProxy extends ValueProxy, PagingLoadResult<ZborProxy> {


    @Override
    List<ZborProxy> getData();

    @Override
    int getOffset();


    @Override
    int getTotalLength();


    @Override
    void setOffset(int i);


    @Override
    void setTotalLength(int i);
}
