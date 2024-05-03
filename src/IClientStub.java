import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClientStub extends Remote {
	public void setUpdate(String update) throws RemoteException;
	public String getUpdate() throws RemoteException;
	public void clearUpdate() throws RemoteException;
}
