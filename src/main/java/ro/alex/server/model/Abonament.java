package ro.alex.server.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Abonament {
	
	private String serie; 
	
	private Integer kilometri; 
	
	private Double cost; 
	
	public static Abonament findAbonamentBySerie(String serie){
		return entityManager().createQuery("SELECT o FROM Abonament o WHERE o.serie = :serie", Abonament.class).setParameter("serie", serie).getSingleResult();
	}

}
