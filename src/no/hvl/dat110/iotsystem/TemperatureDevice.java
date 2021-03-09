package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();

		// create a client object
		Client client = new Client("TemperatureDevice", Common.BROKERHOST, Common.BROKERPORT);
		
		// - connect to the broker
		client.connect();
		System.out.println("Temperature device starting ... ");
		
		// - publish the temperature(s)
		for(int i = 0; i < COUNT; i++) {
			int temp = sn.read();
			client.publish(Common.TEMPTOPIC, "" + temp);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// - disconnect from the broker
		client.disconnect();
		System.out.println("Temperature device stopping ... ");
	}
}
