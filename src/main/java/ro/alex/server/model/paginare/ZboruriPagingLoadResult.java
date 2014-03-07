package ro.alex.server.model.paginare;

import java.util.List;

import ro.alex.server.model.Zbor;

import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

public class ZboruriPagingLoadResult extends PagingLoadResultBean<Zbor>{

	protected ZboruriPagingLoadResult(){
		super();
	}
	
	public ZboruriPagingLoadResult(List<Zbor> zboruri, int totalLength, int offset){
		super(zboruri, totalLength, offset);
	}
}