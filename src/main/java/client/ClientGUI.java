package client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import server.Bridge;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Optional;

public class ClientGUI extends Application {
    private Client client;
    private Bridge bridge;
    private ClientGame clientGame;
    private AnchorPane layout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Game");
        AnchorPane layout = new AnchorPane();
        Registry reg = LocateRegistry.getRegistry("localhost");
        bridge = (Bridge) reg.lookup(Bridge.NAME);
        client = new Client(bridge);
        Scene scene = new Scene(layout, 750, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        clientGame = new ClientGame(layout, client, this);
        clientGame.start();
        scene.setOnMouseClicked(clientGame);
    }

    public void gameOver() {
        client.remove();
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            ButtonType buttonTypeOne = new ButtonType("New game");
            ButtonType buttonTypeTwo = new ButtonType("Exit");
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("You lose!");
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get().getText() == "Exit") {
                Platform.exit();
            } else if (result.get().getText() == "New game") {
                client = new Client(bridge);
                clientGame.setClient(client);
            }
        });
    }

    @Override
    public void stop() throws Exception {
    }

}
