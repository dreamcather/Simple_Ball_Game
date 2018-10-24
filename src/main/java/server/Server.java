package server;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(final String[] args) throws IOException, AlreadyBoundException {
        BridgeClass bridge = new BridgeClass();
        Registry localReg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        Naming.rebind("rmi://192.168.1.111/abc",bridge);
        //localReg.bind(bridge.NAME, bridge);
        System.out.println("Server work");
    }
}