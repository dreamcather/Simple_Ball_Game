package server;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;

public class Server {
    public static void main(final String[] args) throws IOException, AlreadyBoundException {
        BridgeClass bridge = new BridgeClass();
        Naming.rebind("rmi://192.168.1.111/key",bridge);
        System.out.println("Server work");
    }
}