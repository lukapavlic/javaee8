package si.um.feri.javaee.knjiznica.jaxb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Razred, ki ga uporabljamo za serializacijo in deserializacijo
 */
@XmlRootElement(name = "oseba")
@XmlAccessorType(XmlAccessType.FIELD)
public class Oseba implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ime;
	private String priimek;
	private String naslov;
	private int starost;
	private boolean clan;

	@XmlElementWrapper(name = "IzposojeneKnjige")
	private List<String> knjige = new ArrayList<String>();

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

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public boolean isClan() {
		return clan;
	}

	public void setClan(boolean clan) {
		this.clan = clan;
	}

	public int getStarost() {
		return starost;
	}

	public void setStarost(int starost) {
		this.starost = starost;
	}

	private String isClanToString() {
		return this.clan ? "Da" : "Ne";
	}

	public List<String> getKnjige() {
		return knjige;
	}

	public void setKnjige(List<String> knjige) {
		this.knjige = knjige;
	}

	@Override
	public String toString() {
		String izpKnjige = "";

		for (String knjiga : knjige) {
			izpKnjige += "\t" + knjiga + " \n";
		}
		return "Ime in priimek: " + this.ime + " " + this.priimek + " \n" + "Naslov: " + this.naslov + " \n"
				+ "Starost: " + this.starost + " \n" + "Je član: " + isClanToString() + " \n" + "Izposojene knjige: \n"
				+ izpKnjige;
	}
}
