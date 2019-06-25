package si.um.feri.javaee.knjiznica.demo.ejb;

import java.util.logging.Logger;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * V primeru, da delamo z edinci, je potrebno biti pozoren na zaklepanje zrna
 * WRITE zaklepanje, zaklene vse metode razreda - klici se serializirajo
 * READ pomeni, da dovolimo sočasen klic metode na istem primerku zrna
 */
@Singleton
public class DemoLockBean {

	Logger log = Logger.getLogger(DemoLockBean.class.getSimpleName());

	@PersistenceContext
	EntityManager em;
	
	@Lock(LockType.WRITE)
	public void metodaA() throws Exception {
		log.info("metodaA() - zacetek "+this);
		Thread.sleep(5000);
		log.info("metodaA() - konec "+this);
	}

	@Lock(LockType.READ)
	public void metodaB() throws Exception {
		log.info("metodaB() - zacetek "+this);
		Thread.sleep(5000);
		log.info("metodaB() - konec "+this);
	}

}
