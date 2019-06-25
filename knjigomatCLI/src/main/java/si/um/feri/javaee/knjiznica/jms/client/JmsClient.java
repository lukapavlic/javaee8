package si.um.feri.javaee.knjiznica.jms.client;

import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;

public class JmsClient {
	
	public static void main(String[] args) throws Exception {

		InitialContext ctx = new InitialContext();
		Queue queue = (Queue) ctx.lookup("jms/queue/test");
		QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory");

		QueueConnection cnn = factory.createQueueConnection("guest","guest");
		QueueSession session = cnn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
		QueueSender sender = session.createSender(queue);

		sender.send(session.createTextMessage("dodaj_knjigomat:SlovenjGradec"));
		sender.send(session.createTextMessage("dodaj_clana:MojcaPokrajculja"));
		
		Message m=session.createTextMessage("dodaj_knjigomat:Piran");
		sender.send(m,DeliveryMode.NON_PERSISTENT,3,2000);

		session.close();

	}

}
