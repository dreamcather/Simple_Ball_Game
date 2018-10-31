package server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server extends Application {
    private  Connection connection;
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:sqlite:src/main/resource/Records.db";
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BridgeClass bridge = new BridgeClass(connection);
        LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        Naming.rebind("rmi://192.168.1.111/key", bridge);

        primaryStage.setTitle("Game");
        Stage stage = primaryStage;
        BorderPane layout = new BorderPane();
        Scene scene = new Scene(layout, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
        Button closeServer = new Button("Close server");
        layout.setCenter(closeServer);
        closeServer.setOnAction(event -> Platform.exit());

    }

    @Override
    public void stop() throws Exception {
        connection.close();
        Naming.unbind("rmi://192.168.1.111/key");
        super.stop();
        System.exit(0);
    }
}