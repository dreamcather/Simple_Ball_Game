package client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import server.Bridge;

public class ClientGUI extends Application {
    private Client client;
    private Bridge bridge;
    private ClientGame clientGame;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game");
        stage = primaryStage;
        AnchorPane layout = new AnchorPane();
        try {
            bridge = (Bridge) Naming.lookup("rmi://192.168.1.111/key");
            client = new Client(bridge);
            try {
                clientGame = new ClientGame(layout, client, this);
                client.setControl(clientGame);
                client.sendMe();
            } catch (IOException e) {
                disconnect();
            }
            Scene scene = new Scene(layout, 750, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.setOnMouseClicked(clientGame);
            scene.setOnKeyPressed(event -> clientGame.handle(event));
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
            alert.setX(stage.getX() + stage.getWidth() / 2 - 250);
            alert.setY(stage.getY() + stage.getHeight() / 2 - 200);
            System.out.println(alert.widthProperty());
            alert.setOnHidden(event -> Platform.exit());

            alert.show();
        });
    }

    private void createNewGame() {
        try {
            client = new Client(bridge);
            clientGame.setClient(client);
            client.setControl(clientGame);
            client.sendMe();
        } catch (Exception e) {
            disconnect();
        }
    }

    private void showRecordsDialog(Alert alert) {
        TextInputDialog dialog = new TextInputDialog("Tran");

        dialog.setTitle("Record");
        dialog.setHeaderText("Enter your name:");
        dialog.setContentText("Name:");

        Optional<String> result1 = dialog.showAndWait();

        if (result1.isPresent()) {
            String playerName = result1.get();
            result1.ifPresent(name -> client.sendRecord(name));
            Alert recordInfo = new Alert(Alert.AlertType.INFORMATION);
            recordInfo.setTitle("Record's Table");
            ArrayList<Pair<String, Integer>> records = client.get10MaxRecords();
            StringBuilder recordsTable = new StringBuilder();
            for (int i = 0; i < records.size(); i++) {
                recordsTable.append(i+1)
                            .append(": ")
                            .append(records.get(i).getKey())
                            .append("  ")
                            .append(records.get(i).getValue())
                            .append("\n");
            }
            recordsTable.append("\n\n You: ").append(playerName).append("  ").append(client.getPlayer().getScore());
            recordInfo.setHeaderText("Our winners");
            recordInfo.setContentText(recordsTable.toString());

            recordInfo.showAndWait();

            Platform.exit();
        } else {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get().getText().equals("Exit")) {
                showRecordsDialog(alert);

            } else if (result.get().getText().equals("New game")) {
                createNewGame();
            }
        }

    }

    public void gameOver() {
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
                showRecordsDialog(alert);

            } else if (result.get().getText().equals("New game")) {
                createNewGame();
            }
        });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (client != null) {

            client.remove();
            client.exit();
        }
    }

}
