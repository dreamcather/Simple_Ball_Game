package game;

import geometry.MyPoint;
import control.MotionControl;
import javafx.animation.AnimationTimer;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import gameObject.*;
import visual.Camera;

import java.io.IOException;

public class Game {
    private AnchorPane gamePanel;
    private Player hero;
    private Label score;
    private Label lifeCounter;
    private int countPoint;
    private PhysicGame physicGame;
    private int prizeCount;
    MotionControl motionControl;
    AnimationTimer animationTimer;
    boolean active;
    VisualGame visualGame;
    int width = 500;
    Camera camera;

    public Game(AnchorPane _panel) throws IOException {
        gamePanel = _panel;
        physicGame = new PhysicGame();
        score = new Label("Score");
        score.setLayoutX(650);
        score.setLayoutY(50);
        gamePanel.getChildren().add(score);
        countPoint = 0;
        lifeCounter = new Label("Life");
        gamePanel.getChildren().add(lifeCounter);
        lifeCounter.setLayoutX(650);
        lifeCounter.setLayoutY(70);
        prizeCount = 0;
        motionControl = new MotionControl();
        active = true;
        visualGame = new VisualGame(gamePanel, new MyPoint(50, 50), width, 1000, 1000);
        camera = visualGame.getCamera();
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };

    }

    public void addWall(String string) {
        String[] substr = string.split("  ");
        MyPoint start = new MyPoint(Double.parseDouble(substr[0]), Double.parseDouble(substr[1]));
        MyPoint end = new MyPoint(Double.parseDouble(substr[2]), Double.parseDouble(substr[3]));
        physicGame.addWall(start, end);
    }

    public void addClosedWall(ClosedWall closedWall)
    {
    }

    public void addHero(String string) {
        String[] strMas = string.split("  ", 6);
        double xCoefficient = Double.parseDouble(strMas[0]);
        double yCoefficient = Double.parseDouble(strMas[1]);
        double speed = Double.parseDouble(strMas[2]);
        double xCoordinate = Double.parseDouble(strMas[3]);
        double yCoordinate = Double.parseDouble(strMas[4]);
        double radius = Double.parseDouble(strMas[5]);
        Player player = new Player(xCoefficient, yCoefficient, speed, xCoordinate, yCoordinate, radius);
        this.hero = player;
        physicGame.addPlayer(player);
    }

    private void createPrize() {
        Prize prize = new Prize(Math.random(),
                Math.random(),
                Math.random() * 5,
                Math.random() * 500,
                Math.random() * 500,
                15);
        physicGame.addBall(prize);
    }

    public void addEnemy(String string) {
        String[] strmas = string.split("  ", 6);
        double xCoefficient = Double.parseDouble(strmas[0]);
        double yCoefficient = Double.parseDouble(strmas[1]);
        double speed = Double.parseDouble(strmas[2]);
        double xCoordinate = Double.parseDouble(strmas[3]);
        double yCoordinate = Double.parseDouble(strmas[4]);
        double radius = Double.parseDouble(strmas[5]);
        physicGame.addBall(new Enemy(xCoefficient, yCoefficient, speed, xCoordinate, yCoordinate, radius));

    }

    public void addPrize(String string) {
        String[] strmas = string.split("  ", 6);
        double xCoefficient = Double.parseDouble(strmas[0]);
        double yCoefficient = Double.parseDouble(strmas[1]);
        double speed = Double.parseDouble(strmas[2]);
        double xCoordinate = Double.parseDouble(strmas[3]);
        double yCoordinate = Double.parseDouble(strmas[4]);
        double radius = Double.parseDouble(strmas[5]);
        physicGame.addBall(new Prize(xCoefficient, yCoefficient, speed, xCoordinate, yCoordinate, radius));
        prizeCount++;
    }

    public void mouseClick(MouseEvent event) {
        motionControl = new MotionControl(event, camera);
        physicGame.setMotionControl(motionControl);
    }

    public void click(KeyCode keyCode) {
        if (keyCode == KeyCode.Q)
            camera.setPosition(new MyPoint(camera.getPosition().getX() + 10, camera.getPosition().getY() + 10));
        if (keyCode == KeyCode.A)
            camera.setPosition(new MyPoint(camera.getPosition().getX() - 10, camera.getPosition().getY() - 10));
        if ((keyCode == KeyCode.UP) || (keyCode == KeyCode.DOWN) || (keyCode == keyCode.LEFT) || (keyCode == keyCode.RIGHT)) {
            physicGame.player.setKey(keyCode);
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

    public void newGame() {
        hero.setLifeCount(5);
    }

    public void gameOver() {
        newGame();
    }

    public void update() {
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
                //createPrize();
                prizeCount++;
            }
        }
        visualGame.update(physicGame.getObjectList(camera));
    }

    public boolean isActive() {
        return active;
    }

}
