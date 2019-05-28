package si.um.feri.javaee.knjiznica.vao;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Avtor implements Serializable {

	private static final long serialVersionUID = -4393966913730046632L;

	private String ime;
	private String priimek;
	
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
	
}
