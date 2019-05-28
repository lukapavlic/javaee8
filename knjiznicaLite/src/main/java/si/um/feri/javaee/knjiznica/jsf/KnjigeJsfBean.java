package si.um.feri.javaee.knjiznica.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import si.um.feri.javaee.knjiznica.ejb.Knjige;
import si.um.feri.javaee.knjiznica.vao.Knjiga;

@Named("knjige")
@SessionScoped
public class KnjigeJsfBean implements Serializable {
	
	private static final long serialVersionUID = -7097570390291811964L;
	
	Logger log=Logger.getLogger(KnjigeJsfBean.class.getSimpleName());
	
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	 
    public Locale getLocale() {
    	log.info("getLocale(): "+locale);
        return locale;
    }
 
    public String getLanguage() {
    	log.info("getLocale(): "+locale.getLanguage());
        return locale.getLanguage();
    }
 
    public void changeLanguage(String language) {
    	log.info("changeLanguage("+language+")");
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }
	
	@EJB
	Knjige ejb;
	
	private Knjiga izbrana=new Knjiga();

	private int izbranaId;
	
	private String iskalniNiz;
	
	private List<String> zanri=new ArrayList<String>();

	private List<Knjiga> rezultati;

	public void shrani() {
		log.info("shrani "+izbrana);
		izbrana.setZanr(zanri);
		ejb.shrani(izbrana);
	}
	
	public void iskanje() {
		log.info("iskanje(): "+iskalniNiz);
		rezultati=ejb.poisci(iskalniNiz);
	}
	
	public void pocistiRezultateIskanja() {
		log.info("pocistiRezultateIskanja()");
		rezultati=null;
	}

	public Knjiga getIzbrana() {
		return izbrana;
	}

	public void setIzbrana(Knjiga izbrana) {
		this.izbrana = izbrana;
	}

	public int getIzbranaId() {
		return izbranaId;
	}

	public void setIzbranaId(int izbranaId) {
		log.info("setIzbranaId("+izbranaId+")");
		this.izbranaId = izbranaId;
		izbrana=ejb.najdi(izbranaId);
		zanri=new ArrayList<String>();
		if (izbrana==null) {
			izbrana=new Knjiga();
		} else {
			zanri.addAll(izbrana.getZanr());
		}
	}

	public List<String> getZanri() {
		return zanri;
	}

	public void setZanri(List<String> zanri) {
		this.zanri = zanri;
	}

	public String getIskalniNiz() {
		return iskalniNiz;
	}

	public void setIskalniNiz(String iskalniNiz) {
		this.iskalniNiz = iskalniNiz;
	}

	public List<Knjiga> getRezultati() {
		return rezultati;
	}

	public void setRezultati(List<Knjiga> rezultati) {
		this.rezultati = rezultati;
	}

}
