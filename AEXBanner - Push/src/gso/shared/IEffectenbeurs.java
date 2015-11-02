/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.shared;

import gso.client.Listener;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Bart
 */
public interface IEffectenbeurs extends Remote 
{
    IFonds[] getKoersen() throws RemoteException;
    
    void meldAan(IListener listener) throws RemoteException;
}

