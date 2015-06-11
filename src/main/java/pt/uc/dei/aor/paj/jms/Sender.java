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

public class Sender {
	private ConnectionFactory cf;
	private Destination d;

	public Sender() throws NamingException {
		this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.d = InitialContext.doLookup("jms/queue/PlayQueue");
	}

	private void send(String text) throws JMSException {
		try (JMSContext jcontext = cf.createContext("joao", "pedro");) {
			JMSProducer mp = jcontext.createProducer();
			Destination replyTo = jcontext.createTemporaryQueue();
			TextMessage msg = jcontext.createTextMessage(text);
			msg.setJMSReplyTo(replyTo);
			mp.send(d, msg);
			
			JMSConsumer mc = jcontext.createConsumer(replyTo);
			TextMessage reply = (TextMessage) mc.receive();
			System.out.println("Sender got back: " + reply.getText());
			
		} catch (JMSRuntimeException re) {
			re.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws NamingException, JMSException {
		Sender s = new Sender();
		s.send("Outra mensagem!!");
		
	}
}