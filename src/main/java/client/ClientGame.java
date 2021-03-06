package client;

import java.io.IOException;
import java.io.Serializable;

import control.MotionControl;
import game.State;
import game.VisualGame;
import geometry.MyPoint;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import visual.Camera;

public class ClientGame implements EventHandler<MouseEvent>, Serializable {
    private final ClientGUI application;
    private final Label score;
    private final Label lifeCounter;
    private Client client;
    private MotionControl motionControl;
    private VisualGame visualGame;
    private Camera camera;
    private final TextArea textArea;

    public ClientGame(AnchorPane panel, Client client, ClientGUI application) throws IOException {
        this.application = application;
        this.client = client;
        score = new Label("Score");
        score.setLayoutX(650);
        score.setLayoutY(50);
        textArea = new TextArea();
        textArea.setLayoutX(550);
        textArea.setLayoutY(250);
        textArea.setEditable(false);
        textArea.setDisable(true);
        panel.getChildren().addAll(score, textArea);
        lifeCounter = new Label("Life");
        panel.getChildren().add(lifeCounter);
        lifeCounter.setLayoutX(650);
        lifeCounter.setLayoutY(70);
        motionControl = new MotionControl();
        int width = 500;
        visualGame = new VisualGame(panel, new MyPoint(50, 50), width, 1000, 1000);
        camera = visualGame.getCamera();

    }

    private void disconnect() {
        application.disconnect();
    }

    protected void gameOver() {
        Platform.runLater(() -> {
        try {
            client.remove();
            application.gameOver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        });
    }

    protected void update(State state) {
        try {
            Platform.runLater(() -> {
                visualGame.update(state.getGameObjects());
                camera.setPosition(state.getPlayer().getPosition());
                score.setText("Score " + state.getPlayer().getScore());
                lifeCounter.setText("Life " + state.getPlayer().getLifeCount());
            });
        } catch (Exception e) {
            disconnect();
        }

    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void handle(KeyEvent event) {
        motionControl = new MotionControl(event);
        client.sendMotion(motionControl);

    }

    public void printMessage(String string) {
        textArea.setText(textArea.getText() + string + '\n');
    }

    @Override
    public void handle(MouseEvent event) {
        motionControl = new MotionControl(event, camera);
        client.sendMotion(motionControl);
    }

}