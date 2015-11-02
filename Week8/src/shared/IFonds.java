package shared;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Kevin van der Burg & Milton van de Sanden
 */
public interface IFonds extends Remote, Serializable {
    
    /**
     * @return String
     * @throws RemoteException
     */
    public String getName() throws RemoteException;
    
    /**
     * @return double
     * @throws RemoteException
     */
    public double getKoers() throws RemoteException;
}
