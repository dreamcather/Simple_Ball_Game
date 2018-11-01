package client;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import game.State;

public class ClientRMIClass extends UnicastRemoteObject implements ClientRMIInterface, Serializable {
    private final Client client;

    public ClientRMIClass(Client client) throws RemoteException {
        this.client = client;
    }

    @Override
    public void sendMessage(String string) {
        client.sendMessage(string);
    }

    @Override
    public void update(State state) throws RemoteException {
        client.update(state);
    }

    @Override
    public void gameOver() throws RemoteException {
        client.gameOver();
    }
}
