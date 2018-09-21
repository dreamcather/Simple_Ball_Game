package game;

import geometry.Point;
import interaction.CollisionVisitor;
import object.Ball;

import java.util.ArrayList;

public class PhysicGame {
    private ArrayList<Ball> objectList;
    private WallCollection walls;

    public PhysicGame() {
        objectList = new ArrayList<>();
        walls = new WallCollection();
    }

    public void addBall(Ball ball) {
        objectList.add(ball);
    }

    public void addWall(Point start, Point end) {
        walls.getCollection().add(new Wall(start, end));
    }

    private double distanceBall(Point first, Point second) {
        return Math.sqrt(Math.pow(first.getX() - second.getX(), 2) + Math.pow(first.getY() - second.getY(), 2));
    }

    private void collisionWithWalls(Ball ball) {
        for (int i = 0; i < walls.getCollection().size(); i++) {
            Wall currentWall = (Wall) walls.getCollection().elementAt(i);
            if (currentWall.isCollisionWithBallAndNormalize(ball)) {
                ball.sumPerpendicularVector(currentWall.getLine().getNormal());
            }
        }
        for (Ball currentBall : objectList) {
            currentBall.refreshVisualModel();
        }
    }

    private void collisionWithBall(Ball mainBall, int number) {
        for (int i = number + 1; i < objectList.size(); i++) {
            Ball currentBall = objectList.get(i);
            if (distanceBall(mainBall.getPosition(), currentBall.getPosition()) < mainBall.getRadius()
                    + currentBall.getRadius()) {
                mainBall.accept(currentBall.accept(new CollisionVisitor())).collide();
            }
        }
    }

    private void clear() {
        for (int i = 0; i < objectList.size(); i++) {
            if (objectList.get(i).isAlive() == false) {
                objectList.remove(i);
            }
        }
    }

    public void move() {
        for (int i = 0; i < objectList.size(); i++) {
            collisionWithBall(objectList.get(i), i);
        }
        for (Ball currentObject : objectList) {
            collisionWithWalls(currentObject);
        }
        clear();
        for (Ball currentObject : objectList) {
            currentObject.move();
        }
    }
}
