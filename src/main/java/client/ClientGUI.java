package client;

import game.ClientGame;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.Bridge;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientGUI extends Application {
    private Stage window;
    private Scene scene;
    private AnchorPane layout;
    private Client client;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Game");
        layout = new AnchorPane();
        Registry reg = LocateRegistry.getRegistry("localhost");
        Bridge bridge = (Bridge) reg.lookup(Bridge.NAME);
        client = new Client(bridge);
        scene = new Scene(layout, 750, 600);
        window.setScene(scene);
        window.show();
        ClientGame clientGame = new ClientGame(layout,client);
        clientGame.start();
    }
}
