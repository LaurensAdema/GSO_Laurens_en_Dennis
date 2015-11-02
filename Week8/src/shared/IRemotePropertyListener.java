package shared;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.EventListener;

/**
 *
 * @author Kevin van der Burg & Milton van de Sanden
 */
public interface IRemotePropertyListener extends EventListener, Remote {
    
    /**
     * Notify all listeners subscribed to a property about a change
     * @param evt property change details
     * @throws RemoteException 
     */
    void propertyChange(PropertyChangeEvent evt) throws RemoteException;
}
