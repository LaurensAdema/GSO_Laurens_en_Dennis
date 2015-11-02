package aex.client;

import aex.shared.IEffectenbeurs;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class MockEffectenbeurs implements IEffectenbeurs {

    private final String[] COMPANIES_STRINGS =
    {
        "Shell", "Honig"
    };
    private final double rangeMin = 0.01;
    private final double rangeMax = 99.99;
    private final List<IFonds> koersen = new ArrayList();
    Timer timer = new Timer();

    public MockEffectenbeurs()
    {
        startTimer();
    }

    private void startTimer()
    {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            {
                koersen.clear();
                for (String company : COMPANIES_STRINGS)
                {
                    IFonds fonds = new Fonds(company);
                    koersen.add(fonds);
                }
            }
        }, 0, 3000);
    }

    @Override
    public IFonds[] getKoersen() throws RemoteException
    {
        return (IFonds[]) koersen.toArray();
    }
}
