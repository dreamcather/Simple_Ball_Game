package game;

import client.Client;
import game.State;
import game.VisualGame;
import geometry.MyPoint;
import control.MotionControl;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import visual.Camera;

import java.io.IOException;

public class ClientGame {
    private Label score;
    private Label lifeCounter;
    private Client client;
    private MotionControl motionControl;
    private AnimationTimer animationTimer;
    private boolean active;
    private VisualGame visualGame;
    private Camera camera;

    public ClientGame(AnchorPane panel, Client client) throws IOException {
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
        active = true;
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

    public void mouseClick(MouseEvent event) {
        motionControl = new MotionControl(event, camera);
        client.sendMotion(motionControl);

    }

    public void start() {
        animationTimer.start();
        active = true;
    }

    public void stop() {
        animationTimer.stop();
        active = false;
    }

    private void update() {
        State state = client.getObjectList();
        visualGame.update(state.gameObjects);
        camera.setPosition(state.player.getPosition());
        score.setText("Score " + state.player.getScore());
        lifeCounter.setText("Life " + state.player.getLifeCount());


    }
}