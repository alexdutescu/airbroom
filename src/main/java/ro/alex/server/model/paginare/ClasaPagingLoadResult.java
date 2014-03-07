package ro.alex.server.model.paginare;

import java.util.List;

import ro.alex.server.model.Clasa;

import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

public class ClasaPagingLoadResult extends PagingLoadResultBean<Clasa>{

	protected ClasaPagingLoadResult(){
		super();
	}
	
	public ClasaPagingLoadResult(List<Clasa> zboruri, int totalLength, int offset){
		super(zboruri, totalLength, offset);
	}
}