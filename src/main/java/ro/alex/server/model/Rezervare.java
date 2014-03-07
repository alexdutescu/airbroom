package ro.alex.server.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.sencha.gxt.data.shared.SortInfoBean;
import com.sencha.gxt.data.shared.loader.FilterConfigBean;

import ro.alex.server.model.paginare.RezervarePagingLoadResult;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Rezervare {

//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="zbor_id")
//	Zbor zbor;
//
//	@GeneratedValue(strategy=GenerationType.SEQUENCE)
//	private Long cod;
//
//	private String modPlata;
//
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="clasa_id")
//	private Clasa clasa;
//
//	private BigDecimal pretTotal;
	
	private Double cost;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="locDus_id")
	private Loc locDus;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="locIntors_id")
	private Loc locIntors;
	
	private String nume, prenume, cod; 

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="zborDusId")
	private Zbor zborDus;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="zborIntorsId")
	private Zbor zborIntors;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="persoana_id")
	private Persoana persoana;
	
	private Boolean dusIntors;
	
	public static String stringGenerator()	{
		return RandomStringUtils.random(8, true, true);
	}

	
	
	public static Double calculPret(String clasa){
		Double bd = null;

		if(clasa != null){
			if("economic".equals(clasa)) bd = new Double(50);
			if("business".equals(clasa)) bd =  new Double(100);
			if("extra".equals(clasa)) bd = new Double(150);
		}
		else return null;
		return bd;
	}
	
	public static Rezervare getRezervareByCod(String cod){
		return entityManager().createQuery("SELECT o FROM Rezervare o WHERE cod=:cod",Rezervare.class).setParameter("cod", cod).getResultList().get(0);
	}
	
	public static RezervarePagingLoadResult findAllRezervariPaginat(int offset, int limit, List<? extends SortInfoBean> sortInfo, List<FilterConfigBean> filterConfig){
		List<Rezervare> rezervari = findAllRezervares();
		//to do filtrare si sortare
		ArrayList<Rezervare> sublist = new ArrayList<Rezervare>();
		int start = offset;
		int actualLimit = rezervari.size();
		if (limit > 0) {
			actualLimit = Math.min(start + limit, actualLimit);
		}
		for (int i = offset; i < actualLimit; i++) {
			sublist.add(rezervari.get(i));
		}
		return new RezervarePagingLoadResult(rezervari, rezervari.size(), offset);
	}
}
