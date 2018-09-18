package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class GameState {
    AnchorPane gamePanel;
    Hero hero;
    Label score;
    Label gameTime;
    int countPoint;
    double time;
    ArrayList<VisualBall> objectList;
    WallColection walls;

    GameState(AnchorPane _panel) {
        gamePanel = _panel;
        objectList = new ArrayList<>();
        score = new Label("Score");
        score.setLayoutX(550);
        score.setLayoutY(50);
        gamePanel.getChildren().add(score);
        walls = new WallColection(8);
        countPoint = 0;
        gameTime = new Label("Time");
        gamePanel.getChildren().add(gameTime);
        gameTime.setLayoutX(550);
        gameTime.setLayoutY(70);
        time = System.currentTimeMillis();
        walls.collection[0] = new Wall(new Point(0, 500), new Point(0, 0));
        walls.collection[1] = new Wall(new Point(500, 0), new Point(500, 500));
        walls.collection[2] = new Wall(new Point(500, 0), new Point(0, 0));
        walls.collection[3] = new Wall(new Point(500, 500), new Point(0, 500));
        walls.collection[4] = new Wall(new Point(250, 200), new Point(200, 250));
        walls.collection[5] = new Wall(new Point(200, 250), new Point(250, 300));
        walls.collection[6] = new Wall(new Point(250, 300), new Point(300, 250));
        walls.collection[7] = new Wall(new Point(300, 250), new Point(250, 200));
    }

    public void setHero(Hero hero) {
        this.hero = hero;
        objectList.add(hero);
    }

    public void addEnemy(Enemy enemy) {
        objectList.add(enemy);
    }


    public void addPrize(Prize prize) {
        objectList.add(prize);
    }

    private void collisionWithWalls(Ball ball) {
        for (int i = 0; i < walls.collection.length; i++) {
            Wall curentWall = walls.collection[i];
            if (((curentWall.calculateDistanceToPoint(ball.getFuturePosition()) < ball.getRadius()))
                    && curentWall.isBetween(ball.getFuturePosition())) {

                Point collisionPoint = curentWall.getLine().intersectionLine(ball.getPosition(), ball.getVector());

                Line perpendicularLine = new Line(collisionPoint, curentWall.getLine().getNormal());

                Point middlePoint = perpendicularLine.getProectionPoint(ball.getPosition());

                Vector paralelVector = new Vector(ball.getPosition(), middlePoint);

                Point endPoint = paralelVector.addition(middlePoint);

                ball.changeVector(new Vector(collisionPoint, endPoint));

            }

        }
    }

    private double distanceBall(Point first, Point second) {
        return Math.sqrt(Math.pow(first.getX() - second.getX(), 2) + Math.pow(first.getY() - second.getY(), 2));
    }

    private void clear() {
        for (int i = 0; i < objectList.size(); i++) {
            if (objectList.get(i).isAlive() == false) {
                objectList.get(i).model.hide(gamePanel);
                ;
                objectList.remove(i);
            }
        }
    }

    private void collisionWithBall(VisualBall mainBall, int number) {
        for (int i = number + 1; i < objectList.size(); i++) {
            VisualBall currentBall = objectList.get(i);
            if (distanceBall(mainBall.ball.getFuturePosition(), currentBall.ball.getFuturePosition()) < mainBall.ball.getRadius()
                    + currentBall.ball.getRadius()) {
                mainBall.accept(currentBall.accept(new CollisionVisitor())).collide();
            }
        }
    }

    public void move() {
        for (VisualBall curentObject : objectList) {
            collisionWithWalls(curentObject.ball);
        }

        for (int i = 0; i < objectList.size(); i++) {
            collisionWithBall(objectList.get(i), i);
        }
        clear();
        for (VisualBall curentObject : objectList) {
            curentObject.move();
        }
        if (hero.getScore() != countPoint) {
            score.setText("Score " + hero.getScore());
            countPoint = hero.getScore();
        }
        gameTime.setText("Time " + (System.currentTimeMillis() - time)/1000);

    }

}
