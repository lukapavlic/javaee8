package si.um.feri.javaee.knjiznica.jsf;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import si.um.feri.javaee.knjiznica.ejb.ClaniBean;
import si.um.feri.javaee.knjiznica.vao.Clan;

@Named("clani")
@SessionScoped
public class ClaniJsfBean implements Serializable {
	
	private static final long serialVersionUID = -7097570390212211964L;
	
	Logger log=Logger.getLogger(ClaniJsfBean.class.getSimpleName());
	
	@EJB
	ClaniBean ejb;
	
	private Clan izbran;
	
	private Clan novi=new Clan(System.currentTimeMillis()+"");

	public void nov() {
		log.info("nov()");
		novi=new Clan(System.currentTimeMillis()+"");
	}
	
	public void shrani() {
		log.info("shrani("+novi+")");
		ejb.shrani(novi);
		novi=new Clan(System.currentTimeMillis()+"");
	}

	public String izberi(Clan c) {
		log.info("izberi("+c+")");
		izbran=c;
		return "pregled_clana.xhtml";
	}

	public String uredi(Clan c) {
		log.info("uredi("+c+")");
		novi=c;
		return "urejanje_clana.xhtml";
	}
	
	public List<Clan> getClani() {
		log.info("getClani()");
		return ejb.vrniVse();
	}
	
	public Clan getIzbran() {
		return izbran;
	}

	public void setIzbran(Clan izbran) {
		this.izbran = izbran;
	}

	public Clan getNovi() {
		return novi;
	}

	public void setNovi(Clan novi) {
		this.novi = novi;
	}
	
}
