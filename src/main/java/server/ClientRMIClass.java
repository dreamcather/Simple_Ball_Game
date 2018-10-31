package server;

import java.rmi.RemoteException;

public class ClientRMIClass implements ClientRMIInterface {
    @Override
    public void sendMessage(String string) throws RemoteException {
        System.out.println(string);

    }
}
