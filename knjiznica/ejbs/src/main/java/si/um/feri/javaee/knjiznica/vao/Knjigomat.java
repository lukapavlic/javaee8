package si.um.feri.javaee.knjiznica.vao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Knjigomat implements Serializable {

	private static final long serialVersionUID = -8009522228935849531L;

	private int id;
	private String naziv="naziv";
	private String naslov="naslov";
	private int kapaciteta=15;
	
	private boolean urejanje=false;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@NotNull
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public int getKapaciteta() {
		return kapaciteta;
	}
	public void setKapaciteta(int kapaciteta) {
		this.kapaciteta = kapaciteta;
	}
	
	@Transient
	@XmlTransient
	public boolean isUrejanje() {
		return urejanje;
	}
	public void setUrejanje(boolean urejanje) {
		this.urejanje = urejanje;
	}
	
}
