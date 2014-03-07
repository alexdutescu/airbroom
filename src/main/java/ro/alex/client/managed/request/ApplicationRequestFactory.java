// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ro.alex.client.managed.request;
import ro.alex.client.request.AbonamentRequest;
import ro.alex.client.request.AeroportRequest;
import ro.alex.client.request.AvionRequest;
import ro.alex.client.request.ClasaRequest;
import ro.alex.client.request.CompanieRequest;
import ro.alex.client.request.ContRequest;
import ro.alex.client.request.LocRequest;
import ro.alex.client.request.OrasRequest;
import ro.alex.client.request.PersoanaRequest;
import ro.alex.client.request.RezervareRequest;
import ro.alex.client.request.UserRequest;
import ro.alex.client.request.ZborRequest;
import ro.alex.shared.scaffold.ScaffoldRequestFactory;

public interface ApplicationRequestFactory extends ScaffoldRequestFactory {

    AbonamentRequest abonamentRequest();

    AeroportRequest aeroportRequest();

    AvionRequest avionRequest();

    ClasaRequest clasaRequest();

    CompanieRequest companieRequest();

    ContRequest contRequest();

    LocRequest locRequest();

    OrasRequest orasRequest();

    PersoanaRequest persoanaRequest();

    RezervareRequest rezervareRequest();

    UserRequest userRequest();

    ZborRequest zborRequest();
}