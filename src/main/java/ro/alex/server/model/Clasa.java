package ro.alex.server.model;

import java.util.List;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import ro.alex.server.model.paginare.ClasaPagingLoadResult;



@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Clasa {

	private String clasa;
	
	
	public static ClasaPagingLoadResult findAllClasePaginat(int offset, int limit){
		List<Clasa> clase = Clasa.findAllClasas();
		
		

//		ArrayList<Clasa> sublist = new ArrayList<Clasa>();
		int start = offset;
		int actualLimit = clase.size();
		if (limit > 0) {
			actualLimit = Math.min(start + limit, actualLimit);
		}
//		for (int i = offset; i < actualLimit; i++) {
//			sublist.add(clase.get(i));
//		}
		return new ClasaPagingLoadResult(clase, clase.size(), offset);
	}
}
