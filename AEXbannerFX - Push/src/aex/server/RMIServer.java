/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Gebruiker
 */
public class RMIServer {

    // References to registry and student administration
    private Registry registry = null;
    private Effectenbeurs effectenbeurs = null;

    public RMIServer()
    {
        try
        {
            effectenbeurs = new Effectenbeurs();
        } catch (RemoteException ex)
        {
            effectenbeurs = null;
            System.out.println(ex.getMessage());
        }

        // Create registry at port number
        try
        {
            System.out.println(aex.client.Application.port);
            registry = LocateRegistry.createRegistry(aex.client.Application.port);
        } catch (RemoteException ex)
        {
            registry = null;
            System.out.println(ex.getMessage());
        }

        // Bind effectenbeurs using registry
        try
        {
            registry.rebind(aex.client.Application.bindName, effectenbeurs);
        } catch (RemoteException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        RMIServer server = new RMIServer();
        System.out.println("Server gestart");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run()
            {
                System.out.println("Still running");
            }
        }, 5000);
        Scanner s = new Scanner(System.in);
            System.out.println("Type stop to stop.");
            
            while(!s.nextLine().equals("stop"))
            {
                System.out.println("Type stop to stop.");
            }
    }
}
