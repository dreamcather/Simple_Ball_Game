package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class GameState {
    AnchorPane gamePanel;
    Hero hero;
    Label score;
    Label gameTime;
    int countPoint;
    double time;
    PhysicGame phisicGame;
    ArrayList<VisualBall> visualObject;
    Factory factory;

    GameState(AnchorPane _panel) {
        gamePanel = _panel;
        factory = new Factory(gamePanel);
        visualObject = new ArrayList<>();
        phisicGame = new PhysicGame();
        score = new Label("Score");
        score.setLayoutX(550);
        score.setLayoutY(50);
        gamePanel.getChildren().add(score);
        countPoint = 0;
        gameTime = new Label("Time");
        gamePanel.getChildren().add(gameTime);
        gameTime.setLayoutX(550);
        gameTime.setLayoutY(70);
        time = System.currentTimeMillis();
    }

    public void addWall(Point start,Point end){
        phisicGame.addWall(start,end);
        gamePanel.getChildren().add(new Line(start.getX(), start.getY(), end.getX(), end.getY()));
    }

    public void setHero(Hero hero) {
        this.hero = hero;
        phisicGame.addBall(hero);
        visualObject.add(factory.createPlayer(hero));
    }

    public void addEnemy(Ball enemy) {
        visualObject.add(factory.createEnemy(enemy));
        phisicGame.addBall(enemy);

    }

    public void addPrize(Ball prize) {
        visualObject.add(factory.createPrize(prize));
        phisicGame.addBall(prize);
    }

    public void update() {
        phisicGame.move();
        if (hero.getScore() != countPoint) {
            score.setText("Score " + hero.getScore());
            countPoint = hero.getScore();
        }
        gameTime.setText("Time " + (System.currentTimeMillis() - time)/1000);

        for(VisualBall curentObject: visualObject) {
            curentObject.update();
        }

    }

}
