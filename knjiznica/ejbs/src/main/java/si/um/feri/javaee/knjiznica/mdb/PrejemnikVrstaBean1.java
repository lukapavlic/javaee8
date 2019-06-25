package si.um.feri.javaee.knjiznica.mdb;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import si.um.feri.javaee.knjiznica.ejb.ClaniBean;
import si.um.feri.javaee.knjiznica.ejb.KnjigomatiBean;
import si.um.feri.javaee.knjiznica.vao.Clan;
import si.um.feri.javaee.knjiznica.vao.Knjigomat;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/topic/test"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") 
		})
public class PrejemnikVrstaBean1 implements MessageListener {

	Logger log=Logger.getLogger(PrejemnikVrstaBean1.class.getSimpleName());
	
	@EJB
	KnjigomatiBean ejbKnjigomati;
	
	@EJB
	ClaniBean ejbClani;
	
	public void onMessage(Message message) {

		if (message instanceof TextMessage) {
			TextMessage tm = (TextMessage) message;
			try {
				log.info(tm.getText());
				
				String s=tm.getText();
				String[] ukazi=s.split(":");
				if (ukazi.length==2 && ukazi[0].equals("dodaj_knjigomat")) {
					Knjigomat k=new Knjigomat();
					k.setNaziv(ukazi[1]);
					ejbKnjigomati.shrani(k);
				}
				if (ukazi.length==2 && ukazi[0].equals("dodaj_clana")) {
					Clan c=new Clan();
					c.setIme(ukazi[1]);
					c.setEmail("x@y.com");
					c.setId(System.currentTimeMillis()+"");
					ejbClani.shrani(c);
				}
				
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Prispelo je neznano sporocilo.");
		}
	}

}
