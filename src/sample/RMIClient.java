package sample;

import publisher.IRemotePropertyListener;
import publisher.IRemotePublisherForListener;

import java.beans.PropertyChangeEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIClient extends UnicastRemoteObject implements IRemotePropertyListener {
	private String koersString;

	public static void main(String[] args) throws IOException {
		System.out.println("client started");
		RMIClient RMIClient = new RMIClient();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		in.readLine();
	}

	private RMIClient() throws RemoteException {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 8080);
			IRemotePublisherForListener publisher = (IRemotePublisherForListener) registry.lookup("publisher");
			publisher.subscribeRemoteListener(this, "fondsen");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void getKoersString(Fonds property) {
		System.out.println(property.getName() + property.getX());
	}

	private void setKoersString() {
		koersString = "";
	}

	public void propertyChange(PropertyChangeEvent propertyChangeEvent) throws RemoteException {
		setKoersString();
		getKoersString((Fonds) propertyChangeEvent.getNewValue());
	}
}
