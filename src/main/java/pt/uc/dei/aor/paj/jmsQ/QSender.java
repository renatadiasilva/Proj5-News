package pt.uc.dei.aor.paj.jmsQ;

import java.io.*;
import javax.jms.*;
import javax.naming.*;
public class QSender {
	public static void main(String[] args) {
		new QSender().send();
	}
	public void send() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			//Prompt for JNDI names
			String factoryName = "jms/RemoteConnectionFactory";
			String queueName = "jms/queue/PlayQueue";
			//Look up administered objects
			InitialContext initContext = new InitialContext();
			QueueConnectionFactory factory =
					(QueueConnectionFactory) initContext.lookup(factoryName);
			Queue queue = (Queue) initContext.lookup(queueName);
			initContext.close();
			//Create JMS objects
			QueueConnection connection = factory.createQueueConnection();
			QueueSession session =
					connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = session.createSender(queue);
			//Send messages
			String messageText = null;
			while (true) {
				System.out.println("Enter message to send or 'quit':");
				messageText = reader.readLine();
				if ("quit".equals(messageText))
					break;
				TextMessage message = session.createTextMessage(messageText);
				sender.send(message);
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