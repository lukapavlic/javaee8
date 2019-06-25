package si.um.feri.javaee.knjiznica.demo.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Razred je uporabljen pri naprednejših JPA demonstracijah
 */
@Entity
public class AvtorCriteria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String ime;

	private String priimek;

	@Temporal(TemporalType.DATE)
	private Date datumRojstva;

	@OneToMany(mappedBy = "avtor", fetch = FetchType.LAZY)
	private List<KnjigaCriteria> knjige = new ArrayList<KnjigaCriteria>();

	public AvtorCriteria(int id, String ime, String priimek, Date datumrojstva, List<KnjigaCriteria> knjige) {
		super();
		this.id = id;
		this.ime = ime;
		this.priimek = priimek;
		this.datumRojstva = datumrojstva;
		this.knjige = knjige;
	}

	public AvtorCriteria() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getDatumRojstva() {
		return datumRojstva;
	}

	public void setDatumRojstva(Date datumRojstva) {
		this.datumRojstva = datumRojstva;
	}

	public List<KnjigaCriteria> getKnjige() {
		return knjige;
	}

	public void setKnjige(List<KnjigaCriteria> knjige) {
		this.knjige = knjige;
	}

}
