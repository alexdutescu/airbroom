package ro.alex.browser.ui.window;

import ro.alex.client.managed.request.ApplicationRequestFactory;
import ro.alex.client.proxy.AbonamentProxy;
import ro.alex.client.proxy.LocProxy;
import ro.alex.client.proxy.RezervareProxy;
import ro.alex.client.proxy.ZborProxy;
import ro.alex.client.request.AbonamentRequest;
import ro.alex.client.request.LocRequest;
import ro.alex.client.request.RezervareRequest;

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

public class TransactionInfo {


	final Window window = new Window();
	private ApplicationRequestFactory requestFactory;
	private EventBus eventBus;
	private TextButton saveBtn;
	private boolean isDusIntors, escala;
	private ZborProxy zborDus, zborIntors;
	private String nume, prenume, codGenerat, clasa, modPlata;
	private double cost;
	private LocProxy locDus, locIntors;
	private AbonamentProxy abonamentProxy;

	//zbor dus
	public TransactionInfo(ApplicationRequestFactory requestFactory, EventBus eventBus, TextButton saveBtn, boolean isDusIntors, String nume, String prenume, 
			String codGenerat, ZborProxy zborDus, double cost, String clasa, LocProxy locDus, String modPlata, boolean escala, AbonamentProxy abonament){
		this.requestFactory = requestFactory;
		this.eventBus = eventBus;
		this.saveBtn = saveBtn;
		this.isDusIntors = isDusIntors;
		this.nume = nume;
		this.prenume = prenume;
		this.codGenerat = codGenerat;
		this.zborDus = zborDus;
		this.cost = cost;
		this.clasa = clasa;
		this.locDus = locDus;
		this.modPlata = modPlata;
		this.escala = escala;
		this.abonamentProxy = abonament;
		asWidgetDus();
	}

	//zbor dus-intors
	public TransactionInfo(ApplicationRequestFactory requestFactory, EventBus eventBus, TextButton saveBtn, boolean isDusIntors, String nume, String prenume, 
			String codGenerat, ZborProxy zborDus, ZborProxy zborIntors, double cost, String clasa, LocProxy locDus, LocProxy locIntors, String modPlata, boolean escala, AbonamentProxy abonament){
		this.requestFactory = requestFactory;
		this.eventBus = eventBus;
		this.saveBtn = saveBtn;
		this.isDusIntors = isDusIntors;
		this.nume = nume;
		this.prenume = prenume;
		this.codGenerat = codGenerat;
		this.zborDus = zborDus;
		this.zborIntors = zborIntors;
		this.cost = cost;
		this.clasa = clasa;
		this.locDus = locDus;
		this.locIntors = locIntors;
		this.modPlata = modPlata;
		this.escala = escala;
		this.abonamentProxy = abonament;
		asWidgetDusIntors();
	}


	public void asWidgetDus() {

		window.setPixelSize(600, 300);
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
		String escalaStr = escala ? "Da" : "Nu";
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
				"		"+ locDus.getNumar() +"</td>"+
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
				"		"+ modPlata +"</td>"+
				"	<td>"+
				"		Escala:</td>"+
				"	<td>"+
				"		"+ escalaStr +"</td>"+
				"</tr>"+
				"</tbody>"+
				"</table>";

		FramedPanel panel = new FramedPanel();
		panel.setHeaderVisible(false);
		//		HTML text = new HTML("<h1>Proba"+ codGenerat +"<h2><p>"+ nume +"<p></br><h2>"+ prenume +"</h2>");
		HTML text = new HTML();
		//		if(isDusIntors) text.setHTML(strDusIntors);
		text.setHTML(strDus);
		text.getElement().getStyle().setOverflowY(Overflow.AUTO);
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		con.add(text, new VerticalLayoutData(1, 1));
		Label lbl = new Label("Codul generat: "+ codGenerat);
		con.add(text);
		con.add(lbl);
		panel.add(con);
		window.add(panel);

		TextButton confirmaBtn = new TextButton("Confirma");
		//creez o entitate Rezervare, adaug informatiile necesare, o persist
		//marchez locul ca ocupat, persist schimbarea
		confirmaBtn.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				//do some persist magic
				RezervareRequest rq = requestFactory.rezervareRequest();
				RezervareProxy rp = rq.create(RezervareProxy.class);
				final LocRequest lr = requestFactory.locRequest();//e nevoie de contexte diferite?
				final LocProxy lpDus = lr.edit(locDus);

				rp.setCod(codGenerat);
				rp.setNume(nume);
				rp.setPrenume(prenume);
				rp.setZborDus(zborDus);
			
				rp.setDusIntors(false);

				rp.setCost(cost);
				
				lpDus.setOcupat(true);
				rp.setLocDus(locDus);
				
				rq.persist().using(rp).fire(new Receiver<Void>() {

					@Override
					public void onSuccess(Void response) {
						lr.persist().using(lpDus).fire();
						Info.display("Succes", "Date salvate");
						if(abonamentProxy != null){
							AbonamentRequest aq = requestFactory.abonamentRequest();
							AbonamentProxy ap = aq.edit(abonamentProxy);
							ap.setKilometri(abonamentProxy.getKilometri() - zborDus.getNumarKm());
							
							aq.append(requestFactory.abonamentRequest()).persist().using(ap).fire();
						}
					}

					@Override
					public void onFailure(ServerFailure sf){
						com.google.gwt.user.client.Window.alert(sf.getMessage());
					}
				});
				
				
				window.hide();
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



		window.setData("open", saveBtn);

	}

	public void asWidgetDusIntors() {

		window.setPixelSize(600, 400);
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
		String escalaStr = escala ? "Da" : "Nu";
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
				"		"+ locDus.getNumar() +"</td>"+
				"	<td>"+
				"		<strong>Clasa:</strong></td>"+
				"	<td>"+
				"		"+ clasa +"</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"	<td>"+
				"			<strong><span style='color:#a9a9a9;'>Cursa intors "+ zborIntors.getCod() +"</span></strong></td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"	<td>"+
				"		&nbsp;</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Oras plecare:</strong></td>"+
				"	<td>"+
				"		"+ zborIntors.getOrasp().getDenumire() +"</td>"+
				"	<td>"+
				"		<strong>Oras destinatie:</strong></td>"+
				"	<td>"+
				"		"+ zborIntors.getOrasd().getDenumire() +"</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Data intoarcere:</strong></td>"+
				"	<td>"+
				"		"+ zborIntors.getData() +"</td>"+
				"	<td>"+
				"		<strong>Cost:</strong></td>"+
				"	<td>"+
				"		"+ cost +"</td>"+
				"</tr>"+
				"<tr>"+
				"	<td>"+
				"		<strong>Loc: </strong></td>"+
				"	<td>"+
				"		"+ locIntors.getNumar() +"</td>"+
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
				"		<strong>Mod plata:</strong></td>"+
				"	<td>"+
				"		"+ modPlata +"</td>"+
				"	<td>"+
				"		Escala:</td>"+
				"	<td>"+
				"		"+ escalaStr +"</td>"+
				"</tr>"+
				"</tbody>"+
				"</table>";

		FramedPanel panel = new FramedPanel();
		panel.setHeaderVisible(false);
		//		HTML text = new HTML("<h1>Proba"+ codGenerat +"<h2><p>"+ nume +"<p></br><h2>"+ prenume +"</h2>");
		HTML text = new HTML();
		//		if(isDusIntors) text.setHTML(strDusIntors);
		text.setHTML(strDus);
		text.getElement().getStyle().setOverflowY(Overflow.AUTO);
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		con.add(text, new VerticalLayoutData(1, 1));
		Label lbl = new Label("Codul unic de identificare al rezervarii: "+ codGenerat );
		con.add(text);
		con.add(lbl);
		panel.add(con);
		window.add(panel);

		TextButton confirmaBtn = new TextButton("Confirma");
		//creez o entitate Rezervare, adaug informatiile necesare, o persist
		//marchez locurile gasite ca fiind ocupate
		confirmaBtn.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				//to do some persist magic
				RezervareRequest rq = requestFactory.rezervareRequest();
				RezervareProxy rp = rq.create(RezervareProxy.class);
				final LocRequest lr1 = requestFactory.locRequest();//e nevoie de contexte diferite? ... last edit: da!
				final LocRequest lr2 = requestFactory.locRequest();
				final LocProxy lpDus = lr1.edit(locDus);
				final LocProxy lpIntors = lr2.edit(locIntors);

				rp.setCod(codGenerat);
				rp.setNume(nume);
				rp.setPrenume(prenume);
				rp.setZborDus(zborDus);
				
				rp.setZborIntors(zborIntors);
				rp.setDusIntors(true);

				rp.setCost(cost);
				
				lpDus.setOcupat(true);
				rp.setLocDus(locDus);
				
				lpIntors.setOcupat(true);
				rp.setLocIntors(locIntors);

				rq.persist().using(rp).fire(new Receiver<Void>() {

					@Override
					public void onSuccess(Void response) {
						Info.display("Succes", "Date salvate");
						lr1.persist().using(lpDus).fire(new Receiver<Void>() {

							@Override
							public void onSuccess(Void response) {
								lr2.persist().using(lpIntors).fire();
								if(abonamentProxy != null){
									AbonamentRequest aq = requestFactory.abonamentRequest();
									AbonamentProxy ap = aq.edit(abonamentProxy);
									ap.setKilometri(abonamentProxy.getKilometri() - 2*zborDus.getNumarKm());
									
									aq.append(requestFactory.abonamentRequest()).persist().using(ap).fire();
								}
							}
						});
					}

					@Override
					public void onFailure(ServerFailure sf){
						com.google.gwt.user.client.Window.alert(sf.getMessage());
					}
				});
				
				window.hide();
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

		window.setData("open", saveBtn);

		//		return window;
	}

	public Window getWindow(){
		return window;
	}
}