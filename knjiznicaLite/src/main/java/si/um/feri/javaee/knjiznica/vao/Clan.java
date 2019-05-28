package si.um.feri.javaee.knjiznica.vao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Clan implements Serializable {
	
	private static final long serialVersionUID = -2132249243295053907L;

	private String ime;
	private String priimek;
	private String email;
	private boolean clanstvoAktivirano;
	private List<Izposoja> izposoje=new ArrayList<Izposoja>();
	private String kodaClana;

	public Clan() {
	}
	
	public Clan(String kodaClana) {
		this.kodaClana=kodaClana;
	}
	
	@Id
	public String getKodaClana() {
		return kodaClana;
	}
	public void setKodaClana(String kodaClana) {
		this.kodaClana = kodaClana;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPriimek() {
		return priimek;
	}
	public void setPriimek(String priimek) {
		this.priimek = priimek;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isClanstvoAktivirano() {
		return clanstvoAktivirano;
	}
	public void setClanstvoAktivirano(boolean clanstvoAktivirano) {
		this.clanstvoAktivirano = clanstvoAktivirano;
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public List<Izposoja> getIzposoje() {
		return izposoje;
	}
	public void setIzposoje(List<Izposoja> izposoje) {
		this.izposoje = izposoje;
	}
	
}
