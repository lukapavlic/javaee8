package si.um.feri.javaee.knjiznica.jsf;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import si.um.feri.javaee.knjiznica.ejb.Izposoje;
import si.um.feri.javaee.knjiznica.ejb.Knjige;
import si.um.feri.javaee.knjiznica.vao.Knjiga;

/**
 * JSF zrno - delo z izposojami
 */
@Named("izposoje")
@SessionScoped
public class IzposojeJsfBean implements Serializable {
	
	private static final long serialVersionUID = -7097571110212211964L;
	
	Logger log=Logger.getLogger(IzposojeJsfBean.class.getSimpleName());
	
	@EJB
	Izposoje ejb;
	
	@EJB
	Knjige knjigeEjb;
	
	private String knjigaId;
	
	private Knjiga knjiga;
	
	private String clanKoda;
	

	public Izposoje getEjb() {
		return ejb;
	}

	public String getKnjigaId() {
		return knjigaId;
	}

	public void setKnjigaId(String knjigaId) {
		this.knjigaId = knjigaId;
		knjiga=knjigeEjb.najdi(Integer.parseInt(knjigaId));
	}

	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}

	public String getClanKoda() {
		return clanKoda;
	}

	public void setClanKoda(String clanKoda) {
		this.clanKoda = clanKoda;
	}

}
