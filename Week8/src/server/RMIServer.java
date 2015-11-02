package server;

import shared.IEffectenbeurs;
import shared.IFonds;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin van der Burg & Milton van de Sanden
 */
public class RMIServer {
    public final static int port = 1099;
    Registry registry;
    IEffectenbeurs beurs;
    
    /**
     *
     * Starts the Server and set the default "Fonds"
     */
    public RMIServer() {
        try {
            registry = LocateRegistry.createRegistry(RMIServer.port);
            
            List<IFonds> fonds = new ArrayList<>();
            fonds.add(new Koers("Kevin Corp", 0.00));
            fonds.add(new Koers("Miltonius Empire", 0.00));
            fonds.add(new Koers("Hill Valley Inc.", 0.00));
            fonds.add(new Koers("The Game", 0.00));
            
            beurs = new MockEffectenBeurs(fonds);
            
            registry.rebind("beurs", beurs);
            
        } catch (RemoteException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *  Starts the MockEffectenBeurs
     * 
     */
    public void start() {
        System.out.println("[MockEffectenBeurs] starting...");
        ((MockEffectenBeurs) beurs).start();
    }
    
    /**
     *  Stops the MockEffectenBeurs
     * @throws NoSuchObjectException
     * 
     */
    public void stop() throws NoSuchObjectException {
        ((MockEffectenBeurs) beurs).stop();
        UnicastRemoteObject.unexportObject(registry, true);
        System.out.println("[MockEffectenBeurs] stopping...");
        System.exit(1);
    }
    
    /**
     * Starts the server and check if you type Stop. If you you type stop it stops the server.
     * @param args
     */
    public static void main(String[] args) {
        RMIServer server = null;
        try {
            server = new RMIServer();
            server.start();
            Scanner s = new Scanner(System.in);
            System.out.println("Type stop to stop.");
            
            while(!s.nextLine().equals("stop"))
            {
                System.out.println("Type stop to stop.");
            }
            
            server.stop();
        } catch (RemoteException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
