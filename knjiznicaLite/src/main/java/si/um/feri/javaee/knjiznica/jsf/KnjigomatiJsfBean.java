package si.um.feri.javaee.knjiznica.jsf;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import si.um.feri.javaee.knjiznica.ejb.KnjigomatiBean;
import si.um.feri.javaee.knjiznica.vao.Knjigomat;

@Named("knjigomati")
@SessionScoped
public class KnjigomatiJsfBean implements Serializable {
	
	private static final long serialVersionUID = -7097571110212211964L;
	
	Logger log=Logger.getLogger(KnjigomatiJsfBean.class.getSimpleName());
	
	@EJB
	KnjigomatiBean ejb;
	
	List<Knjigomat> knjigomati;

	public List<Knjigomat> getKnjigomati() {
		log.info("getKnjigomati()");
		if (knjigomati==null) knjigomati=ejb.vrniVse();
		return knjigomati;
	}
	
	public void dodaj() {
		log.info("dodaj()");
		Knjigomat k=new Knjigomat();
		k.setNaslov("naslov");
		k.setNaziv("naziv");
		k.setKapaciteta(10);
		k.setUrejanje(true);
		knjigomati.add(k);
	}
	
	public void uredi(Knjigomat k) {
		k.setUrejanje(true);
	}
	
	public void shraniSpremembe() {
		for (Knjigomat k :knjigomati)
			if (k.isUrejanje())
				ejb.shrani(k);
		knjigomati=null;
	}
	
}
