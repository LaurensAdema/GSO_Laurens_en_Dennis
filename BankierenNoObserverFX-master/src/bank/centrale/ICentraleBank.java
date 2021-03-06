package bank.centrale;

import bank.bankieren.IMutateBank;
import bank.bankieren.Money;
import fontys.util.NumberDoesntExistException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICentraleBank extends Remote {
    int getUniqueRekNr(String bankName) throws RemoteException;

    void registerBank(String bankName, IMutateBank bank) throws RemoteException;

    boolean maakOver(int source, int destination, Money amount) throws RemoteException, NumberDoesntExistException;
}
