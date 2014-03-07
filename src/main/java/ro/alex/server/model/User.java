package ro.alex.server.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class User {

	private String nick;

	private String parola;

	public static User validateAuth(String nick, String parola){
		return	entityManager().createQuery("SELECT o FROM User o WHERE nick = :nick AND parola = :parola", User.class)
							.setParameter("nick", nick)
							.setParameter("parola", parola)
							.getSingleResult();
		}

}
