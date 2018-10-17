package game;

import client.Client;
import geometry.MyPoint;
import control.MotionControl;
import javafx.animation.AnimationTimer;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import gameObject.*;
import save.Reader;
import visual.Camera;

import java.io.IOException;

public class ClientGame {
    private Player hero;
    private Label score;
    private Label lifeCounter;
    private int countPoint;
    private Client client;
    private int prizeCount;
    private MotionControl motionControl;
    private AnimationTimer animationTimer;
    private boolean active;
    private VisualGame visualGame;
    private Camera camera;

    public ClientGame(AnchorPane panel, Client client) throws IOException {
        this.client = client;
        hero = client.getPlayer();
        score = new Label("Score");
        score.setLayoutX(650);
        score.setLayoutY(50);
        panel.getChildren().add(score);
        countPoint = 0;
        lifeCounter = new Label("Life");
        panel.getChildren().add(lifeCounter);
        lifeCounter.setLayoutX(650);
        lifeCounter.setLayoutY(70);
        prizeCount = 0;
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

    private void createPrize() {
        Prize prize = new Prize(Math.random(),
                Math.random(),
                Math.random() * 5,
                Math.random() * 500,
                Math.random() * 500,
                15);

    }

    public void mouseClick(MouseEvent event) {
        motionControl = new MotionControl(event, camera);

    }

    public void click(KeyCode keyCode) {
        if (keyCode == KeyCode.Q)
            camera.setPosition(new MyPoint(camera.getPosition().getX() + 10, camera.getPosition().getY() + 10));
        if (keyCode == KeyCode.A)
            camera.setPosition(new MyPoint(camera.getPosition().getX() - 10, camera.getPosition().getY() - 10));
        if ((keyCode == KeyCode.UP) || (keyCode == KeyCode.DOWN) || (keyCode == KeyCode.LEFT)
                || (keyCode == KeyCode.RIGHT)) {

        }
    }

    public void start() {
        animationTimer.start();
        active = true;
    }

    public void stop() {
        animationTimer.stop();
        active = false;
    }

    private void newGame() {
        hero.setLifeCount(5);
    }

    private void gameOver() {
        stop();
    }

    private void update() {
        if (hero.getLifeCount() <= 0) {
            gameOver();
        } else {

            if (hero.getScore() != countPoint) {
                score.setText("Score " + hero.getScore());
                countPoint = hero.getScore();
                prizeCount--;
            }
            lifeCounter.setText("Life " + hero.getLifeCount());

            if (prizeCount == 0) {
                createPrize();
                prizeCount++;
            }
        }
        visualGame.update(client.getObjectList());
        camera.setPosition(client.getPlayer().getPosition());

    }

    public boolean isActive() {
        return active;
    }

    public void exit(){
        stop();
    }

}