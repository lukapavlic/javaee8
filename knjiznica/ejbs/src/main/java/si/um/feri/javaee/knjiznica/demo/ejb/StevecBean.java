package si.um.feri.javaee.knjiznica.demo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Razerd demonstrira NEPRAVILNO uporabo sejnih zrn brez stanja!
 *
 */
@Stateless
@LocalBean
public class StevecBean {

	String ime;
	
	int stevec;
	
	public String dajmo() throws Exception {
		Thread.sleep(250);
		stevec++;
		return ime+"="+stevec;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}
	
}
