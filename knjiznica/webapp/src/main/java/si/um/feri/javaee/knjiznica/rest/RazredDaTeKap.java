package si.um.feri.javaee.knjiznica.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import si.um.feri.javaee.knjiznica.ejb.KnjigomatiBean;
import si.um.feri.javaee.knjiznica.vao.Knjigomat;

/**
 * Demonstracija razreda, ki je hkrati: lokalno sejno zrno, REST storitev, WSDL storitev
 *
 */
@Stateless
@LocalBean

@Path("/nidani")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@WebService
public class RazredDaTeKap {

	Logger log=Logger.getLogger(RazredDaTeKap.class.getSimpleName());
	
	@EJB
	KnjigomatiBean ejb;
	
	@POST
	@Path("/knjigomati")
	public void shrani(Knjigomat k) {
		log.info("shrani("+k+")");
		ejb.shrani(k);
	}
	
	@GET
	@Path("/knjigomati")
	public List<Knjigomat> vrniVse() {
		log.info("vrniVse()");
		return ejb.vrniVse();
	}
	
}
