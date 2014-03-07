package ro.alex.browser.ui.window;

import ro.alex.browser.events.RezervaEvent;
import ro.alex.browser.handlers.RezervaEventHandler;
import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.LocProxy;
import ro.alex.client.proxy.ZborProxy;

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class DisplayTransactionInfo implements IsWidget {

	private TextButton salveazaBtn;
	private Window window;
	private ZborProxy zborDus, zborIntors;
	private LocProxy loc;

	private String nume, prenume, codGenerat, clasa;
	private boolean isDusIntors;
	private VerticalLayoutContainer con = new VerticalLayoutContainer();
	private ApplicationRequestFactory requestFactory;
	private EventBus eventBus;
	
	private Double cost;


//	public DisplayTransactionInfo(ApplicationRequestFactory requestFactory, EventBus eventBus, TextButton salveazaBtn, ZborProxy zborDus,  
//			String nume, String prenume, boolean isDusIntors, String codGenerat, Double cost, String clasa, LocProxy lp) {
//		this.salveazaBtn = salveazaBtn;
//		this.zborDus = zborDus;
//		
//		this.nume = nume;
//		this.prenume = prenume;
//		this.codGenerat = codGenerat;
//		this.isDusIntors = isDusIntors;
//		this.requestFactory = requestFactory;
//		this.eventBus = eventBus;
//		
//		this.cost = cost;
//		this.clasa = clasa;
//		this.lp = lp;
//	}

	public TextButton getSalveazaBtn() {
		return salveazaBtn;
	}

	public void setSalveazaBtn(TextButton salveazaBtn) {
		this.salveazaBtn = salveazaBtn;
	}

	public ZborProxy getZborDus() {
		return zborDus;
	}

	public void setZborDus(ZborProxy zborDus) {
		this.zborDus = zborDus;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getCodGenerat() {
		return codGenerat;
	}

	public void setCodGenerat(String codGenerat) {
		this.codGenerat = codGenerat;
	}

	public String getClasa() {
		return clasa;
	}

	public void setClasa(String clasa) {
		this.clasa = clasa;
	}

	public boolean isDusIntors() {
		return isDusIntors;
	}

	public void setDusIntors(boolean isDusIntors) {
		this.isDusIntors = isDusIntors;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

//	@Inject
//	public DisplayTransactionInfo(ApplicationRequestFactory requestFactory, EventBus eventBus){
//		this.requestFactory = requestFactory;
//		this.eventBus = eventBus;
//		asWidget();
//		
//	}
	
	@Override
	public Widget asWidget() {
		window = new Window();
		window.setPixelSize(700, 500);
		window.setModal(true);
		window.setBlinkModal(true);
		window.setHeadingText("Confirmati tranzactia?");
		window.addHideHandler(new HideHandler() {
			@Override
			public void onHide(HideEvent event) {
				TextButton open = ((Window) event.getSource()).getData("open");
				open.focus();
			}
		});
		String strDus = "<table align='left' border='0' cellpadding='1' cellspacing='1' style='width: 500px;'>"+
				"<tbody>"+
				"<tr>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"	<td>"+
				"		<strong><span style='color:#a9a9a9;'>Cursa dus "+ zborDus.getCod() +"</span></strong></td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Oras plecare:&nbsp;</strong></td>"+
				"	<td>"+
				"		"+ zborDus.getOrasp().getDenumire() +"</td>"+
				"	<td>"+
				"		<strong>Oras destinatie:</strong></td>"+
				"	<td>"+
				"		"+ zborDus.getOrasd().getDenumire() +"</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Data plecare: </strong></td>"+
				"		<td>"+
				"		"+ zborDus.getData() +"</td>"+
				"	<td>"+
				"		<strong>Cost:</strong></td>"+
				"	<td>"+
				"		"+ cost +"</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Loc: </strong></td>"+
				"	<td>"+
				"		inca nu e gata</td>"+
				"	<td>"+
				"		<strong>Clasa:</strong></td>"+
				"	<td>"+
				"		"+ clasa +"</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"	<td>"+
				"		<strong><span style='color:#a9a9a9;'>Informatii aditionale</span></strong></td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Nume:</strong></td>"+
				"	<td>"+
				"		"+ nume +"</td>"+
				"	<td>"+
				"		<strong>Prenume:</strong></td>"+
				"	<td>"+
				"		"+ prenume +"</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Mod plata:</strong></td>"+
				"	<td>"+
				"		who the fuck cares?</td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"</tr>"+
				"</tbody>"+
				"</table>";
		String strDusIntors = "<table align='left' border='0' cellpadding='1' cellspacing='1' style='width: 500px;'>"+
				"<tbody>"+
				"<tr>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"	<td>"+
				"		<strong><span style='color:#a9a9a9;'>Cursa dus ro44</span></strong></td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Oras plecare:&nbsp;</strong></td>"+
				"	<td>"+
				"		"+ zborDus.getOrasp().getDenumire() +"</td>"+
				"	<td>"+
				"		<strong>Oras destinatie:</strong></td>"+
				"	<td>"+
				"		"+ zborDus.getOrasd().getDenumire() +"</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Data plecare: </strong></td>"+
				"		<td>"+
				"		"+ zborDus.getData() +"</td>"+
				"	<td>"+
				"		<strong>Cost:</strong></td>"+
				"	<td>"+
				"		inca nu e gata</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Loc: </strong></td>"+
				"	<td>"+
				"		inca nu e gata</td>"+
				"	<td>"+
				"		<strong>Clasa:</strong></td>"+
				"	<td>"+
				"		inca nu e gata</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"	<td>"+
				"			<strong><span style='color:#a9a9a9;'>Cursa intors ro35</span></strong></td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Oras plecare:</strong></td>"+
				"	<td>"+
				"		"+ zborIntors.getOrasp() +"</td>"+
				"	<td>"+
				"		<strong>Oras destinatie:</strong></td>"+
				"	<td>"+
				"		"+ zborIntors.getOrasd() +"</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Data intoarcere:</strong></td>"+
				"	<td>"+
				"		"+ zborIntors.getData() +"</td>"+
				"	<td>"+
				"		<strong>Cost:</strong></td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Loc: </strong></td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"	<td>"+
				"		<strong>Clasa:</strong></td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"	<td>"+
				"		<strong><span style='color:#a9a9a9;'>Informatii aditionale</span></strong></td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Nume:</strong></td>"+
				"	<td>"+
				"		"+ nume +"</td>"+
				"	<td>"+
				"		<strong>Prenume:</strong></td>"+
				"	<td>"+
				"		"+ prenume +"</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Cod unic generat:</strong></td>"+
				"	<td>"+
				"		"+ codGenerat +"</td>"+
				"	<td>"+
				"		<strong>Total cursa:</strong></td>"+
				"	<td>"+
				"		inca nu e implementat</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Mod plata:</strong></td>"+
				"	<td>"+
				"		who the fuck cares?</td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"</tr>"+
				"</tbody>"+
				"</table>";
		FramedPanel panel = new FramedPanel();
		panel.setHeaderVisible(false);
		//		HTML text = new HTML("<h1>Proba"+ codGenerat +"<h2><p>"+ nume +"<p></br><h2>"+ prenume +"</h2>");
		HTML text = new HTML();
		if(isDusIntors) text.setHTML(strDusIntors);
		else text.setHTML(strDus);
		text.getElement().getStyle().setOverflowY(Overflow.AUTO);
		con.add(text, new VerticalLayoutData(1, 1));
		Label lbl = new Label("All rights reserved."+ codGenerat +" - "+ nume);
		con.add(text);
		con.add(lbl);
		panel.add(con);

		window.add(panel);
		TextButton confirmaBtn = new TextButton("Confirma");
		//creez o entitate Rezervare, adaug informatiile necesare, o persist
		confirmaBtn.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
//				Info.display("Alert", codGenerat +"--"+ nume +"--"+ prenume +"--"+ zborDus.getCod());
//				RezervareRequest rq = requestFactory.rezervareRequest();
//				RezervareProxy rp = rq.create(RezervareProxy.class);
//
//				rp.setCod(codGenerat);
//				rp.setNume(nume);
//				rp.setPrenume(prenume);
//				rp.setZborDus(zborDus);
//				if(isDusIntors) {
//					rp.setZborIntors(zborIntors);
//					rp.setDusIntors(true);
//				}else rp.setDusIntors(false);

//				rp.setCost(cost);
//				if(!lp.getOcupat()) rp.setLoc(lp);
				//else blow the shit up



//				rq.persist().using(rp).fire(new Receiver<Void>() {
//
//					@Override
//					public void onSuccess(Void response) {
//						Info.display("Succes", "Date salvate");
//					}
//
//					@Override
//					public void onFailure(ServerFailure sf){
//						com.google.gwt.user.client.Window.alert(sf.getMessage());
//					}
//				});

				window.hide();
			}
		});
		window.addButton(confirmaBtn);
		TextButton anuleazaBtn = new TextButton("Anuleaza");
		anuleazaBtn.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				window.hide();
			}
		});
		window.addButton(anuleazaBtn);
		window.setFocusWidget(window.getButtonBar().getWidget(0));

		window.setData("open", salveazaBtn);
		
eventBus.addHandler(RezervaEvent.TYPE, new RezervaEventHandler() {
			
			@Override
			public void onRezervaEvent(RezervaEvent rezervaEvent) {
				window.show();
			}
		});
		return window;
	}

	public Window getWindow(){
		return (Window)asWidget();
	}
}
