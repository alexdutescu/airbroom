package ro.alex.browser.ui.view;

import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.request.AbonamentRequest;
import ro.alex.client.request.PersoanaRequest;
import ro.alex.shared.scaffold.ScaffoldRequestFactory.RezervareScaffoldRequest;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.sencha.gxt.core.client.util.DelayedTask;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.InvalidEvent;
import com.sencha.gxt.widget.core.client.event.InvalidEvent.InvalidHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.RegExValidator;
import com.sencha.gxt.widget.core.client.form.validator.RegExValidator.RegExMessages;
import com.sencha.gxt.widget.core.client.info.Info;
//TO BE CONTINUED 
public class CreateAbonamentFormView implements IsWidget, SelectHandler{

	private VerticalPanel v;
	private TextField nume, prenume;
	private NumberField<Integer> kilometri;
	private TextArea adresa;
	private final ApplicationRequestFactory requestFactory;
	private VerticalLayoutContainer con;
	private PersoanaProxy pp;
	private TextButton btnAbonament;
	
	public CreateAbonamentFormView(ApplicationRequestFactory requestFactory, VerticalLayoutContainer con, PersoanaProxy pp, TextButton tb){
		this.requestFactory = requestFactory;
		this.con = con;
		this.pp = pp;
		this.btnAbonament = tb;
	}
	@Override
	public Widget asWidget() {
		v = new VerticalPanel();
		createForm();
		return v;
	}
	
	private void createForm(){
		FramedPanel panel = new FramedPanel();
		panel.setHeadingText("Furnizare abonament");
		panel.setWidth(350);
		panel.setBodyStyle("background: none; padding: 5px");

		VerticalLayoutContainer p = new VerticalLayoutContainer();
		panel.add(p);
		
		RegExValidator validator = new RegExValidator("^[a-zA-Z]+$");
		validator.setMessages(new RegExMessages() {
			
			@Override
			public String regExMessage() {
				return "Nu aveti voie sa introduceti decat litere";
			}
		});
		
		nume = new TextField();
		nume.setAllowBlank(false);
		nume.setEmptyText("Introduceti numele...");
		nume.addValidator(validator);
		nume.addInvalidHandler(new InvalidHandler() {
			
			@Override
			public void onInvalid(InvalidEvent event) {
				final TextField tf = (TextField) event.getSource();
				DelayedTask dt = new DelayedTask() {
					
					@Override
					public void onExecute() {
						tf.clear();
						tf.redraw();
					}
				};
				dt.delay(5000);
			}
		});
		p.add(new FieldLabel(nume, "Nume"), new VerticalLayoutData(1, -1));
		
		prenume = new TextField();
		prenume.setAllowBlank(false);
		prenume.setEmptyText("Introduceti prenumele...");
		prenume.addValidator(validator);
		prenume.addInvalidHandler(new InvalidHandler() {
			
			@Override
			public void onInvalid(InvalidEvent event) {
				final TextField tf = (TextField) event.getSource();
				DelayedTask dt = new DelayedTask() {
					
					@Override
					public void onExecute() {
						tf.clear();
						tf.redraw();
					}
				};
				dt.delay(5000);
			}
		});
		p.add(new FieldLabel(prenume, "Prenume"), new VerticalLayoutData(1, -1));
		
		kilometri = new NumberField<Integer>(new IntegerPropertyEditor());
		p.add(new FieldLabel(kilometri, "Numar de kilometri"));
		
		adresa = new TextArea();
		adresa.setAllowBlank(false);
		adresa.addValidator(new MinLengthValidator(10));
		p.add(new FieldLabel(adresa, "Adresa"), new VerticalLayoutData(1, 100));

		panel.addButton(new TextButton("Salveaza", this));
		panel.addButton(new TextButton("Anuleaza", this));
		
		v.add(panel);
	}
	
	private void doSaveThingy(){
		final AbonamentRequest aRequest = requestFactory.abonamentRequest();
		final AbonamentProxy ap = aRequest.create(AbonamentProxy.class);
		
		RezervareScaffoldRequest rezervareScaffoldRequest = requestFactory.rezervareScaffoldRequest();
		
		rezervareScaffoldRequest.stringGenerator().fire(new Receiver<String>() {

			@Override
			public void onSuccess(String cod) {
				double cost = (Integer)kilometri.getValue() * 2;
				ap.setSerie(cod);
				ap.setKilometri((Integer)kilometri.getValue());
				ap.setCost(cost);
				
				aRequest.persist().using(ap).fire(new Receiver<Void>() {

					@Override
					public void onSuccess(Void response) {
						Info.display("Succes", "Abonament salvat");
						PersoanaRequest pReq = requestFactory.persoanaRequest();
						PersoanaProxy persoanaEdit = pReq.edit(pp);
						persoanaEdit.setAbonament(ap);
						
						pReq.persist().using(persoanaEdit).with("abonament").fire(new Receiver<Void>() {

							@Override
							public void onSuccess(Void response) {
								Info.display("Succes", "Abonament adaugat la persoana");
								btnAbonament.setText("Abonament");
							}
							@Override
							public void onFailure(ServerFailure sf){
								Window.alert("Asociere abonament persoana a crapat cu mesajul: "+ sf.getMessage());
							}
						});
						con.remove(v);
						
					}
					@Override
					public void onFailure(ServerFailure sf){
						Window.alert("Abonamentul nu a fost salvat, eroare: "+ sf.getMessage());
					}
				});
			}
		});
	}
	
	@Override
	public void onSelect(SelectEvent event) {
		TextButton tb = (TextButton) event.getSource();
		if(tb.getText() == "Salveaza") doSaveThingy();
		else {
			con.remove(v);//clear widget
			
		}
	}

}
