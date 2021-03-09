package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.common.TODO;

public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main (String[] args) {
		
		System.out.println("Display starting ...");
		

				
		// create a client object
		Client client = new Client("DisplayDevice", Common.BROKERHOST, Common.BROKERPORT);
		
		// - connect to the broker
		client.connect();
		
		// - create the temperature topic on the broker
		client.createTopic(Common.TEMPTOPIC);
		
		// - subscribe to the topic
		client.subscribe(Common.TEMPTOPIC);
		
		// - receive messages on the topic
		for(int i = 0; i < COUNT; i++) {
			PublishMsg msg = (PublishMsg) client.receive();
			System.out.println("DISPLAY: " + msg.getMessage());
		}
		
		// - unsubscribe from the topic
		client.unsubscribe(Common.TEMPTOPIC);
		
		// - disconnect from the broker
		client.disconnect();
		System.out.println("Display stopping ... ");
				
	}
}
