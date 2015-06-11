package pt.uc.dei.aor.paj.jms;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Receiver {
	private ConnectionFactory cf;
	private Destination d;

	public Receiver() throws NamingException {
		this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.d = InitialContext.doLookup("jms/queue/PlayQueue");
	}

	private void receive() throws JMSException {
		try (JMSContext jcontex = cf.createContext("joao", "pedro");) {
			JMSConsumer mc = jcontex.createConsumer(d);
			TextMessage msg = (TextMessage) mc.receive();
			System.out.println("Message: " + msg.getText());
			Destination replyTo = msg.getJMSReplyTo();
			TextMessage reply = jcontex.createTextMessage(msg.getText() + " to you too my friend!");
			JMSProducer mp = jcontex.createProducer();
			mp.send(replyTo, reply);
		} catch (JMSRuntimeException re) {
			re.printStackTrace();
		}
	}

	public static void main(String[] args) throws NamingException, JMSException {
		Receiver r = new Receiver();

		r.receive();
	}

}