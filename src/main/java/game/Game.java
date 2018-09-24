package game;

import geometry.Point;
import interaction.MotionControl;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import object.*;

import java.util.ArrayList;

public class Game {
    private AnchorPane gamePanel;
    private Player hero;
    private Label score;
    private Label lifeCounter;
    private int countPoint;
    private PhysicGame physicGame;
    private ArrayList<VisualBall> visualObject;
    private Factory factory;
    private int prizeCount;
    MotionControl motionControl;

    public Game(AnchorPane _panel) {
        gamePanel = _panel;
        factory = new Factory(gamePanel);
        visualObject = new ArrayList<>();
        physicGame = new PhysicGame();
        score = new Label("Score");
        score.setLayoutX(550);
        score.setLayoutY(50);
        gamePanel.getChildren().add(score);
        countPoint = 0;
        lifeCounter = new Label("Life");
        gamePanel.getChildren().add(lifeCounter);
        lifeCounter.setLayoutX(550);
        lifeCounter.setLayoutY(70);
        prizeCount = 0;
        motionControl =new MotionControl();
    }

    public void addWall(Point start, Point end) {
        physicGame.addWall(start, end);
        gamePanel.getChildren().add(new Line(start.getX(), start.getY(), end.getX(), end.getY()));
    }

    public void addHero(Player hero) {
        this.hero = hero;
        physicGame.addBall(hero);
        visualObject.add(factory.createPlayer(hero));
    }

    private void createPrize() {
        Prize prize = new Prize(Math.random(),
                                Math.random(),
                                Math.random() * 5,
                                Math.random() * 500,
                                Math.random() * 500,
                                15);
        visualObject.add(factory.createPrize(prize));
        physicGame.addBall(prize);
    }

    public void addEnemy(Ball enemy) {
        visualObject.add(factory.createEnemy(enemy));
        physicGame.addBall(enemy);

    }

    public void addPrize(Ball prize) {
        visualObject.add(factory.createPrize(prize));
        physicGame.addBall(prize);
        prizeCount++;
    }

    public void mouseClick(MouseEvent event) {
        motionControl = new MotionControl(event);
    }

    public void update() {
        if (hero.getLifeCount() > -10) {
            physicGame.move(motionControl);
            if (hero.getScore() != countPoint) {
                score.setText("Score " + hero.getScore());
                countPoint = hero.getScore();
                prizeCount--;
            }
            lifeCounter.setText("Life " + hero.getLifeCount());

            for (VisualBall currentObject : visualObject) {
                currentObject.update();
            }
            if (prizeCount == 0) {
                createPrize();
                prizeCount++;
            }
        }

    }

}
