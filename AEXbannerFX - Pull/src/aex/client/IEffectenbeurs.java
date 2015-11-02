package aex.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEffectenbeurs extends Remote 
{
    IFonds[] getKoersen() throws RemoteException;
}
