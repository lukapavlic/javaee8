package si.um.feri.javaee.knjiznica.mdb;

import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Ta prejemnik iz vrste ima trajno naročnino - sporočila ga bodo počakala, tudi če ni na voljo v času dostave sporočila
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/topic/test"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"), 
		@ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
		@ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "PrejemnikVrstaBean2")
		})
public class PrejemnikVrstaBean2 implements MessageListener {

	Logger log=Logger.getLogger(PrejemnikVrstaBean2.class.getSimpleName());
	
	public void onMessage(Message message) {

		if (message instanceof TextMessage) {
			TextMessage tm = (TextMessage) message;
			try {
				log.info("TUDI DOBILA: "+tm.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Prispelo je neznano sporocilo.");
		}
	}

}
