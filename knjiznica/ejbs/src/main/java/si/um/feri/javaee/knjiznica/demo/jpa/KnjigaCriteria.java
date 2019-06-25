package si.um.feri.javaee.knjiznica.demo.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Razred je uporabljen pri naprednejših JPA demonstracijah
 */
@Entity
public class KnjigaCriteria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String naslov;
	private int letoIzdaje;

	@ManyToOne
	private AvtorCriteria avtor;

	@ManyToOne	
	private KnjigomatCriteria knjigomat;

	public KnjigaCriteria(int id, String naslov, AvtorCriteria avtor, KnjigomatCriteria knjigomat) {
		super();
		this.id = id;
		this.naslov = naslov;
		this.avtor = avtor;
		this.knjigomat = knjigomat;
	}

	public KnjigaCriteria() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public AvtorCriteria getAvtor() {
		return avtor;
	}

	public void setAvtor(AvtorCriteria avtor) {
		this.avtor = avtor;
	}

	public KnjigomatCriteria getKnjigomat() {
		return knjigomat;
	}

	public void setKnjigomat(KnjigomatCriteria knjigomat) {
		this.knjigomat = knjigomat;
	}

	public int getLetoIzdaje() {
		return letoIzdaje;
	}

	public void setLetoIzdaje(int letoIzdaje) {
		this.letoIzdaje = letoIzdaje;
	}

}
