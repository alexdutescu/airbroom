package ro.alex.shared.scaffold;

import java.util.List;

import ro.alex.browser.proxy.ClasaPagingLoadResultProxy;
import ro.alex.browser.proxy.RezervarePagingLoadResultProxy;
import ro.alex.browser.proxy.ZboruriPagingLoadResultProxy;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.LocProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.RezervareProxy;
import ro.alex.client.proxy.UserProxy;
import ro.alex.client.proxy.ZborProxy;

import com.google.web.bindery.requestfactory.shared.LoggingRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.ServiceName;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterConfig;

/**
 * The base request factory interface for this app. Add
 * new custom request types here without fear of them
 * being managed away by Roo.
 */
public interface ScaffoldRequestFactory extends RequestFactory {
	
	@ServiceName("ro.alex.server.model.Zbor")
	public interface ZborPagingLoadRequest extends RequestContext{
		Request<ZboruriPagingLoadResultProxy> findAllZborsPaginat(int offset, int limit, List<? extends SortInfo> sortInfo);
		Request<ZboruriPagingLoadResultProxy> sortFlights(int offset, int limit, List<? extends SortInfo> sortInfo, int orasp, int orasd, String data);
		Request<ZboruriPagingLoadResultProxy> findAllZborsPF(int offset, int limit, List<? extends SortInfo> sortInfo, List<? extends FilterConfig> filterConfig);
		Request<List<ZborProxy>> getHours(long orasp, long orasd, String data);
	}
	
	@ServiceName("ro.alex.server.model.Clasa")
	public interface ClasaPagingLoadRequest extends RequestContext{
		Request<ClasaPagingLoadResultProxy> findAllClasePaginat(int offset, int limit);
	}
	
	@ServiceName("ro.alex.server.model.Abonament")
	public interface AbonamentScaffoldRequest extends RequestContext{
		Request<AbonamentProxy> findAbonamentBySerie(String string);
	}
	
	@ServiceName("ro.alex.server.model.User")
	public interface UserScaffoldRequest extends RequestContext{
		Request<UserProxy> validateAuth(String user, String parola);
	}
	
	@ServiceName("ro.alex.server.model.Rezervare")
	public interface RezervareScaffoldRequest extends RequestContext{
		Request<String> stringGenerator();
		Request<Double> calculPret(String clasa);
		Request<RezervareProxy> getRezervareByCod(String cod);
	}
	
	@ServiceName("ro.alex.server.model.Rezervare")
	public interface RezervarePagingLoadRequest extends RequestContext{
		Request<RezervarePagingLoadResultProxy> findAllRezervariPaginat(int offset, int limit, List<? extends SortInfo> sortInfo, List<? extends FilterConfig> filterConfig);
	}
	
	@ServiceName("ro.alex.server.model.Loc")
	public interface LocScaffoldRequest extends RequestContext{
		Request<LocProxy> getLocLiber(Long clasaId, Long avionId, Long zborId);
	}
	
	@ServiceName("ro.alex.server.model.Persoana")
	public interface PersoanaScaffoldRequest extends RequestContext{
		Request<PersoanaProxy> getPersoanaByUserId(long id);
	}
	
	
    ZborPagingLoadRequest 		zborPagingRequest();
    ClasaPagingLoadRequest 		clasaPagingRequest();
    AbonamentScaffoldRequest 	abonamentScaffoldRequest();
    UserScaffoldRequest 		userScaffoldRequest();
    RezervareScaffoldRequest 	rezervareScaffoldRequest();
    LocScaffoldRequest 			locScaffoldRequest();
    PersoanaScaffoldRequest 	persoanaScaffoldRequest();
    RezervarePagingLoadRequest rezervarePagingLoadRequest();
	
	/**
	 * Return a GWT logging request.
	 */
	LoggingRequest loggingRequest();
}
