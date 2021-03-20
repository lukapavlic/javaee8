package si.um.feri.javaee.knjiznica.vao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import si.um.feri.javaee.knjiznica.vao.json.ClanJson;

@Entity
public class Clan extends ClanJson implements Serializable {
	
	private static final long serialVersionUID = -2132249243295053907L;

	private String id;
	private boolean clanstvoAktivirano;
	private List<Izposoja> izposoje=new ArrayList<Izposoja>();

	public Clan() {
	}
	
	public Clan(String id) {
		this.id = id;
	}

	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		setKoda(id);
		this.id = id;
	}
	
	public boolean isClanstvoAktivirano() {
		return clanstvoAktivirano;
	}
	
	public void setClanstvoAktivirano(boolean clanstvoAktivirano) {
		this.clanstvoAktivirano = clanstvoAktivirano;
	}
	
	@OneToMany(cascade = CascadeType.ALL) //fetch = FetchType.EAGER, 
	public List<Izposoja> getIzposoje() {
		return izposoje;
	}
	public void setIzposoje(List<Izposoja> izposoje) {
		this.izposoje = izposoje;
	}
	
}
