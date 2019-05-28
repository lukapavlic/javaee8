package si.um.feri.javaee.knjiznica.vao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Izposoja implements Serializable {

	private static final long serialVersionUID = 6570861013744764988L;

	private int id;
	private Knjiga knjiga;
	private Clan clan;
	private Calendar datumRezervacije=new GregorianCalendar();
	private Calendar datumIzposoje;
	private Calendar datumVrnitve;
	private String komentar;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}

	public Calendar getDatumIzposoje() {
		return datumIzposoje;
	}

	public void setDatumIzposoje(Calendar datumIzposoje) {
		this.datumIzposoje = datumIzposoje;
	}

	public Calendar getDatumVrnitve() {
		return datumVrnitve;
	}

	public void setDatumVrnitve(Calendar datumVrnitve) {
		this.datumVrnitve = datumVrnitve;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public Calendar getDatumRezervacije() {
		return datumRezervacije;
	}

	public void setDatumRezervacije(Calendar datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
	}
	
}
