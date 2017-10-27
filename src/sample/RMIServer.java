package sample;

import publisher.RemotePublisher;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
	private static final int portNr = 8080;

	public static void main(String[] args) throws RemoteException {
		System.out.println("Server started");
		RMIServer rmiServer = new RMIServer();
	}

	public RMIServer() throws RemoteException {
		RemotePublisher publisher = new RemotePublisher();
		Informer effectenBeurs = new Informer(publisher);
		Registry registry = LocateRegistry.createRegistry(portNr);
		registry.rebind("Effectenbeurs", effectenBeurs);
		registry.rebind("publisher", publisher);
	}
}

