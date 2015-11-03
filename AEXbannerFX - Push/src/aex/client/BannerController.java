/**
 * ***** BannerController.java **********************************
 */
package aex.client;

import aex.shared.IFonds;
import aex.shared.IEffectenbeurs;
import aex.shared.IRemotePropertyListener;
import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dennis
 */
public class BannerController extends UnicastRemoteObject implements IRemotePropertyListener, Serializable {

    private final AEXBanner banner;
    private Registry client;
    private BannerController self = this; //absolutely neccesary, I am not proud of this
    private boolean connected = false;

    public BannerController(AEXBanner banner) throws RemoteException
    {
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
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
        }, "Shutdown-thread"));
        this.banner = banner;

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run()
            {
                if (!connected)
                {
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
                    try
                    {
                        client = LocateRegistry.getRegistry(Application.port);
                        // dit moet wel de ergste hack zijn die ik ooit heb geschreven
                        ((IEffectenbeurs) client.lookup(aex.client.Application.bindName)).addListener(self, "koersen");
                    } catch (RemoteException | NotBoundException ex)
                    {
                    }
                }
            }
        }, 0, 5000);
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException
    {
        banner.setKoersen(BannerString((IFonds[]) evt.getNewValue()));
    }
}
