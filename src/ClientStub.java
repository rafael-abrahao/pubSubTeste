import java.rmi.RemoteException;

public class ClientStub implements IClientStub {
	
	private String update = null;
	
	@Override
	public void setUpdate(String update) throws RemoteException {
		this.update = update;
	}
	
	@Override
	public String getUpdate() throws RemoteException{
		return update;
	}
	
	@Override
	public void clearUpdate() throws RemoteException{
		this.update = null;
	}
}
