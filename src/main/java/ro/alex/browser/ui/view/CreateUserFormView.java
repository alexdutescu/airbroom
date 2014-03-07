package ro.alex.browser.ui.view;

import ro.alex.browser.events.ClearCenterEvent;
import ro.alex.browser.events.LogInEvent;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.UserProxy;
import ro.alex.client.request.PersoanaRequest;
import ro.alex.client.request.UserRequest;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
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
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.RegExValidator;
import com.sencha.gxt.widget.core.client.form.validator.RegExValidator.RegExMessages;
import com.sencha.gxt.widget.core.client.info.Info;

public class CreateUserFormView implements IsWidget, SelectHandler{

	private ApplicationRequestFactory requestFactory;
	private EventBus eventBus;
	private VerticalPanel v;
	private TextField nume, prenume, nick;
	private PasswordField parola, confirmaParola;
	private TextArea description;

	@Inject
	public CreateUserFormView(ApplicationRequestFactory requestFactory, EventBus eventBus){
		this.requestFactory = requestFactory;
		this.eventBus = eventBus;
	}

	@Override
	public Widget asWidget() {
		v = new VerticalPanel();
		createForm();
		return v;
	}

	private void createForm(){
		FramedPanel panel = new FramedPanel();
		panel.setHeadingText("Formular de inregistrare");
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
		nume.addValidator(validator);
		nume.setAllowBlank(false);
		nume.setEmptyText("Introduceti numele...");
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
		nume.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				Info.display("Value Changed", "First name changed to " + event.getValue() == null ? "blank" : event.getValue());
				
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
		prenume.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				Info.display("Value Changed", "Last name changed to " + event.getValue() == null ? "blank" : event.getValue());
			}
		});
		p.add(new FieldLabel(prenume, "Prenume"), new VerticalLayoutData(1, -1));

		nick = new TextField();
		nick.setAllowBlank(false);
		nick.setEmptyText("Introduceti un alias...");
		nick.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				Info.display("Value Changed", "Alias changed to " + event.getValue() == null ? "blank" : event.getValue());
			}
		});
		p.add(new FieldLabel(nick, "Alias"), new VerticalLayoutData(1, -1));

		parola = new PasswordField();
		parola.setAllowBlank(false);
		p.add(new FieldLabel(parola, "Parola"), new VerticalLayoutData(1, -1));

		confirmaParola = new PasswordField();
		confirmaParola.setAllowBlank(false);
		p.add(new FieldLabel(confirmaParola, "Confirma parola"), new VerticalLayoutData(1, -1));

		description = new TextArea();
		description.setAllowBlank(false);
		description.addValidator(new MinLengthValidator(10));
		p.add(new FieldLabel(description, "Adresa"), new VerticalLayoutData(1, 100));

		panel.addButton(new TextButton("Salveaza", this));
		panel.addButton(new TextButton("Anuleaza", this));

		v.add(panel);
	}

	@Override
	public void onSelect(SelectEvent event) {
		TextButton tb = (TextButton)event.getSource();
		if(tb.getText() == "Salveaza") doSaveThingy();//do save thingy
		else eventBus.fireEvent(new ClearCenterEvent());//close widget(ascunde si totul devine null)
	}

	private void doSaveThingy(){
		UserRequest uRequest = requestFactory.userRequest(); 
		final PersoanaRequest pRequest = requestFactory.persoanaRequest();
		
		final PersoanaProxy pp = pRequest.create(PersoanaProxy.class);
		final UserProxy up = uRequest.create(UserProxy.class);

		up.setNick(nick.getValue());
		//		if(parola.getValue().length() == confirmaParola.getValue().length())
		up.setParola(parola.getValue());
		//		else {
		//			try {
		//				throw new Exception();
		//			} catch (Exception e) {
		//				Window.alert("Campurile de parola nu sunt identice, recompletati!");
		//				parola.clear();
		//				confirmaParola.clear();
		//				return;
		//			}
		//		}
		
		uRequest.persist().using(up).with("user").fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				Info.display("Succes", "Utilizatorul salvat cu succes");
				pp.setNume(nume.getValue());
				pp.setPrenume(prenume.getValue());
				pp.setUser(up);
				pRequest.persist().using(pp).with("data.user").fire(new Receiver<Void>() {

					@Override
					public void onSuccess(Void response) {
						Info.display("Succes", "Persoana salvata cu succes");
						eventBus.fireEvent(new LogInEvent());
					}

					@Override
					public void onFailure(ServerFailure sf){
						Window.alert("Eroare: "+ sf.getMessage());
					}
				});
			}

			@Override
			public void onFailure(ServerFailure sf){
				Window.alert("Salvarea utilizatorului a crapat cu succes :))");
			}
		});

		
	}

}
