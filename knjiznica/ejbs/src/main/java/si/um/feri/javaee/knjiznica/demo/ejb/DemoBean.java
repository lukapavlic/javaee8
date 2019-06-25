package si.um.feri.javaee.knjiznica.demo.ejb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import si.um.feri.javaee.knjiznica.demo.jpa.AvtorCriteria;
import si.um.feri.javaee.knjiznica.demo.jpa.KnjigaCriteria;
import si.um.feri.javaee.knjiznica.demo.jpa.KnjigomatCriteria;
import si.um.feri.javaee.knjiznica.demo.jpa.Oddelek;
import si.um.feri.javaee.knjiznica.demo.jpa.Telefon;
import si.um.feri.javaee.knjiznica.demo.jpa.Zaposlen;
import si.um.feri.javaee.knjiznica.vao.Clan;
import si.um.feri.javaee.knjiznica.vao.Knjiga;
import si.um.feri.javaee.knjiznica.vao.Knjigomat;
import si.um.feri.javaee.knjiznica.vao.OcenaKnjigeEnostavna;
import si.um.feri.javaee.knjiznica.vao.OcenaKnjigeSKomentarjem;

/**
 * Zrno se uporablja direktno iz gumbov spletne aplikacije v namen demonstracije (asinhroni klici, JPA, ipd.)
 *
 */
@Stateless
@LocalBean
public class DemoBean {

	Logger log = Logger.getLogger(DemoBean.class.getSimpleName());

	@EJB
	StevecBean stevec1;

	@EJB
	StevecBean stevec2;

	@EJB
	StevecBean stevec3;

	@Asynchronous
	public void stej1() throws Exception {
		log.info("stej1()");
		stevec1.setIme("PRVI");
		for (int i = 0; i < 20; i++)
			log.info(stevec1.dajmo());
	}

	@Asynchronous
	public void stej2() throws Exception {
		log.info("stej1()");
		stevec2.setIme("DRUGI");
		for (int i = 0; i < 20; i++)
			log.info(stevec2.dajmo());
	}

	@Asynchronous
	public void stej3() throws Exception {
		log.info("stej1()");
		stevec3.setIme("TRETJI");
		for (int i = 0; i < 20; i++)
			log.info(stevec3.dajmo());
	}

	public void dolgoTraja() {
		log.info("dolgoTraja() - zacetek");
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
				log.info("dolgoTraja() - " + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		log.info("dolgoTraja() - konec");
	}

	@Asynchronous
	public void dolgoTrajaAsinhrono() {
		log.info("dolgoTraja() - zacetek");
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
				log.info("dolgoTraja() - " + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log.info("dolgoTraja() - konec");
	}

	@Asynchronous
	public Future<String> pozdravAsinhrono(String ime) {
		log.info("dolgoTraja() - zacetek");
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
				log.info("dolgoTraja() - " + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log.info("dolgoTraja() - konec; Zdravo, " + ime);
		return new AsyncResult<String>("Zdravo, " + ime);
	};

	// -- JPA --

	Random r = new Random();

	@PersistenceContext
	EntityManager em;

	public void testniPodatki() {
		log.info("testniPodatki()");

		Knjiga k = new Knjiga();
		k.getAvtor().setIme("Ivan");
		k.getAvtor().setPriimek("Cankar");
		k.setNaslov("Pohujšanje v dolini Šentflorjanski");
		k.setKodaKnjige("ICAN1");
		k.setLetoIzdaje(1908);
		k.setSteviloStrani(82);
		k.setOpis("Pohujšanje v dolini šentflorjanski: Farsa v treh aktih je dramsko delo Ivana Cankarja.");
		k.getZanr().add("");

		OcenaKnjigeEnostavna ocena = new OcenaKnjigeEnostavna();
		ocena.setOcena(3);
		ocena.setKnjiga(k);
		k.getOcena().add(ocena);
		em.persist(k);
		log.info("persist " + k);
		em.persist(ocena);
		log.info("persist " + ocena);

		k = new Knjiga();
		k.getAvtor().setIme("Feri");
		k.getAvtor().setPriimek("Lainšček");
		k.setNaslov("Namesto koga roža cveti");
		k.setKodaKnjige("FLAIN1");
		k.setLetoIzdaje(1991);
		k.setSteviloStrani(186);
		k.setOpis(
				"Delo je pripoved o življenju, mladosti in odraščanju dveh Romov, Halgata in njegovega prijatelja Pištija.");
		k.getZanr().add("LEPOSLOVJE");
		k.getZanr().add("MLADINSKIROMAN");

		OcenaKnjigeSKomentarjem ocenaK = new OcenaKnjigeSKomentarjem();
		ocenaK.setOcena(5);
		ocenaK.setKomentar("Odlična knjiga, zelo priporočam");
		ocenaK.setKnjiga(k);
		k.getOcena().add(ocenaK);
		em.persist(k);
		log.info("persist " + k);
		em.persist(ocenaK);
		log.info("persist " + ocenaK);

		Knjigomat kg = new Knjigomat();
		kg.setKapaciteta(14);
		kg.setNaslov("Koroška c 22");
		kg.setNaziv("Knjigomat na Koroški");
		em.persist(kg);
		log.info("persist " + kg);

		kg = new Knjigomat();
		kg.setKapaciteta(22);
		kg.setNaslov("Studenci 12");
		kg.setNaziv("Knjigomat Danice Vogrinec");
		em.persist(kg);
		log.info("persist " + kg);

		Clan c = new Clan();
		c.setEmail("martin.krpan@gmail.com");
		c.setIme("Martin");
		c.setPriimek("Krpan");
		c.setId("mkrp" + r.nextInt(1000));
		em.persist(c);
		log.info("persist " + c);

		c = new Clan();
		c.setEmail("peter.klepec@gmail.com");
		c.setIme("Peter");
		c.setPriimek("Klepec");
		c.setId("pklp" + r.nextInt(1000));
		em.persist(c);
		log.info("persist " + c);

	}

	public void testJpaQl() {
		Zaposlen z = new Zaposlen();
		z.setIme("Janez");
		z.setPlaca(1299);

		Oddelek o = new Oddelek();
		o.setIme("Oblikovanje");

		z.setOddelek(o);
		o.getZaposleni().add(z);

		Telefon tel = new Telefon();
		tel.setStevilka("111-111-1111");
		tel.setZaposlen(z);
		z.getTelefons().add(tel);

		em.persist(z);
		em.persist(tel);
		em.persist(o);

		Zaposlen z1 = new Zaposlen();
		z1.setIme("Peter");
		z1.setPlaca(1399);

		Oddelek o1 = new Oddelek();
		o1.setIme("Razvoj");

		z1.setOddelek(o1);
		o1.getZaposleni().add(z1);

		Telefon tel1 = new Telefon();
		tel1.setStevilka("222-222-2222");
		tel1.setZaposlen(z1);
		z1.getTelefons().add(tel1);

		em.persist(z1);
		em.persist(tel1);
		em.persist(o1);

		Zaposlen z3 = new Zaposlen();
		z3.setIme("Luka");
		z3.setPlaca(1300);

		Oddelek o3 = new Oddelek();
		o3.setIme("Kadrovska");

		z3.setOddelek(o3);
		o3.getZaposleni().add(z3);

		Telefon tel3 = new Telefon();
		tel3.setStevilka("333-333-3333");
		tel3.setZaposlen(z3);
		z3.getTelefons().add(tel3);

		em.persist(z3);
		em.persist(tel3);
		em.persist(o3);

		// poizvedovanje
		// SUM, AVG
		log.info("SUM, AVG");
		List l = em.createQuery("SELECT z FROM Zaposlen z").getResultList();
		for (Object obj : l) {
			izpisirezultat(obj);
		}
		List list = em.createQuery("SELECT SUM(z.placa) FROM Zaposlen z").getResultList();
		for (Object obj : list) {
			izpisirezultat(obj);
		}

		// COUNT, GROUP BY
		// št zaposlenih na določenem oddelku ter povprečje plač
		log.info("COUNT, GROUP BY");
		List l1 = em
				.createQuery("SELECT o.ime, COUNT(z), AVG(z.placa) FROM Oddelek o JOIN o.zaposleni z GROUP BY o.ime")
				.getResultList();
		for (Object obj : l1) {
			izpisirezultat(obj);
		}

		log.info("GROUP BY");
		List l11 = em.createQuery("SELECT z.ime, COUNT(z) FROM Zaposlen z GROUP BY z.ime").getResultList();
		for (Object obj : l11) {
			izpisirezultat(obj);
		}

		// =: vhodni parameter
		// vsi zaposleni, ki delajo na oddelku Razvoj
		log.info("Vhodni parameter");
		Query q = em.createQuery("SELECT z FROM Zaposlen z JOIN z.oddelek o WHERE o.ime =: ime");
		q.setParameter("ime", "Razvoj");
		List l2 = q.getResultList();	

		for (Object obj : l2) {
			izpisirezultat(obj);
		}

		// ORDER BY
		log.info("ORDER BY");
		List l3 = em.createQuery("SELECT z FROM Zaposlen z ORDER BY z.placa ASC").getResultList();

		for (Object obj : l3) {
			izpisirezultat(obj);
		}

		// dedovanje
		log.info("Dedovanje");
		List l4 = em.createQuery("SELECT o FROM OcenaKnjigeEnostavna o").getResultList();

		for (Object obj : l4) {
			izpisirezultat(obj);
		}
	}

	public void testJPACriteria() {
		log.info("testJPACriteria()");

		KnjigaCriteria k = new KnjigaCriteria();
		k.setNaslov("Pohujšanje v dolini Šentflorjanski");
		k.setLetoIzdaje(1908);

		AvtorCriteria a = new AvtorCriteria();
		a.setIme("Ivan");
		a.setPriimek("Cankar");
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
		try {
			a.setDatumRojstva(df.parse("1960.1.20"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		a.getKnjige().add(k);
		k.setAvtor(a);

		KnjigomatCriteria kg = new KnjigomatCriteria();
		kg.setLokacija("Koroška c 22");
		kg.getKnjige().add(k);
		k.setKnjigomat(kg);

		em.persist(k);
		log.info("persist " + k);
		em.persist(a);
		log.info("persist " + a);
		em.persist(kg);
		log.info("persist " + kg);

		k = new KnjigaCriteria();
		k.setNaslov("Namesto koga roža cveti");
		k.setLetoIzdaje(1991);

		a = new AvtorCriteria();
		a.setIme("Feri");
		a.setPriimek("Lainšček");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy.MM.dd");
		try {
			a.setDatumRojstva(df1.parse("1962.1.20"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		a.getKnjige().add(k);
		k.setAvtor(a);

		kg = new KnjigomatCriteria();
		kg.setLokacija("Studenci 12");
		kg.getKnjige().add(k);
		k.setKnjigomat(kg);

		em.persist(k);
		log.info("persist " + k);
		em.persist(a);
		log.info("persist " + a);
		em.persist(kg);
		log.info("persist " + kg);

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<KnjigaCriteria> criteriaQueryKnjiga = criteriaBuilder.createQuery(KnjigaCriteria.class);
		CriteriaQuery<AvtorCriteria> criteriaQueryAvtor = criteriaBuilder.createQuery(AvtorCriteria.class);
		CriteriaQuery<KnjigomatCriteria> criteriaQueryKnjigomat = criteriaBuilder.createQuery(KnjigomatCriteria.class);

		Root<KnjigaCriteria> knjigaC = criteriaQueryKnjiga.from(KnjigaCriteria.class);
		Root<AvtorCriteria> avtorC = criteriaQueryAvtor.from(AvtorCriteria.class);
		Root<KnjigomatCriteria> knjigomatC = criteriaQueryKnjigomat.from(KnjigomatCriteria.class);

		System.out.println("vse knjige");
		CriteriaQuery<KnjigaCriteria> selectKnjige = criteriaQueryKnjiga.select(knjigaC);
		TypedQuery<KnjigaCriteria> typedQueryKnjige = em.createQuery(selectKnjige);
		List<KnjigaCriteria> resultlistKnjige = typedQueryKnjige.getResultList();

		for (Object o : resultlistKnjige) {
			KnjigaCriteria e = (KnjigaCriteria) o;
			System.out.println("ID : " + e.getId() + " Naslov : " + e.getNaslov());
		}

		System.out.println("vsi avtorji");
		CriteriaQuery<AvtorCriteria> selectAvtorje = criteriaQueryAvtor.select(avtorC);
		TypedQuery<AvtorCriteria> typedQueryAvtorje = em.createQuery(selectAvtorje);
		List<AvtorCriteria> resultlistAvtorje = typedQueryAvtorje.getResultList();

		for (Object o : resultlistAvtorje) {
			AvtorCriteria e = (AvtorCriteria) o;
			System.out.println("ID : " + e.getId() + " Ime : " + e.getIme() + " Priimek : " + e.getPriimek());
		}

		System.out.println("vsi knjigomati");
		CriteriaQuery<KnjigomatCriteria> selectKnjigomati = criteriaQueryKnjigomat.select(knjigomatC);
		TypedQuery<KnjigomatCriteria> typedQueryKnjigomati = em.createQuery(selectKnjigomati);
		List<KnjigomatCriteria> resultlistKnjigomati = typedQueryKnjigomati.getResultList();

		for (Object o : resultlistKnjigomati) {
			KnjigomatCriteria e = (KnjigomatCriteria) o;
			System.out.println("ID : " + e.getId() + " Lokacija : " + e.getLokacija());
		}

		// Join
		System.out.println("Join");
		criteriaQueryKnjiga = criteriaBuilder.createQuery(KnjigaCriteria.class);
		avtorC = criteriaQueryKnjiga.from(AvtorCriteria.class);
		Join<AvtorCriteria, KnjigaCriteria> knjige = avtorC.join("knjige");
		criteriaQueryKnjiga.select(knjige).where(criteriaBuilder.equal(avtorC.get("priimek"), "Cankar"));

		List<KnjigaCriteria> resultsJoin = em.createQuery(criteriaQueryKnjiga).getResultList();
		for (Object kn : resultsJoin) {
			KnjigaCriteria knj = (KnjigaCriteria) kn;
			System.out.println("Naslov = " + knj.getNaslov());
		}

		// distinct
		System.out.println("Distinct");
		CriteriaQuery<KnjigaCriteria> selectDistinct = criteriaQueryKnjiga.select(knjigaC);
		selectDistinct.select(knjigaC.get("naslov")).distinct(true);

		List<KnjigaCriteria> resultsDistinct = em.createQuery(selectDistinct).getResultList();
		for (Object kn : resultsDistinct) {
			System.out.println("Naslov = " + kn);
		}

		// where + between
		criteriaQueryKnjiga = criteriaBuilder.createQuery(KnjigaCriteria.class);
		criteriaQueryAvtor = criteriaBuilder.createQuery(AvtorCriteria.class);
		avtorC = criteriaQueryAvtor.from(AvtorCriteria.class);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = dateFormat.parse("1959.1.20");
			date2 = dateFormat.parse("1961.1.20");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Where + between");
		CriteriaQuery q = criteriaQueryAvtor.select(avtorC);
		q.where(criteriaBuilder.between(avtorC.get("datumRojstva"), date1, date2));
		List<AvtorCriteria> resultList = em.createQuery(q).getResultList();
		System.out.println(resultList.size());
		for (Object o : resultList) {
			AvtorCriteria e = (AvtorCriteria) o;
			System.out.println("ID : " + e.getId() + " Ime : " + e.getIme() + " Priimek : " + e.getPriimek());
		}

		// group by + having
		System.out.println("group by + having");
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<KnjigaCriteria> cq = cb.createQuery(KnjigaCriteria.class);
		Root<KnjigaCriteria> kc = cq.from(KnjigaCriteria.class);
		cq.select(kc);
		cq.groupBy(kc);
		cq.having(criteriaBuilder.in(kc.get("naslov")).value(""));
		List<KnjigaCriteria> resultListgBy = em.createQuery(cq).getResultList();
		System.out.println(resultListgBy.size());

		for (Object obj : resultListgBy) {
			KnjigaCriteria knjiga = (KnjigaCriteria) obj;
			System.out.println("naslov = " + knjiga.getNaslov());
		}

		// order by
		// select k.letoizdaje from knjigacriteria k GROUP BY k.letoizdaje ORDER BY k.letoizdaje;
		System.out.println("order by");

		CriteriaQuery<KnjigaCriteria> q1 = cb.createQuery(KnjigaCriteria.class);
		Root<KnjigaCriteria> k1 = q1.from(KnjigaCriteria.class);
		q1.select(k1);
		q1.orderBy(cb.asc(k1.get("letoIzdaje")));
		List<KnjigaCriteria> resultListgOrder = em.createQuery(q1).getResultList();
		System.out.println(resultListgOrder.size());

		for (Object kc1 : resultListgOrder) {
			KnjigaCriteria knjiga = (KnjigaCriteria) kc1;
			System.out.println("naslov = " + knjiga.getNaslov());
		}
	}

	/**
	 * Ustrezno izpiše rezultat glede na tipa objekta, ki ga dobi
	 * @param r
	 */
	public void izpisirezultat(Object r) {
		if (r == null) {
			System.out.print("NULL");
		} else if (r instanceof Object[]) {
			Object[] row = (Object[]) r;
			System.out.print("[");
			for (int i = 0; i < row.length; i++) {
				this.izpisirezultat(row[i]);
			}
			System.out.print("]");
		} else if (r instanceof Long || r instanceof Double || r instanceof String) {
			System.out.print(r.getClass().getName() + ": " + r);
		} else {
			System.out.print(r);
		}
		System.out.println();
	}

}
