package si.um.feri.javaee.knjiznica.ejb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import si.um.feri.javaee.knjiznica.vao.Clan;
import si.um.feri.javaee.knjiznica.vao.Knjiga;

@Stateless
@Remote(Knjige.class)
public class KnjigeBean implements Knjige {

	SimpleDateFormat date_format=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

	Logger log=Logger.getLogger(KnjigeBean.class.getSimpleName());
			
	@PersistenceContext
	EntityManager em;
	
	@Override
	public void shrani(Knjiga k) {
		log.info("shrani("+k+")");
		if (najdi(k.getId())==null) {
			em.persist(k);
			if (k.getKodaKnjige()==null) k.setKodaKnjige(date_format.format(new Date())+"-"+k.getId());
		}
		else em.merge(k);
	}
	
	public Knjiga najdi(int id) {
		log.info("najdi("+id+")");
		return em.find(Knjiga.class, id);
	}

	@Override
	public List<Knjiga> vrniVse(String zanr) {
		log.info("vrniVse("+zanr+")");
		Query q=null;
		if (zanr==null || zanr.trim().equals("")) q=em.createQuery("select k from Knjiga k");
		else {
			q=em.createQuery("select k from Knjiga k where :zanr member of k.zanr");
			q.setParameter("zanr", zanr);
		}
		return q.getResultList();
	}

	@Override
	public Knjiga najdi(String koda) {
		log.info("najdi("+koda+")");
		Query q=em.createQuery("select k from Knjiga k where k.kodaKnjige = :koda");
		q.setParameter("koda", koda);
		return (Knjiga)q.getSingleResult();
	}
	
	@Override
	public List<Knjiga> poisci(String iskalniNiz) {
		log.info("poisci("+iskalniNiz+")");
		Query q=em.createQuery("select k from Knjiga k where k.naslov like :niz");
		q.setParameter("niz", "%"+iskalniNiz+"%");
		return q.getResultList();
	}

}
