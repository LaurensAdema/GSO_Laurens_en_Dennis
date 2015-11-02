package aex.server;

import aex.client.Fonds;
import aex.shared.IEffectenbeurs;
import aex.client.IFonds;
import aex.shared.IRemotePropertyListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;

public class Effectenbeurs extends UnicastRemoteObject implements IEffectenbeurs {

    private IFonds[] fondsen;
    private Timer timer;
    private BasicPublisher publisher;

    public Effectenbeurs() throws RemoteException
    {
        this.fondsen = new IFonds[]
        {
            new Fonds("Philip"), new Fonds("Unilever"), new Fonds("Fontys")
        };
        setTimer();
        publisher = new BasicPublisher(new String[]
        {
            "koersen"
        });
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
                IFonds[] fondsenOld = fondsen.clone();
                for (IFonds fonds : fondsen)
                {
                    fonds.changeKoers();
                }
                if (publisher == null)
                {
                    //System.out.println("publisher is null");
                } else
                {
                    //System.out.println("publisher updated");
                    publisher.inform(this, "koersen", fondsenOld, getKoersen());
                }
            }
        }, 0, 500);
    }

    @Override
    public void addListener(IRemotePropertyListener listener, String property) throws RemoteException
    {
        publisher.addListener(listener, property);
    }

    @Override
    public void removeListener(IRemotePropertyListener listener, String property) throws RemoteException
    {
        publisher.removeListener(listener, property);
    }
}
