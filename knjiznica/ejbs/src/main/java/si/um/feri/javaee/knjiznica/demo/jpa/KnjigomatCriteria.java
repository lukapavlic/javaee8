package si.um.feri.javaee.knjiznica.demo.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Razred je uporabljen pri naprednejših JPA demonstracijah
 */
@Entity
public class KnjigomatCriteria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String lokacija;

	@OneToMany(mappedBy = "knjigomat", fetch = FetchType.LAZY)
	private List<KnjigaCriteria> knjige = new ArrayList<KnjigaCriteria>();

	public KnjigomatCriteria(int id, String lokacija, List<KnjigaCriteria> knjige) {
		super();
		this.id = id;
		this.lokacija = lokacija;
		this.knjige = knjige;
	}

	public KnjigomatCriteria() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	public List<KnjigaCriteria> getKnjige() {
		return knjige;
	}

	public void setKnjige(List<KnjigaCriteria> knjige) {
		this.knjige = knjige;
	}

}
