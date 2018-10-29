package client;

import game.State;
import game.VisualGame;
import geometry.MyPoint;
import control.MotionControl;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import visual.Camera;

import java.io.IOException;

public class ClientGame implements EventHandler<MouseEvent> {
    private final ClientGUI application;
    private final Label score;
    private final Label lifeCounter;
    private Client client;
    private MotionControl motionControl;
    private AnimationTimer animationTimer;
    private VisualGame visualGame;
    private Camera camera;

    public ClientGame(AnchorPane panel, Client client, ClientGUI application) throws IOException {
        this.application = application;
        this.client = client;
        score = new Label("Score");
        score.setLayoutX(650);
        score.setLayoutY(50);
        panel.getChildren().add(score);
        lifeCounter = new Label("Life");
        panel.getChildren().add(lifeCounter);
        lifeCounter.setLayoutX(650);
        lifeCounter.setLayoutY(70);
        motionControl = new MotionControl();
        int width = 500;
        visualGame = new VisualGame(panel, new MyPoint(50, 50), width, 1000, 1000);
        camera = visualGame.getCamera();
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };

    }

    private void disconnect() {
        stop();
        application.disconnect();
    }

    private void gameOver() {
        try {
            stop();
            application.gameOver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        animationTimer.start();
    }

    private void stop() {
        animationTimer.stop();
    }

    private void update() {
        try {
            State state = client.getObjectList();
            visualGame.update(state.getGameObjects());
            camera.setPosition(state.getPlayer().getPosition());
            score.setText("Score " + state.getPlayer().getScore());
            lifeCounter.setText("Life " + state.getPlayer().getLifeCount());
            if (state.getPlayer().getLifeCount() <= 0) {
                gameOver();
            }
        } catch (Exception e) {
            disconnect();
        }

    }

    public void setClient(Client client) {
        this.client = client;
        start();
    }

    public void handle(KeyEvent event){
        motionControl = new MotionControl(event);
        client.sendMotion(motionControl);

    }


    @Override
    public void handle(MouseEvent event) {
        motionControl = new MotionControl(event, camera);
        client.sendMotion(motionControl);
    }

}