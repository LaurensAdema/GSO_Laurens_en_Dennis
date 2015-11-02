package aex.shared;

import aex.client.IFonds;
import java.rmi.RemoteException;

public interface IEffectenbeurs extends IRemotePublisher 
{
    IFonds[] getKoersen() throws RemoteException;
}
