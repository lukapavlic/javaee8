package si.um.feri.javaee.knjiznica.vao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;

@Entity
@Inheritance
public class OcenaKnjigeEnostavna implements Serializable {

	private static final long serialVersionUID = 3000031329572803680L;

	private int id;

	private int ocena;

	private Knjiga knjiga;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	@ManyToOne
	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}

	@Override
	public String toString() {
		return "Ocena id: " + getId() + ", ocena: " + getOcena();
	}
}
