package client;

import game.ClientGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import server.Bridge;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientGUI extends Application {
    private Client client;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Game");
        stage = primaryStage;
        AnchorPane layout = new AnchorPane();
        Registry reg = LocateRegistry.getRegistry("localhost");
        Bridge bridge = (Bridge) reg.lookup(Bridge.NAME);
        client = new Client(bridge);
        Scene scene = new Scene(layout, 750, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        ClientGame clientGame = new ClientGame(layout, client, this);
        clientGame.start();
        scene.setOnMouseClicked(clientGame);
    }

    @Override
    public void stop() throws Exception {
        client.remove();
        super.stop();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("You lose!");
        alert.setOnHidden(evt -> Platform.exit());

        alert.show();
    }

}
