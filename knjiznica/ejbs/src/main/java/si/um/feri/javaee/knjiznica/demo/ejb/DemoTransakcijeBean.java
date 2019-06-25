package si.um.feri.javaee.knjiznica.demo.ejb;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import si.um.feri.javaee.knjiznica.vao.Knjigomat;

/**
 * Demonstracija zrna, kjer uporabljamo strežniško zagotavljanje dela s tranasakcijami (CMT)
 * Transakcije imamo možnost preklicati (rollback) ali določati posebne zahteve glede transakcij na nivoju metode
 * Sicer pa je meja transakcije (prvo klicana) EJB metoda
 * Privzeti atribut pri metodi je TransactionAttributeType.REQUIRED, razen pri asinhronih metodah je TransactionAttributeType.REQUIRES_NEW
 */
@Stateless
@LocalBean
public class DemoTransakcijeBean {

	Logger log = Logger.getLogger(DemoTransakcijeBean.class.getSimpleName());

	@PersistenceContext
	EntityManager em;
	
	@Resource
	SessionContext sctx;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void vstaviKnjigomate() throws Exception {
		for (int i=0;i<100;i++) {
			em.persist(new Knjigomat());
			Thread.sleep(10);
			log.info("vstaviKnjigomate()"+i);
		}
		//sctx.setRollbackOnly();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delegat() throws Exception {
		log.info("delegat() z");
		vstaviKnjigomate();
		vstaviKnjigomate();
		log.info("delegat() k");
	}
	

}
