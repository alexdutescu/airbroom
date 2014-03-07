package ro.alex.browser.ui.window;

import com.google.web.bindery.requestfactory.shared.Receiver;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.RezervareProxy;
import ro.alex.client.request.AbonamentRequest;
import ro.alex.client.request.RezervareRequest;

public class DeleteRezervarePrompt implements SelectHandler{
	
	final Window window = new Window();
	private ApplicationRequestFactory requestFactory;
	private RezervareProxy rp;
	private TextButton modificaBtn;
	private AbonamentProxy abonament;
	
	public DeleteRezervarePrompt(ApplicationRequestFactory requestFactory, RezervareProxy rp, TextButton modificaBtn){
		this.requestFactory = requestFactory;
		this.rp = rp;
		this.modificaBtn = modificaBtn;
		
		promptView();
	}

	private void promptView(){
		window.setPixelSize(190, 13);
		window.setModal(true);
		window.setBlinkModal(true);
		window.setHeadingText("Doriti sa stergeti rezervarea?");
		window.addHideHandler(new HideHandler() {
			@Override
			public void onHide(HideEvent event) {
				TextButton open = ((Window) event.getSource()).getData("open");
				open.focus();
			}
		});
		
		TextButton tb1 = new TextButton("Sterge", this);
		TextButton tb2 = new TextButton("Anuleaza", this);

		window.addButton(tb1);
		window.addButton(tb2);
		window.setData("open", modificaBtn);
	}
	
	private void stergeRezervare(){
		RezervareRequest rezReq = requestFactory.rezervareRequest();
		rezReq.remove().using(rp).fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				Info.display("Succes", "Rezervarea a fost stearsa");
			}
		});//rezProxy sau rp?
	}
	
	private void updateAbonament(){
		AbonamentRequest abRequest = requestFactory.abonamentRequest();
		AbonamentProxy abProxy = abRequest.edit(abonament);
		if(rp.getDusIntors()) abProxy.setKilometri(rp.getZborDus().getNumarKm() + rp.getZborIntors().getNumarKm() + abonament.getKilometri());
		else abProxy.setKilometri(rp.getZborDus().getNumarKm() + abonament.getKilometri());
		
		abRequest.persist().using(abProxy).fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				Info.display("Succes", "Abonamentul a fost actualizat");
			}
		});
	}
	
	public Window getWindow(){
		return window;
	}

	@Override
	public void onSelect(SelectEvent event) {
		TextButton tb = (TextButton) event.getSource();
		if( tb.getText() == "Sterge" ) {
			if(abonament != null) updateAbonament();
			stergeRezervare();
			window.hide();
		}
		else window.hide();
	}
}
