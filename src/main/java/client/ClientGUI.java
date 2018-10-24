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
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Optional;

public class ClientGUI extends Application {
    private Client client;
    private Bridge bridge;
    private ClientGame clientGame;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game");
        AnchorPane layout = new AnchorPane();
        try {
            bridge = (Bridge) Naming.lookup("rmi://192.168.1.111/key");
            client = new Client(bridge);
            Scene scene = new Scene(layout, 750, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
            try {
                clientGame = new ClientGame(layout, client, this);
            } catch (IOException e) {
                disconnect();
            }
            clientGame.start();
            scene.setOnMouseClicked(clientGame);
        } catch (NotBoundException e) {
            disconnect();
        } catch (MalformedURLException e) {
            disconnect();
        } catch (RemoteException e) {
            disconnect();
        }
    }

    private void disconnect(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Can't connect");
        alert.setContentText("Ooops, there was an error!");

        alert.showAndWait();
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
        super.stop();
        if(client!=null)
        client.remove();
    }

}
