package ro.alex.server.model.paginare;

import java.util.List;

import ro.alex.server.model.Rezervare;

import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

public class RezervarePagingLoadResult extends PagingLoadResultBean<Rezervare>{

	protected RezervarePagingLoadResult(){
		super();
	}
	
	public RezervarePagingLoadResult(List<Rezervare> rezervari, int totalLength, int offset){
		super(rezervari, totalLength, offset);
	}
}