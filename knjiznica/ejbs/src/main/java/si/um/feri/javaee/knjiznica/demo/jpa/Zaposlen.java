package si.um.feri.javaee.knjiznica.demo.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Razred je uporabljen pri naprednejših JPA demonstracijah
 */
@Entity
public class Zaposlen implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	private String ime;
	private long placa;

	@OneToOne
	private Naslov naslov;

	@OneToMany(mappedBy = "zaposlen")
	private Collection<Telefon> telefons = new ArrayList<Telefon>();

	@ManyToOne
	private Oddelek oddelek;

	@ManyToMany(mappedBy = "zaposleni")
	private Collection<Projekt> projects = new ArrayList<Projekt>();

	public int getId() {
		return id;
	}

	public void setId(int empNo) {
		this.id = empNo;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public long getPlaca() {
		return placa;
	}

	public void setPlaca(long placa) {
		this.placa = placa;
	}

	public Collection<Telefon> getTelefons() {
		return telefons;
	}

	public void addTelefon(Telefon telefon) {
		if (!getTelefons().contains(telefon)) {
			getTelefons().add(telefon);
			if (telefon.getZaposlen() != null) {
				telefon.getZaposlen().getTelefons().remove(telefon);
			}
			telefon.setZaposlen(this);
		}
	}

	public Oddelek getOddelek() {
		return oddelek;
	}

	public void setOddelek(Oddelek oddelek) {
		if (this.oddelek != null) {
			this.oddelek.getZaposleni().remove(this);
		}
		this.oddelek = oddelek;
		this.oddelek.getZaposleni().add(this);
	}

	public Collection<Projekt> getProjects() {
		return projects;
	}

	public void addProject(Projekt project) {
		if (!getProjects().contains(project)) {
			getProjects().add(project);
		}
		if (!project.getZaposleni().contains(this)) {
			project.getZaposleni().add(this);
		}
	}

	public Naslov getNaslov() {
		return naslov;
	}

	public void setNaslov(Naslov naslov) {
		this.naslov = naslov;
	}

	public String toString() {
		return "Zaposlen " + getId() + ": ime: " + getIme() + ", placa: " + getPlaca() + ", telefoni: " + getTelefons()
				+ ", st. oddelka: " + ((getOddelek() == null) ? null : getOddelek().getId());
	}
}
