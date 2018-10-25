package server;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {
    public static void main(final String[] args) throws IOException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:sqlite:src/main/resource/Records.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BridgeClass bridge = new BridgeClass(connection);
        LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        Naming.rebind("rmi://192.168.1.111/key", bridge);

        System.out.println("Server work");
    }
}