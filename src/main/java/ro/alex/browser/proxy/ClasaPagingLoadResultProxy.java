package ro.alex.browser.proxy;

import java.util.List;

import ro.alex.client.proxy.ClasaProxy;

import com.google.web.bindery.requestfactory.shared.ProxyForName;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@ProxyForName(value = "ro.alex.server.model.paginare.ClasaPagingLoadResult")
public interface ClasaPagingLoadResultProxy extends ValueProxy, PagingLoadResult<ClasaProxy> {
	
	@Override
    List<ClasaProxy> getData();


    @Override
    int getOffset();


    @Override
    int getTotalLength();


    @Override
    void setOffset(int i);


    @Override
    void setTotalLength(int i);
}
