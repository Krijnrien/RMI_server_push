package sample;

import publisher.RemotePublisher;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

class Informer extends UnicastRemoteObject implements Serializable {
	private RemotePublisher remotePublisher;


	Informer(RemotePublisher publisher) throws RemoteException {
		remotePublisher = publisher;
		remotePublisher.registerProperty("fondsen");
		createMockFonds();
		Timer timer = new Timer();
		//Set a timer to regenerate the stocks every minute
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				createMockFonds();
			}
		}, 0, 2000);
	}

	private void createMockFonds() {
		Random rand = new Random();
		try {
			System.out.println("Sending from server!");
			remotePublisher.inform("fondsen", null, new Fonds("Test", rand.nextInt()));
		} catch(RemoteException e) {
			e.printStackTrace();
		}
	}
}

