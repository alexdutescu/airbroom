package ro.alex.server.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Loc {
	
	private Integer numar;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "clasa_id")
	Clasa clasa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "avion_id")
	Avion avion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "zbor_id")
	Zbor zbor;
	
	private Boolean ocupat;
	
	
	public static Loc getLocLiber(Long clasaId, Long avionId, Long zborId){
		return entityManager().createQuery("SELECT o FROM Loc o WHERE clasa_id="+ clasaId +" AND avion_id="+ avionId +
				" AND zbor_id="+ zborId +" AND ocupat=0", Loc.class).getResultList().get(0);
	}
}
