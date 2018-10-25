package client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
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
    private Stage stage;
    private AnchorPane anchorPane;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game");
        stage =primaryStage;
        AnchorPane layout = new AnchorPane();
        anchorPane =layout;
        try {
            bridge = (Bridge) Naming.lookup("rmi://192.168.1.111/key");
            client = new Client(bridge);
            try {
                clientGame = new ClientGame(layout, client, this);
            } catch (IOException e) {
                disconnect();
            }
            clientGame.start();
            Scene scene = new Scene(layout, 750, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.setOnMouseClicked(clientGame);
        } catch (NotBoundException | RemoteException | MalformedURLException e) {
            disconnect();
        }
    }

    protected void disconnect() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Can't connect");
            alert.setContentText("Ooops, there was an error!");
            alert.setX(stage.getX()+stage.getWidth()/2-250);
            alert.setY(stage.getY()+stage.getHeight()/2-200);
            System.out.println(alert.widthProperty());
            alert.setOnHidden(event -> Platform.exit());

            alert.show();
        });
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
            if (result.get().getText().equals("Exit")) {
                TextInputDialog dialog = new TextInputDialog("Tran");

                dialog.setTitle("Record");
                dialog.setHeaderText("Enter your name:");
                dialog.setContentText("Name:");

                Optional<String> result1 = dialog.showAndWait();

                result1.ifPresent(name -> {
                    client.sendRecord(name);
                });
                Platform.exit();
            } else if (result.get().getText().equals("New game")) {
                client = new Client(bridge);
                clientGame.setClient(client);
            }
        });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (client != null)
            client.remove();
    }

}
