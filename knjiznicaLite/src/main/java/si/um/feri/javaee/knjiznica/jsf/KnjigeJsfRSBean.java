package si.um.feri.javaee.knjiznica.jsf;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import si.um.feri.javaee.knjiznica.ejb.Knjige;
import si.um.feri.javaee.knjiznica.vao.Knjiga;

@Named("knjigers")
@RequestScoped
public class KnjigeJsfRSBean implements Serializable {
	
	private static final long serialVersionUID = -7097570390291811964L;
	
	Logger log=Logger.getLogger(KnjigeJsfRSBean.class.getSimpleName());
	
	@EJB
	Knjige ejb;
	
	private List<Knjiga> knjigeCache=null;
	
	private String zanr;
	
	public List<Knjiga> getKnjige() {
		if (knjigeCache==null) {
			knjigeCache=ejb.vrniVse(zanr);
		}
		return knjigeCache;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}
	
}
