package si.um.feri.javaee.knjiznica.vao.json;

import javax.persistence.MappedSuperclass;

/**
 * VAO, ki demonstrira preslikovanje v JSON
 * Hkrati se uporabla v JPA za preslikovanje v podatkovno bazo
 * @see si.um.feri.javaee.knjiznica.vao.Clan
 */
@MappedSuperclass
public class ClanJson {

	private String ime;
	private String priimek;
	private String email;
	private String koda;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getKoda() {
		return koda;
	}
	public void setKoda(String koda) {
		this.koda = koda;
	}
	
}
