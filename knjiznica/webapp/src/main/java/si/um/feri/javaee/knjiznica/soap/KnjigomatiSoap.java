package si.um.feri.javaee.knjiznica.soap;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.WebService;
import si.um.feri.javaee.knjiznica.ejb.KnjigomatiBean;
import si.um.feri.javaee.knjiznica.vao.Knjigomat;

/**
 * Spletna storitev za delo s knjigomati
 */
@WebService
public class KnjigomatiSoap {

	Logger log=Logger.getLogger(KnjigomatiSoap.class.getSimpleName());
	
	@EJB
	KnjigomatiBean ejb;
	
	public void shrani(Knjigomat k) {
		log.info("shrani("+k+")");
		ejb.shrani(k);
	}
	
	public List<Knjigomat> vrniVse() {
		log.info("vrniVse()");
		return ejb.vrniVse();
	}
	
}
