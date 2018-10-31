package client;

import game.State;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
}
