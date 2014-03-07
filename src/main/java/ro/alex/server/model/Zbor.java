package ro.alex.server.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import ro.alex.server.model.paginare.ZboruriPagingLoadResult;

import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.SortInfoBean;
import com.sencha.gxt.data.shared.loader.FilterConfigBean;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Zbor {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avion_id")
	private Avion avion;

	private String cod;

	private Integer numarKm;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date data;

	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY, targetEntity=Oras.class)
	@JoinColumn(name="oraspId")
	private Oras orasp;

	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY, targetEntity=Oras.class)
	@JoinColumn(name="orasdId")
	private Oras orasd;

	private Boolean deschis, escala; 



	public static ZboruriPagingLoadResult findAllZborsPaginat(int offset, int limit, List<? extends SortInfoBean> sortInfo){

		////  	String sql = "SELECT o FROM Zbor o WHERE data_plecare BETWEEN NOW() AND DATEADD(month, +2, GETDATE())";
		//  	
		////	List<Zbor> zboruri = entityManager().createQuery(sql, Zbor.class).getResultList();
		List<Zbor> zboruri = findAllZbors();


		//  	if (sortInfo.size() > 0) {
		//		SortInfo sort = sortInfo.get(0);
		//		if (sort.getSortField() != null) {
		//			final String sortField = sort.getSortField();
		//			if (sortField != null) {
		//				Collections.sort(zboruri, sort.getSortDir().comparator(new Comparator<Zbor>() {
		//					public int compare(Zbor p1 , Zbor p2) {
		//						
		//						if (sortField.equals("numarLocuri")) {//aici trebuie bibilit ??
		//							return p1.getNumarLocuri().compareTo(p2.getNumarLocuri());
		//						}
		//						return 0;
		//					}
		//				}));
		//			}
		//		}
		//	}

		ArrayList<Zbor> sublist = new ArrayList<Zbor>();
		int start = offset;
		int actualLimit = zboruri.size();
		if (limit > 0) {
			actualLimit = Math.min(start + limit, actualLimit);
		}
		for (int i = offset; i < actualLimit; i++) {
			sublist.add(zboruri.get(i));
		}
		return new ZboruriPagingLoadResult(sublist, zboruri.size(), offset);
	}

	public static ZboruriPagingLoadResult sortFlights(int offset, int limit, List<? extends SortInfoBean> sortInfo, int orasp, int orasd, String data){//adauga param necesari, dar intai creeaza un sql de proba
		String sql;
		//		if(datai == null || datai.length()==0){
		//			sql = "SELECT o FROM Zbor o WHERE o.orasp_id=:pId AND o.orasd_id=:dId AND DATE(o.data)=:datap";
		//		}
		//		else sql = "SELECT o FROM Zbor o WHERE o.orasp_id=:pId AND o.orasd_id=:dId AND DATE(o.data)=:datap" +
		//											"OR o.orasp_id=:dId AND o.orasd_id=:pId AND DATE(o.data)=:datai";
		//		sql = "SELECT o FROM Zbor o";

		//		List<Zbor> zboruri = entityManager().createQuery("SELECT o FROM Zbor o WHERE orasp_id=:orasp AND orasd_id=:orasd AND DATE(data)='"+ data +"'", Zbor.class)
		//													.setParameter("orasp", orasp)
		//													.setParameter("orasd", orasd)
		////													.setParameter("data", (String)data)
		//													.getResultList();
		System.out.println(orasp +" -- "+ orasd + " -- "+ data);

		List<Zbor> zboruri = entityManager().createQuery("SELECT o FROM Zbor o WHERE orasp_id="+ orasp +" AND orasd_id="+ orasd +" AND DATE(data)='"+ data +"'", Zbor.class).getResultList();
		//		List<Zbor> zboruri = findAllZbors();

		ArrayList<Zbor> sublist = new ArrayList<Zbor>();
		int start = offset;
		int actualLimit = zboruri.size();
		if (limit > 0) {
			actualLimit = Math.min(start + limit, actualLimit);
		}
		for (int i = offset; i < actualLimit; i++) {
			sublist.add(zboruri.get(i));
		}
		return new ZboruriPagingLoadResult(sublist, zboruri.size(), offset);
	}

	public static List<Zbor> getHours(long orasp, long orasd, String data){
		return entityManager().createQuery("SELECT o FROM Zbor o WHERE orasp_id='"+ (int)orasp +"' AND orasd_id='"+ (int)orasd +"' AND DATE(data)='"+ data +"'", Zbor.class).getResultList();
	}

	public static ZboruriPagingLoadResult findAllZborsPF(int offset, int limit, List<? extends SortInfoBean> sortInfo, List<FilterConfigBean> filterConfig){
		List<Zbor> zboruri = findAllZbors();

		if (filterConfig != null && filterConfig.size() != 0) {
			item: for (int i = zboruri.size() - 1; i >= 0; i--) {
				Zbor p = zboruri.get(i);
				for (FilterConfigBean f : filterConfig) {
					if (f.getField().equals("cod")) {
						if (!p.getCod().contains(f.getValue())) {
							zboruri.remove(i);
							continue item;
						}
					} 
					else if (f.getField().equals("orasp.denumire")) {
						if (!p.getOrasp().getDenumire().contains(f.getValue())) {
							zboruri.remove(i);
							continue item;
						}
					}
					else if (f.getField().equals("orasd.denumire")) {
						if (!p.getOrasd().getDenumire().contains(f.getValue())) {
							zboruri.remove(i);
							continue item;
						}
					}
				}
			}
		}
		if (sortInfo.size() > 0) {
			SortInfo sort = sortInfo.get(0);
			if (sort.getSortField() != null) {
				final String sortField = sort.getSortField();
				if (sortField != null) {
					Collections.sort(zboruri, sort.getSortDir().comparator(new Comparator<Zbor>() {
						public int compare(Zbor p1, Zbor p2) {
							if (sortField.equals("cod")) {//aici trebuie bibilit ??
								return p1.getCod().compareTo(p2.getCod());
							}else if (sortField.equals("orasp.denumire")) {
				                return p1.getOrasp().getDenumire().compareTo(p2.getOrasp().getDenumire());
				              }else if (sortField.equals("orasd.denumire")) {
				                return p1.getOrasd().getDenumire().compareTo(p2.getOrasd().getDenumire());
				              }
							return 0;
						}
					}));
				}
			}
		}

		ArrayList<Zbor> sublist = new ArrayList<Zbor>();
		int start = offset;
		int actualLimit = zboruri.size();
		if (limit > 0) {
			actualLimit = Math.min(start + limit, actualLimit);
		}
		for (int i = offset; i < actualLimit; i++) {
			sublist.add(zboruri.get(i));
		}
		return new ZboruriPagingLoadResult(sublist, zboruri.size(), offset);
	}
}
