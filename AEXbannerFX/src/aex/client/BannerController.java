/**
 * ***** BannerController.java **********************************
 */
package aex.client;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

public class BannerController {

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Timer pollingTimer;

    public BannerController(AEXBanner banner)
    {
        this.banner = banner;
        this.effectenbeurs = new MockEffectenbeurs();

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        // TODO
        pollingTimer.schedule(new TimerTask() {

            @Override
            public void run()
            {
                Platform.runLater(new Runnable() {public void run() {banner.setKoersen(BannerString());}});
            }
        }, 0, 2000);
    }

    public String BannerString()
    {
        StringBuilder koersen = new StringBuilder();

        for (IFonds fonds : effectenbeurs.getKoersen())
        {
            koersen.append(fonds.getNaam());
            koersen.append(" ");
            koersen.append(fonds.getKoers());
            koersen.append(" ");
        }

        if (koersen.length() < 1)
        {
            koersen.append("Er zijn geen koersen beschikbaar.");
        }

        return koersen.toString().trim();
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
