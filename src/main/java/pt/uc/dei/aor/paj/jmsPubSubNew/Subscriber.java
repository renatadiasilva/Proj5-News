package pt.uc.dei.aor.paj.jmsPubSubNew;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Subscriber implements MessageListener {
	private ConnectionFactory cf;
	private Destination d;

	private boolean stop = false;

	public Subscriber() throws NamingException {
		this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.d = InitialContext.doLookup("jms/topic/PlayTopic");
	}

	private void subscribe() throws JMSException {
		try (JMSContext jcontex = cf.createContext("joao", "pedro");) {
			JMSConsumer mc = jcontex.createConsumer(d);
			mc.setMessageListener(this);
			//Wait for stop
			while (!stop) {
				Thread.sleep(1000);
			}
			//Exit
			System.out.println("Exiting...");
			System.out.println("Goodbye!");
		} catch (JMSRuntimeException re) {
			re.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws NamingException, JMSException {
		Subscriber r = new Subscriber();
		r.subscribe();
	}

	@Override
	public void onMessage(Message message) {
		try {
			String msgText = ((TextMessage) message).getText();
			System.out.println("Message: " + msgText);
			if ("stop".equals(msgText))
				stop = true;
		} catch (JMSException e) {
			e.printStackTrace();
			stop = true;
		}
	}

}