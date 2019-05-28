package si.um.feri.javaee.knjiznica.ejb;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import si.um.feri.javaee.knjiznica.vao.Clan;
import si.um.feri.javaee.knjiznica.vao.Izposoja;
import si.um.feri.javaee.knjiznica.vao.Knjiga;

@Stateless
public class IzposojeBean implements Izposoje {

	Logger log=Logger.getLogger(IzposojeBean.class.getSimpleName());

	@PersistenceContext
	EntityManager em;

	@EJB
	Knjige knjigeEjb;
	
	 @EJB
	 ClaniBean claniEjb;
	
	@Override
	public List<Izposoja> getIzposoje() {
		log.info("getIzposoje()");
		return em.createQuery("select i from Izposoja i where (i.datumIzposoje != null) and (i.datumVrnitve = null)").getResultList();
	}
	
	@Override
	public List<Izposoja> getRezervacije() {
		log.info("getRezervacije()");
		return em.createQuery("select i from Izposoja i where (i.datumIzposoje = null) and (i.datumVrnitve = null)").getResultList();
	}
	
	@Override
	public List<Izposoja> getIzposoje(String kodaClana) {
		log.info("getIzposoje("+kodaClana+")");
		Query q=em.createQuery("select i from Izposoja i where (i.clan.kodaClana = :kc) and (i.datumIzposoje != null) and (i.datumVrnitve = null)");
		q.setParameter("kc", kodaClana);
		return q.getResultList();
	}
	
	@Override
	public List<Izposoja> getRezervacije(String kodaClana) {
		log.info("getRezervacije("+kodaClana+")");
		Query q=em.createQuery("select i from Izposoja i where (i.clan.kodaClana = :kc) and (i.datumIzposoje = null) and (i.datumVrnitve = null)");
		q.setParameter("kc", kodaClana);
		return q.getResultList();
	}
	
	@Override
	public Izposoja rezerviraj(String kodaKnjige, String kodaClana) {
		log.info("rezerviraj("+kodaKnjige+"-"+kodaClana+")");
		if (najdiRezervacijo(kodaKnjige,kodaClana)!=null) {
			log.info("rezerviraj("+kodaKnjige+"-"+kodaClana+") --> rezervacija že obstaja");
			return null;
		}
		if (najdiIzposojo(kodaKnjige,kodaClana)!=null) {
			log.info("rezerviraj("+kodaKnjige+"-"+kodaClana+") --> rezervacija že obstaja");
			return null;
		}
		Knjiga k=knjigeEjb.najdi(kodaKnjige);
		Clan c=claniEjb.najdi(kodaClana);
		Izposoja i=new Izposoja();
		i.setClan(c);
		i.setKnjiga(k);
		em.persist(i);
		return i;
	}
	
	@Override
	public Izposoja najdiRezervacijo(String kodaKnjige, String kodaClana) {
		log.info("najdiRezervacijo("+kodaKnjige+"-"+kodaClana+")");
		Query q=em.createQuery("select i from Izposoja i where (i.knjiga.kodaKnjige = :kk) and (i.clan.kodaClana = :kc) and (i.datumIzposoje = null) and (i.datumVrnitve = null)");
		q.setParameter("kk", kodaKnjige);
		q.setParameter("kc", kodaClana);
		try {
			return (Izposoja)q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Izposoja najdiIzposojo(String kodaKnjige, String kodaClana) {
		log.info("najdiIzposojo("+kodaKnjige+"-"+kodaClana+")");
		Query q=em.createQuery("select i from Izposoja i where (i.knjiga.kodaKnjige = :kk) and (i.clan.kodaClana = :kc) and (i.datumVrnitve = null)");
		q.setParameter("kk", kodaKnjige);
		q.setParameter("kc", kodaClana);
		try {
			return (Izposoja)q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Izposoja izposodi(String kodaKnjige, String kodaClana) {
		log.info("izposodi("+kodaKnjige+"-"+kodaClana+")");
		Izposoja i=najdiRezervacijo(kodaKnjige, kodaClana);
		if (i==null) {
			Knjiga k=knjigeEjb.najdi(kodaKnjige);
			Clan c=claniEjb.najdi(kodaClana);
			i=new Izposoja();
			i.setClan(c);
			i.setKnjiga(k);
		}
		i.setDatumIzposoje(new GregorianCalendar());
		em.persist(i);
		return i;
	}

	@Override
	public void vrni(String kodaKnjige, String kodaClana) {
		log.info("vrni("+kodaKnjige+"-"+kodaClana+")");
		Izposoja i=najdiIzposojo(kodaKnjige, kodaClana);
		if (i==null) {
			Knjiga k=knjigeEjb.najdi(kodaKnjige);
			Clan c=claniEjb.najdi(kodaClana);
			i=new Izposoja();
			i.setClan(c);
			i.setKnjiga(k);
		}
		i.setDatumVrnitve(new GregorianCalendar());
		em.persist(i);
	}

}
