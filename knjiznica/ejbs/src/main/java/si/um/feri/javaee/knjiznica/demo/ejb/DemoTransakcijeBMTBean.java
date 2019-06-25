package si.um.feri.javaee.knjiznica.demo.ejb;


import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import si.um.feri.javaee.knjiznica.vao.Knjigomat;

/**
 * Demonstracija zrna, kjer uporabljamo lastno zagotvaljanje dela s tranasakcijami (BMT)
 * Pozor: pri poizvedbah transakcija NI potrebna
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class DemoTransakcijeBMTBean {

	Logger log = Logger.getLogger(DemoTransakcijeBMTBean.class.getSimpleName());

	@PersistenceContext
	EntityManager em;

	@Resource
	SessionContext sctx;

//	@Resource
//	UserTransaction tx;

	public void vstaviKnjigomate() throws Exception {
		log.info("vstaviKnjigomate()");
		UserTransaction ut = sctx.getUserTransaction();
		try {
			ut.begin();
			for (int i = 0; i < 100; i++) {
				em.persist(new Knjigomat());
			}
			ut.commit();
			log.info("ut.commit()");
		} catch (Exception ex) {
			ut.rollback();
			log.info("ut.rollback()");
		}
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Knjigomat> getVse() {
//		log.info("vrniVse()");
//		return em.createQuery("select k from Knjigomat k").getResultList();
//	}

}
