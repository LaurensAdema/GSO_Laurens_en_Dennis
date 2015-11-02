package server;

import shared.IEffectenbeurs;
import shared.IFonds;
import shared.IRemotePropertyListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Kevin van der Burg & Milton van de Sanden
 */
public class MockEffectenBeurs extends UnicastRemoteObject implements IEffectenbeurs {
    private List<IFonds> koersen;
    private transient Timer timer;
    private transient Random random;
    private transient BasicPublisher publisher;
    
    /**
     * Public constructor
     * @param koersen A list of all the "koersen" 
     * @throws RemoteException 
     */
    public MockEffectenBeurs(List<IFonds> koersen) throws RemoteException {
        this.koersen = koersen;
        publisher = new BasicPublisher(new String[]{"koersen"});
    }
    
    /**
     * Public constructor with no parameters
     * @throws RemoteException
     */
    public MockEffectenBeurs() throws RemoteException {
        koersen = new ArrayList<>();
        publisher = new BasicPublisher(new String[]{"koersen"});
    }
    
    /**
     * 
     * @return list of koersen
     */
    @Override
    public List<IFonds> getKoersen() {        
        return Collections.unmodifiableList(koersen);
    }
    
    /**
     * Start random stock timer. Generates new values every 3 seconds.
     */
    public void start() {
        if(timer == null)
        {
            this.random = new Random();
            this.timer = new Timer();
            
            this.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    koersen.stream().forEach((koers) -> {
                        ((Koers) koers).setKoers(random.nextDouble() + random.nextInt(100));
                    });
                    publisher.inform(this, "koersen", null, getKoersen());
                }      
            }, 0, 3000);
        }
    }
    
    /**
     * Stop random stock timer.
     */
    public void stop() {
        if(timer != null)
            this.timer.cancel();
    }
    
    @Override
    public void addListener(IRemotePropertyListener listener, String property) throws RemoteException {
        publisher.addListener(listener, property);
    }
    
    @Override
    public void removeListener(IRemotePropertyListener listener, String property) throws RemoteException {
        publisher.removeListener(listener, property);
    }
}
