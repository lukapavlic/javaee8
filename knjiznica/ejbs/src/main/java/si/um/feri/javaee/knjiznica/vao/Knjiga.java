package si.um.feri.javaee.knjiznica.vao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Knjiga implements Serializable {

	private static final long serialVersionUID = -8009529228935849531L;

	private int id;
	private String naslov;
	private String opis;
	private Avtor avtor=new Avtor();
	private List<String> zanr=new ArrayList<String>();
	private int steviloStrani;
	private int letoIzdaje;
	private String kodaKnjige;

	private List<OcenaKnjigeEnostavna> ocena = new ArrayList<OcenaKnjigeEnostavna>();

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Embedded
	@AttributeOverrides(value = {
			@AttributeOverride(name = "ime", column = @Column(name = "ime_avtorja")),
			@AttributeOverride(name = "priimek", column = @Column(name = "priimek_avtorja"))
	})
	public Avtor getAvtor() {
		return avtor;
	}

	public void setAvtor(Avtor avtor) {
		this.avtor = avtor;
	}
	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	public List<String> getZanr() {
		return zanr;
	}

	public void setZanr(List<String> zanr) {
		this.zanr = zanr;
	}

	public int getSteviloStrani() {
		return steviloStrani;
	}

	public void setSteviloStrani(int steviloStrani) {
		this.steviloStrani = steviloStrani;
	}

	public int getLetoIzdaje() {
		return letoIzdaje;
	}

	public void setLetoIzdaje(int letoIzdaje) {
		this.letoIzdaje = letoIzdaje;
	}

	public String getKodaKnjige() {
		return kodaKnjige;
	}

	public void setKodaKnjige(String kodaKnjige) {
		this.kodaKnjige = kodaKnjige;
	}

	@JsonbTransient
	@OneToMany(mappedBy = "knjiga")
	public List<OcenaKnjigeEnostavna> getOcena() {
		return ocena;
	}

	public void setOcena(List<OcenaKnjigeEnostavna> ocena) {
		this.ocena = ocena;
	}
	
}
