package si.um.feri.javaee.knjiznica.jsf;

import java.io.Serializable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.Topic;

import si.um.feri.javaee.knjiznica.demo.ejb.DemoBean;
import si.um.feri.javaee.knjiznica.demo.ejb.DemoLockBean;
import si.um.feri.javaee.knjiznica.demo.ejb.DemoSerializable;
import si.um.feri.javaee.knjiznica.demo.ejb.DemoSerializableLocal;
import si.um.feri.javaee.knjiznica.demo.ejb.DemoSerializableRemote;
import si.um.feri.javaee.knjiznica.demo.ejb.DemoTransakcijeBMTBean;
import si.um.feri.javaee.knjiznica.demo.ejb.DemoTransakcijeBean;
import si.um.feri.javaee.knjiznica.vao.Knjigomat;

@Named("demo")
@SessionScoped
public class DemoJsfBean implements Serializable {

	private static final long serialVersionUID = 8921711242052520843L;

	Logger log = Logger.getLogger(DemoJsfBean.class.getSimpleName());
	
	@EJB
	DemoBean ejb;
	
	@EJB
	DemoLockBean ejbLock;

	@EJB
	DemoTransakcijeBean ejbTran;
	

	@EJB
	DemoTransakcijeBMTBean ejbTranBmt;

	@EJB
	DemoSerializableRemote ejbRemote;

	@EJB
	DemoSerializableLocal ejbLocal;

	@EJB
	DemoSerializable ejbLocalBean;

	public void posiljanjeObjekta() {
		Knjigomat k=new Knjigomat();
		log.info("Poslal bom "+k);
		
		ejbRemote.dajMi(k);
		ejbLocal.dajMi(k);
		ejbLocalBean.dajMi(k);
		
	}
	
	
	public DemoBean getEjb() {
		return ejb;
	}

	public DemoTransakcijeBean getEjbTran() {
		return ejbTran;
	}

	public DemoTransakcijeBMTBean getEjbTranBmt() {
		return ejbTranBmt;
	}

	public DemoLockBean getEjbLock() {
		return ejbLock;
	}
	
	public void dolgoTraja() {
		log.info("dolgoTraja() - zacetek");
		ejb.dolgoTraja();
		log.info("dolgoTraja() - konec");
	}

	public void dolgoTrajaAsinhrono() {
		log.info("dolgoTraja() - zacetek");
		ejb.dolgoTrajaAsinhrono();
		log.info("dolgoTraja() - konec");
	}
	
	public void pozdravAsinhrono() {
		log.info("dolgoTraja() - zacetek");
		Future<String> res=ejb.pozdravAsinhrono("Donald");
		try {
			System.out.println(res.get(20, TimeUnit.SECONDS));
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("dolgoTraja() - konec");
	}
	
	
	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/test")
	private Queue queue;

	@Resource(lookup = "java:/jms/topic/test")
	private Topic topic;

	public void posljiSporocila(String komu) throws Exception{
		
		log.info("posljiSporocila("+komu+")");
		
		Session session = connectionFactory.createConnection("guest","guest").createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
		MessageProducer producer = null;
		
		if (komu.equals("vrsta")) producer=session.createProducer(queue);
		else producer=session.createProducer(topic);

		producer.send(session.createTextMessage("dodaj_knjigomat:SlovenjGradec"));
		producer.send(session.createTextMessage("dodaj_clana:MojcaPokrajculja"));
		
		Message m=session.createTextMessage("dodaj_knjigomat:Piran");
		producer.send(m,DeliveryMode.NON_PERSISTENT,3,2000);

		session.close();
	}
	
}
