/**
 * ***** BannerController.java **********************************
 */
package aex.client;

import aex.shared.IFonds;
import aex.server.Effectenbeurs;
import aex.shared.IEffectenbeurs;
import aex.shared.IRemotePropertyListener;
import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author Dennis
 */
public class BannerController extends UnicastRemoteObject implements IRemotePropertyListener, Serializable {

    private final AEXBanner banner;
    //private IEffectenbeurs effectenbeurs;
    private Registry client;
    private BannerController self = this; //absolutely neccesary, I am not proud of this
    //private final Timer pollingTimer;
    private boolean connected = false;

    public BannerController(AEXBanner banner) throws RemoteException
    {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    client = LocateRegistry.getRegistry(Application.port);
                    // dit moet wel de ergste hack zijn die ik ooit heb geschreven
                    ((IEffectenbeurs) client.lookup(aex.client.Application.bindName)).removeListener(self, "koersen");
                } catch (RemoteException | NotBoundException ex)
                {
                    Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, "Shutdown-thread"));
        this.banner = banner;
        //this.effectenbeurs = new Effectenbeurs();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run()
            {
                if (!connected)
                {
                    System.out.println("loop 1 works");
                    try
                    {
                        client = LocateRegistry.getRegistry(Application.port);
                        // dit moet wel de ergste hack zijn die ik ooit heb geschreven
                        ((IEffectenbeurs) client.lookup(aex.client.Application.bindName)).addListener(self, "koersen");
                        this.cancel();
                        connected = true;
                        System.out.println("Connected");
                    } catch (RemoteException | NotBoundException ex)
                    {
                        //Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
                        //System.out.println(ex.getMessage());
                        //System.out.println("not connected");
                    } catch (Exception ex)
                    {
                        //Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
                        //System.out.println(ex.getMessage());
                        //System.out.println("not connected");
                    }
                }
            }
        }, 0, 500);
        //langer durende timer voor als er gedisconnect wordt
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run()
            {
                if (connected)
                {
                    System.out.println("loop 2 works");
                    try
                    {
                        client = LocateRegistry.getRegistry(Application.port);
                        // dit moet wel de ergste hack zijn die ik ooit heb geschreven
                        ((IEffectenbeurs) client.lookup(aex.client.Application.bindName)).addListener(self, "koersen");
                        //System.out.println("Connected");
                    } catch (RemoteException | NotBoundException ex)
                    {
                        //Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
                        //System.out.println(ex.getMessage());
                        //System.out.println("not connected");
                    }
                }
            }
        }, 0, 5000);
//
//        // Start polling timer: update banner every two seconds
//        pollingTimer = new Timer();
//        // TODO
//        pollingTimer.schedule(new TimerTask() {
//
//            @Override
//            public void run()
//            {
//                Platform.runLater(new Runnable() {
//                    private Registry registry = null;
//
//                    @Override
//                    public void run()
//                    {
//                        //
//
//                        try
//                        {
//                            registry = LocateRegistry.getRegistry(Application.ip, Application.port);
//                        } catch (RemoteException ex)
//                        {
//                            System.out.println(ex.getMessage());
//                        }
//
//                        if (registry != null)
//                        {
//                            try
//                            {
//                                effectenbeurs = (IEffectenbeurs) registry.lookup(Application.bindName);
//                            } catch (RemoteException | NotBoundException ex)
//                            {
//                                effectenbeurs = null;
//                                System.out.println(ex.getMessage());
//                            }
//                        }
//                        banner.setKoersen(BannerString());
//                    }
//                });
//            }
//        }, 0, 2000);
    }

    public String BannerString(IFonds[] koersen)
    {
        StringBuilder bannerString = new StringBuilder();
        if (koersen != null)
        {
            for (IFonds fonds : koersen)
            {
                bannerString.append(fonds.getNaam());
                bannerString.append(": ");
                bannerString.append(String.format("%.2f", fonds.getKoers()));
                bannerString.append(" ");
            }
            if (bannerString.length() < 1)
            {
                bannerString.append("Er zijn geen koersen beschikbaar.");
            }
            return bannerString.toString().trim();
        }
        return "Er zijn geen koersen beschikbaar.";
    }

//    // Stop banner controller
//    public void stop()
//    {
//        pollingTimer.cancel();
//        // Stop simulation timer of effectenbeurs
//        // TODO
//        banner.stop();
//    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException
    {
        banner.setKoersen(BannerString((IFonds[]) evt.getNewValue()));
    }
}
