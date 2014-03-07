package ro.alex.browser.ui.window;

import java.util.List;

import ro.alex.browser.CustomLabelProvider;
import ro.alex.browser.ZborProxyProperties;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.RezervareProxy;
import ro.alex.client.proxy.ZborProxy;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.info.Info;

public class ModifyHourView {

	final Window window = new Window();
	private RezervareProxy rp;
	private ApplicationRequestFactory requestFactory;
	private TextButton modificaBtn;
	private CustomLabelProvider clp = new CustomLabelProvider();
	
	public ModifyHourView(ApplicationRequestFactory requestFactory, RezervareProxy rp, TextButton modificaBtn){
		this.rp = rp;
		this.modificaBtn = modificaBtn;
		this.requestFactory = requestFactory;
		
		if(!rp.getDusIntors()) zborDusView();
		else zborDusIntorsView();
	}
	
	public void zborDusView(){
		
		window.setPixelSize(390, 23);
		window.setModal(true);
		window.setBlinkModal(true);
		window.setHeadingText("Confirmati datele");
		window.addHideHandler(new HideHandler() {
			@Override
			public void onHide(HideEvent event) {
				TextButton open = ((Window) event.getSource()).getData("open");
				open.focus();
			}
		});
		
		FramedPanel panel = new FramedPanel();
		panel.setHeaderVisible(false);
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		HorizontalPanel hp = new HorizontalPanel();
		Label lbl = new Label("Ore disponibile pentru zborul curent:");
//		Label combo = new Label("Aici vine combo");
		ComboBox<ZborProxy> combo = createComboDus();
		hp.add(lbl);
		hp.add(combo);
		con.add(hp);
		
		panel.add(con);
		window.add(panel);
		
		TextButton confirmaBtn = new TextButton("Confirma");
		//creez o entitate Rezervare de care ma folosesc sa editez rezervarea curenta primita ca parametru
		//persist schimbarea
		confirmaBtn.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				//do some persist magic
				Info.display("Event", "Confirma event");
			}
		});
		window.addButton(confirmaBtn);

		TextButton b = new TextButton("Close");
		b.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				
				window.hide();
			}
		});
		window.addButton(b);
		window.setFocusWidget(window.getButtonBar().getWidget(0));

		window.setData("open", modificaBtn);
	}
	
	private void zborDusIntorsView(){
		
		window.setPixelSize(390, 50);
		window.setModal(true);
		window.setBlinkModal(true);
		window.setHeadingText("Confirmati datele");
		window.addHideHandler(new HideHandler() {
			@Override
			public void onHide(HideEvent event) {
				TextButton open = ((Window) event.getSource()).getData("open");
				open.focus();
			}
		});
		
		FramedPanel panel = new FramedPanel();
		panel.setHeaderVisible(false);
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		HorizontalPanel hp1 = new HorizontalPanel();
		Label lbl = new Label("Ore disponibile pentru zborul de plecare:   ");
//		Label combo = new Label("Aici vine combo");
		ComboBox<ZborProxy> comboDus = createComboDus();
		hp1.add(lbl);
		hp1.add(comboDus);
		con.add(hp1);
		
		HorizontalPanel hp2 = new HorizontalPanel();
		lbl = new Label("Ore disponibile pentru zborul de intoarcere:  ");
		ComboBox<ZborProxy> comboIntors = createComboIntors();
		hp2.add(lbl);
		hp2.add(comboIntors);
		con.add(hp2);
		
		panel.add(con);
		window.add(panel);
		
		TextButton confirmaBtn = new TextButton("Confirma");
		//creez o entitate Rezervare de care ma folosesc sa editez rezervarea curenta primita ca parametru
		//persist schimbarea
		confirmaBtn.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				//do some persist magic
				Info.display("Event", "Confirma event");
			}
		});
		window.addButton(confirmaBtn);

		TextButton b = new TextButton("Close");
		b.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				
				window.hide();
			}
		});
		window.addButton(b);
		window.setFocusWidget(window.getButtonBar().getWidget(0));

		window.setData("open", modificaBtn);
	}
	
	private ComboBox<ZborProxy> createComboDus(){
		ZborProxyProperties props = GWT.create(ZborProxyProperties.class);
		String data = DateTimeFormat.getFormat("yyyy-MM-dd").format(rp.getZborDus().getData());
//		Info.display("Data", );
		
		final ListStore<ZborProxy> zbor = new ListStore<ZborProxy>(props.id());
		requestFactory.zborPagingRequest().getHours(rp.getZborDus().getOrasp().getId(), rp.getZborDus().getOrasd().getId(), 
							data).with("*","*.*").fire(new Receiver<List<ZborProxy>>() {

								@Override
								public void onSuccess(List<ZborProxy> response) {
									zbor.addAll(response);
								}
							});
		ComboBox<ZborProxy> combo = new ComboBox<ZborProxy>(zbor, clp);
		combo.setEditable(false);
		combo.setEmptyText("Alegeti ora...");
		combo.setTriggerAction(TriggerAction.ALL);
				
		return combo;
	}
	
	private ComboBox<ZborProxy> createComboIntors(){
		ZborProxyProperties props = GWT.create(ZborProxyProperties.class);
		String data = DateTimeFormat.getFormat("yyyy-MM-dd").format(rp.getZborIntors().getData());
		final ListStore<ZborProxy> zbor = new ListStore<ZborProxy>(props.id());
		requestFactory.zborPagingRequest().getHours(rp.getZborIntors().getOrasp().getId(), rp.getZborIntors().getOrasd().getId(), 
							data).with("*","*.*").fire(new Receiver<List<ZborProxy>>() {

								@Override
								public void onSuccess(List<ZborProxy> response) {
									zbor.addAll(response);
								}
							});

		ComboBox<ZborProxy> combo = new ComboBox<ZborProxy>(zbor, clp);
		combo.setEditable(false);
		combo.setEmptyText("Alegeti ora...");
		combo.setTriggerAction(TriggerAction.ALL);
		
				
		return combo;		
	}
	
	public Window getWindow(){
		return window;
	}
	
	
}
