package aex.server;

import aex.client.Fonds;
import aex.client.IEffectenbeurs;
import aex.client.IFonds;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;

public class Effectenbeurs extends UnicastRemoteObject implements IEffectenbeurs {

    private IFonds[] fondsen;
    private Timer timer;

    public Effectenbeurs() throws RemoteException
    {
        this.fondsen = new IFonds[]
        {
            new Fonds("Philip"), new Fonds("Unilever"), new Fonds("Fontys")
        };
        setTimer();
    }

    @Override
    public IFonds[] getKoersen()
    {
        return this.fondsen;
    }

    private void setTimer()
    {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run()
            {
                for (IFonds fonds : fondsen)
            {
                fonds.changeKoers();
            }
            }
        }, 0, 500);
    }
}
