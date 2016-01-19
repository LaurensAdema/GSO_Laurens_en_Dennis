package fontys.server;

import java.rmi.RemoteException;

public interface IEffectenbeurs extends IRemotePublisher {

    IFonds[] getKoersen() throws RemoteException;
}
