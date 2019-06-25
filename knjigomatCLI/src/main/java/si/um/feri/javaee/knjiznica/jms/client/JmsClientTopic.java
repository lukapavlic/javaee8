package si.um.feri.javaee.knjiznica.jms.client;

import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;

public class JmsClientTopic {
	
	public static void main(String[] args) throws Exception {

		InitialContext ctx = new InitialContext();
		Topic topic = (Topic) ctx.lookup("jms/topic/test");
		TopicConnectionFactory factory = (TopicConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory");

		TopicConnection cnn = factory.createTopicConnection("guest","guest");
		TopicSession session = cnn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
		TopicPublisher publisher = session.createPublisher(topic);

		publisher.publish(session.createTextMessage("dodaj_knjigomat:SlovenjGradec"));
		publisher.publish(session.createTextMessage("dodaj_clana:MojcaPokrajculja"));
		
		Message m=session.createTextMessage("dodaj_knjigomat:Piran");
		publisher.publish(m,DeliveryMode.NON_PERSISTENT,3,2000);

		session.close();

	}

}
