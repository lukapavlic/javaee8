package si.um.feri.javaee.knjiznica.demo.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Razred je uporabljen pri naprednejših JPA demonstracijah
 */
@Entity
public class Telefon implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
    private long id;
    private String stevilka;
    private String tip;
    @ManyToOne
    Zaposlen zaposlen;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public String getStevilka() {
		return stevilka;
	}

	public void setStevilka(String stevilka) {
		this.stevilka = stevilka;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Zaposlen getZaposlen() {
		return zaposlen;
	}

	public void setZaposlen(Zaposlen zaposlen) {
		this.zaposlen = zaposlen;
	}

	public String toString() {
        return "Telefon id: " + getId() + 
               ", st: " + getStevilka() +
               ", tip: " + getTip();
    }
}
