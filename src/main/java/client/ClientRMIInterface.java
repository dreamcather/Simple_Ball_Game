package client;

import game.State;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientRMIInterface extends Remote {

    void sendMessage(String string) throws RemoteException;

    void update(State state) throws RemoteException;
}
