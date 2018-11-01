package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import game.State;

public interface ClientRMIInterface extends Remote {

    void sendMessage(String string) throws RemoteException;

    void update(State state) throws RemoteException;

    void gameOver() throws RemoteException;
}
