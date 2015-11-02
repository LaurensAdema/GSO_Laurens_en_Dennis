/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import shared.IEffectenbeurs;
import shared.IFonds;
import shared.IRemotePropertyListener;
import server.RMIServer;
import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin van der Burg & Milton van de Sanden
 */

public class BannerController extends UnicastRemoteObject implements IRemotePropertyListener, Serializable {

    private transient AEXBanner banner;
    private transient IEffectenbeurs beurs;
    private Registry client;
    private DecimalFormat decimalFormat = new DecimalFormat("#00.00");
    
    /**
     * Public constructor
     * @param banner AEXBanner inherits application.
     * @throws RemoteException
     */
    public BannerController(AEXBanner banner) throws RemoteException {
        try {
            this.banner = banner;
            this.client = LocateRegistry.getRegistry(RMIServer.port);
            
            ((IEffectenbeurs) client.lookup("beurs")).addListener(this, "koersen");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * stops both timers in banner and effectenbeurs
     */
    public void stop() {
        try {
            ((IEffectenbeurs) client.lookup("beurs")).removeListener(this, "koersen");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Update the display text
     * @param koersen A list of koersen to be updated
     */
    private void updateString(List<IFonds> koersen) {
        try {
            StringBuilder b = new StringBuilder();
            
            for(IFonds koers : koersen){
                String newKoers = decimalFormat.format(koers.getKoers());
                b.append(String.format("%s %s - ", koers.getName(), newKoers));  
            }
                
            banner.setKoersen(b.toString() + b.toString());
        } catch (RemoteException ex) {
            Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        updateString((List<IFonds>) evt.getNewValue());
    }    
}
