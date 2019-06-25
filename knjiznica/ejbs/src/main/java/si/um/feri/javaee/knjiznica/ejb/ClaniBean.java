package si.um.feri.javaee.knjiznica.ejb;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import si.um.feri.javaee.knjiznica.vao.Clan;

/**
 * EJB brez vmesnika - na voljo bo le znotraj aplikacije
 * Pazljivo pri povezovanju nanj (@EJB oznaka!)
 */
@Stateless
@LocalBean
public class ClaniBean {

	@PersistenceContext
	EntityManager em;
	
	Logger log=Logger.getLogger(ClaniBean.class.getSimpleName());
	
	public void shrani(Clan c) {
		log.info("shrani("+c+")");
		if (najdi(c.getId())==null) em.persist(c);
		else em.merge(c);
	}
	
	public Clan najdi(String kodaClana) {
		log.info("najdi("+kodaClana+")");
		return em.find(Clan.class, kodaClana);
	}
	
	public Clan vrni(String kodaClana) {
		log.info("vrni("+kodaClana+")");
		Query q=em.createQuery("select c from Clan c where c.id = :koda");
		q.setParameter("koda", kodaClana);
		return (Clan)q.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Clan> vrniVse() {
		log.info("vrniVse()");
		Query q=em.createQuery("select c from Clan c");
		return q.getResultList();
	}
	
}
