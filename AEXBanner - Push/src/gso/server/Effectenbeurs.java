/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.server;

import gso.Task.KoersenPusher;
import gso.Task.KoersenUpdater;
import gso.shared.Fonds;
import gso.shared.IEffectenbeurs;
import gso.shared.IFonds;
import gso.shared.IListener;
import gso.shared.IPublisher;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;


/**
 *
 * @author Bart
 */
public class Effectenbeurs extends UnicastRemoteObject implements IEffectenbeurs, IPublisher
{
    private IFonds[] fondsen;
    private Timer timer;
    private Publisher publisher;
    

    public Effectenbeurs() throws RemoteException
    {
        this.fondsen = new IFonds[] {new Fonds("Philips"), new Fonds("Unilever"), new Fonds("Fontys")};
        setTimer();
        this.publisher = new Publisher(new KoersenPusher(this));
    }
    
    @Override
    public IFonds[] getKoersen()
    {
        return this.fondsen;
    }
    
    private void setTimer()
    {
        timer = new Timer();
        timer.scheduleAtFixedRate(new KoersenUpdater(this.fondsen), 0, 500);
    }

    @Override
    public void meldAan(IListener listener) throws RemoteException {
        publisher.meldAan(listener);
    }

}
