import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerStub extends Remote{
	public void subscribe(IClientStub stub) throws RemoteException;
}
