import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server implements IServerStub{
	
	private List<IClientStub> clients;
	
	public Server() {
		this.clients = new ArrayList<IClientStub>();
	}
	
	@Override
	public void subscribe(IClientStub stub) throws RemoteException {
		this.clients.add(stub);
	}
	
	private void update(String update) {
		for(IClientStub client:this.clients) {
			try {
				client.setUpdate(update);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
	        Server server = new Server();
	        IServerStub stub = (IServerStub) UnicastRemoteObject.exportObject(server, 0);

	        Registry registry = LocateRegistry.getRegistry();
	        registry.bind("Server", stub);

	        System.err.println("Server ready");
	        
	        try (Scanner sc = new Scanner(System.in)) {
				while(true) {
					System.out.print("Digite a atualização da temperatura: ");
					String temp = sc.next();
					server.update(temp);
				}
			}
	        
		}catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
		}

	}

}
