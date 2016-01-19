package bank.centrale;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CentraleBankServer {

    public static void main(String[] arg)
    {
        try
        {
            CentraleBank centraleBank = new CentraleBank();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("centraleBank", centraleBank);
            System.out.println("De centrale bank staat aan.");
        } catch (RemoteException ex)
        {
            Logger.getLogger(CentraleBankServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
