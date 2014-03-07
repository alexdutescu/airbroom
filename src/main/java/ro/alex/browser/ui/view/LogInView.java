package ro.alex.browser.ui.view;

import ro.alex.browser.events.AutentificareEvent;
import ro.alex.browser.events.ClearCenterEvent;
import ro.alex.browser.handlers.AutentificareEventHandler;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.PersoanaProxy;
import ro.alex.client.proxy.UserProxy;
import ro.alex.shared.scaffold.ScaffoldRequestFactory.PersoanaScaffoldRequest;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class LogInView implements IsWidget, SelectHandler {

	private ApplicationRequestFactory requestFactory;
	private EventBus eventBus;
	private TextField userName;
	private PasswordField parola;


	@Inject
	public LogInView(ApplicationRequestFactory requestFactory, EventBus eventBus){
		this.requestFactory = requestFactory;
		this.eventBus = eventBus;
	}

	@Override
	public Widget asWidget() {
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(10);
		FramedPanel form2 = new FramedPanel();
		form2.setHeadingText("Autentificare");
		form2.setWidth(350);

		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeadingText("Date logare");
		fieldSet.setCollapsible(true);
		form2.add(fieldSet);

		VerticalLayoutContainer p = new VerticalLayoutContainer();
		fieldSet.add(p);

		userName = new TextField();
		userName.setAllowBlank(false);
		p.add(new FieldLabel(userName, "Utilizator"), new VerticalLayoutData(1, -1));

		parola = new PasswordField();
		parola.setAllowBlank(false);
		p.add(new FieldLabel(parola, "Parola"), new VerticalLayoutData(1, -1));

		form2.addButton(new TextButton("Autentifica", this));
		form2.addButton(new TextButton("Anuleaza", this));

		vp.add(form2);
		return vp;
	}

	private void validare(String user, String parola){
		requestFactory.userScaffoldRequest().validateAuth(user, parola).with("user").fire(new Receiver<UserProxy>() {

			@Override
			public void onSuccess(UserProxy user) {
				//incarca UserView, 
				//				if()
//				eventBus.fireEvent(new AutentificareEvent(response));
				
				requestFactory.persoanaScaffoldRequest().getPersoanaByUserId(user.getId()).with("*").fire(new Receiver<PersoanaProxy>() {

					@Override
					public void onSuccess(PersoanaProxy persoana) {
						eventBus.fireEvent(new AutentificareEvent(persoana));
					}
				});
			}

			@Override
			public void onFailure(ServerFailure e){
				Window.alert("Autentificare esuata");
			}
		});
	}

	@Override
	public void onSelect(SelectEvent event) {
		TextButton tb = (TextButton)event.getSource();
		Info.display("Eveniment", tb.getText());
		if(tb.getText() == "Autentifica")
			validare(userName.getValue(), parola.getValue());//if valid load context
		else eventBus.fireEvent(new ClearCenterEvent());
	}

}
