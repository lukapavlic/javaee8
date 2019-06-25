package si.um.feri.javaee.knjiznica.demo.jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Razred je uporabljen pri naprednejših JPA demonstracijah
 */
@Entity
public class Oddelek implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	private String ime;
	@OneToMany(mappedBy = "oddelek")
	private Set<Zaposlen> zaposleni = new HashSet<Zaposlen>();

	public int getId() {
		return id;
	}

	public void setId(int deptNo) {
		this.id = deptNo;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Set<Zaposlen> getZaposleni() {
		return zaposleni;
	}

	public String toString() {
		return "Števila oddelka: " + getId() + ", ime: " + getIme();
	}
}
