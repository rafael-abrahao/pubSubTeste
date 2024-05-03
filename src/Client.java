import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client {

	public static void main(String[] args) {
		String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            IServerStub serverStub = (IServerStub) registry.lookup("Server");
            ClientStub client = new ClientStub();
            IClientStub clientStub = (IClientStub) UnicastRemoteObject.exportObject(client, 0);

            serverStub.subscribe(clientStub);
            
            System.err.println("Client ready");
            
            while(true) {
            	if(clientStub.getUpdate() != null) {
            		System.out.println("Atualização: " + clientStub.getUpdate());
            		clientStub.clearUpdate();
            	}
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

	}

}
