package si.um.feri.javaee.knjiznica.demo.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;

/**
 * Razred je uporabljen pri naprednejših JPA demonstracijah
 */
@Entity
@Inheritance
public class Projekt implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	protected int id;
	protected String ime;
	@ManyToMany
	protected Collection<Zaposlen> zaposleni = new ArrayList<Zaposlen>();

	public int getId() {
		return id;
	}

	public void setId(int projectNo) {
		this.id = projectNo;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Collection<Zaposlen> getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Collection<Zaposlen> zaposleni) {
		this.zaposleni = zaposleni;
	}

	public void addZaposlen(Zaposlen zaposlen) {
		if (!getZaposleni().contains(zaposlen)) {
			getZaposleni().add(zaposlen);
		}
		if (!zaposlen.getProjects().contains(this)) {
			zaposlen.getProjects().add(this);
		}
	}

	public String toString() {
		return getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1) + " no: " + getId()
				+ ", ime: " + getIme();
	}
}
