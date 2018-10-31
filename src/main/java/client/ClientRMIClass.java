package client;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientRMIClass extends UnicastRemoteObject implements ClientRMIInterface, Serializable {
    private Client client;

    public ClientRMIClass(Client client) throws RemoteException, MalformedURLException, AlreadyBoundException {
        this.client = client;
    }

    @Override
    public void sendMessage(String string) throws RemoteException {
        client.sendMessage(string);
    }
}
