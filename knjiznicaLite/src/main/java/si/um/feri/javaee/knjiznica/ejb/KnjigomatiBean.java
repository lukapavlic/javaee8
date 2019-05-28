package si.um.feri.javaee.knjiznica.ejb;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import si.um.feri.javaee.knjiznica.vao.Knjigomat;

@Stateless
@LocalBean
public class KnjigomatiBean {

	Logger log=Logger.getLogger(KnjigomatiBean.class.getSimpleName());
	
	@PersistenceContext
	EntityManager em;

	public void shrani(Knjigomat k) {
		log.info("shrani("+k+")");
		if (k.getId()==0) em.persist(k);
		else em.merge(k);
	}
	
	public List<Knjigomat> vrniVse() {
		log.info("vrniVse()");
		return em.createQuery("select k from Knjigomat k").getResultList();
	}
	
}
