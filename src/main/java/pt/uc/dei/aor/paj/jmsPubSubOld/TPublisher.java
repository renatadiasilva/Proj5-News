package pt.uc.dei.aor.paj.jmsPubSubOld;

import java.io.*;
import javax.jms.*;
import javax.naming.*;

public class TPublisher {
	public static void main(String[] args) {
		new TPublisher().publish();
	}
	public void publish() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			//Prompt for JNDI names
			String factoryName = "jms/RemoteConnectionFactory";
			String topicName = "jms/topic/PlayTopic";
			//Look up administered objects
			InitialContext initContext = new InitialContext();
			TopicConnectionFactory factory =
					(TopicConnectionFactory) initContext.lookup(factoryName);
			Topic topic = (Topic) initContext.lookup(topicName);
			initContext.close();
			//Create JMS objects
//			TopicConnection connection = factory.createTopicConnection();
			TopicConnection connection = factory.createTopicConnection("joao", "pedro");
			TopicSession session =
					connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicPublisher publisher = session.createPublisher(topic);
			//Send messages
			String messageText = null;
			while (true) {
				System.out.println("Enter message to send or 'quit':");
				messageText = reader.readLine();
				if ("quit".equals(messageText))
					break;
				TextMessage message = session.createTextMessage(messageText);
				publisher.publish(message);
			}
			//Exit
			System.out.println("Exiting...");
			reader.close();
			connection.close();
			System.out.println("Goodbye!");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}