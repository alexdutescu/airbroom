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
public class Persoana {
	
	private String nume;  
	
	private String prenume;
	
	private String adresa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="abonament_id")
	Abonament abonament;
	
//	public static Persoana validateAuth(String nick, String parola){
//	return	entityManager().createQuery("SELECT o FROM Persoana o WHERE nick = :nick AND parola = :parola", Persoana.class)
//						.setParameter("nick", nick)
//						.setParameter("parola", parola)
//						.getSingleResult();
//	}
	public static Persoana getPersoanaByUserId(long uId){
		return entityManager().createQuery("SELECT o FROM Persoana o WHERE user_id=:id", Persoana.class).setParameter("id", uId).getSingleResult();
	}
}
