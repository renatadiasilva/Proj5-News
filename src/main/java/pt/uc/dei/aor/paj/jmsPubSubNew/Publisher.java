package pt.uc.dei.aor.paj.jmsPubSubNew;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Publisher {
	private ConnectionFactory cf;
	private Destination d;

	public Publisher() throws NamingException {
		this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.d = InitialContext.doLookup("jms/topic/PlayTopic");
	}

//  ver Exceptions
//	private void publish(String text) throws JMSException {	
	private void publish() throws JMSException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try (JMSContext jcontext = cf.createContext("joao", "pedro");) {
			JMSProducer mp = jcontext.createProducer();

			//Send messages
			String messageText = null;
			while (true) {
				System.out.println("Enter message to send or 'quit':");
				messageText = reader.readLine();
				if ("quit".equals(messageText))
					break;
				mp.send(d, messageText);
			}
			//Exit
			System.out.println("Exiting...");
			reader.close();
			System.out.println("Goodbye!");
			
		} catch (JMSRuntimeException re) {
			re.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws NamingException, JMSException {
		Publisher p = new Publisher();
		p.publish();
		
	}
}