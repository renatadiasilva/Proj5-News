package pt.uc.dei.aor.paj.jmsPubSubNew;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Subscriber2 implements MessageListener {
	private ConnectionFactory cf;
//	private Destination d;
	private Topic t;

	private boolean stop = false;

	public Subscriber2() throws NamingException {
		this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.t = InitialContext.doLookup("jms/topic/PlayTopic");
	}

	private void subscribe() throws JMSException {
		try (JMSContext jcontext = cf.createContext("joao", "pedro");) {
			jcontext.setClientID("user2");
			JMSConsumer mc = jcontext.createDurableConsumer(t, "user2", "(Content = 'Stats')", true);
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
		Subscriber2 r = new Subscriber2();
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