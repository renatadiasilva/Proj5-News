package pt.uc.dei.aor.paj.jmsQ;

import java.io.*;

import javax.jms.*;
import javax.naming.*;
public class QReceiver implements MessageListener {
	private boolean stop = false;

	public static void main(String[] args) {
		new QReceiver().receive();
	}

	public void receive() {
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
			QueueReceiver receiver = session.createReceiver(queue);
			receiver.setMessageListener(this);
			connection.start();
			//Wait for stop
			while (!stop) {
				Thread.sleep(1000);
			}
			//Exit
			System.out.println("Exiting...");
			connection.close();
			System.out.println("Goodbye!");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void onMessage(Message message) {
		try {
			String msgText = ((TextMessage) message).getText();
			System.out.println(msgText);
			if ("stop".equals(msgText))
				stop = true;
		} catch (JMSException e) {
			e.printStackTrace();
			stop = true;
		}
	}

}