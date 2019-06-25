package si.um.feri.javaee.knjiznica.websocket.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import si.um.feri.javaee.knjiznica.ejb.Izposoje;
import si.um.feri.javaee.knjiznica.ejb.Knjige;
import si.um.feri.javaee.knjiznica.vao.Izposoja;
import si.um.feri.javaee.knjiznica.vao.Knjiga;

/**
 * JANEZ a.k.a. Wannabe JARVIS
 */
@Named
public class BotBean {

	@EJB
	Knjige knjigeEjb;

	@EJB
	Izposoje izposojeEjb;

	/* Respond to a message from the chat */
	public String respond(String msg) {
		String response;

		/* Remove question marks */
		msg = msg.toLowerCase().replaceAll("\\?", "");
		if (msg.contains("kako si")) {
			response = "Odlično, hvala! Sem komaj čakal na sonček.";
		} else if (msg.contains("jaz tudi")) {
			response = "Super! Kaj bo dobrega?";
		} else if (msg.contains("izposodil knjigo")) {
			response = "Bravo! Dobrodošel v klubu bralcev. Kaj pa te zanima?";
		} else if (msg.contains("leposlovje")) {
			response = "Na voljo imamo: \n" + 
						"\t" + this.vrniVse("Leposlovje") + "\n" + 
						"Vpišite: knjiga:[koda knjige] clan:[koda člana] da opravite izposojo.";
		} else if (msg.contains("knjiga") && msg.contains("clan")) {
			String kodaKnjige = msg.substring(msg.indexOf("a:") + 2, msg.indexOf(" "));
			String kodaClana = msg.substring(msg.indexOf("n:") + 2, msg.length());

			response = this.izposodiKnjigo(kodaKnjige.toUpperCase(), kodaClana)
					? "Izposoja knjige je bila uspešno opravljena. Lep dan."
					: "Žal izposoja knjige ni bila opravljena";
		} else {
			response = "Se opravičujem, nisem razumel. ";
			response += "Lahko pa ti pomagam glede izposoje knjig.";;
		}
		try {
			Thread.sleep(1200);
		} catch (InterruptedException ex) {
		}
		return response;
	}

	public String vrniVse(String zanr) {
		String response = "";
		List<Knjiga> najdeneKnjige = knjigeEjb.vrniVse(zanr.toUpperCase());
		for (Knjiga knjiga : najdeneKnjige) {
			response = "Avtor: " + knjiga.getAvtor().getIme() + " " + knjiga.getAvtor().getPriimek() + "\n"
					+ "\t Naslov: " + knjiga.getNaslov() + "\n" + "\t Koda: " + knjiga.getKodaKnjige() + "\n";
		}

		return response;
	}

	public boolean izposodiKnjigo(String kodaKnjige, String kodaClana) {
		Izposoja i = izposojeEjb.izposodi(kodaKnjige, kodaClana);
		boolean izposoja = false;
		if (i != null)
			izposoja = true;
		return izposoja;
	}
}
