package si.um.feri.javaee.knjiznica.vao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private Set<Izposoja> izposoje=new HashSet<Izposoja>();
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
	public Set<Izposoja> getIzposoje() {
		return izposoje;
	}
	public void setIzposoje(Set<Izposoja> izposoje) {
		this.izposoje = izposoje;
	}
	
}
