/**
 * ***** BannerController.java **********************************
 */
package aex.client;

import aex.shared.IFonds;
import aex.shared.IEffectenbeurs;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

public class BannerController {

    private final AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private final Timer pollingTimer;

    public BannerController(AEXBanner banner)
    {
        this.banner = banner;

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        // TODO
        pollingTimer.schedule(new TimerTask() {

            @Override
            public void run()
            {
                Platform.runLater(new Runnable() {
                    private Registry registry = null;

                    @Override
                    public void run()
                    {
                        //

                        try
                        {
                            registry = LocateRegistry.getRegistry(Application.ip, Application.port);
                        } catch (RemoteException ex)
                        {
                            System.out.println(ex.getMessage());
                        }

                        if (registry != null)
                        {
                            try
                            {
                                effectenbeurs = (IEffectenbeurs) registry.lookup(Application.bindName);
                            } catch (RemoteException | NotBoundException ex)
                            {
                                effectenbeurs = null;
                                System.out.println(ex.getMessage());
                            }
                        }
                        banner.setKoersen(BannerString());
                    }
                });
            }
        }, 0, 2000);
    }

    public String BannerString()
    {
        StringBuilder koersen = new StringBuilder();
        if (effectenbeurs != null)
        {
            try {
                for (IFonds fonds : effectenbeurs.getKoersen())
                {
                    koersen.append(fonds.getNaam());
                    koersen.append(": ");
                    koersen.append(String.format("%.2f", fonds.getKoers()));
                    koersen.append(" ");
                }
                
                if (koersen.length() < 1)
                {
                    koersen.append("Er zijn geen koersen beschikbaar.");
                }
                
                return koersen.toString().trim();
            } catch (RemoteException ex) {
                Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "Er zijn geen koersen beschikbaar.";
    }

    // Stop banner controller
    public void stop()
    {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
        banner.stop();
    }
}
