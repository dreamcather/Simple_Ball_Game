package game;

import geometry.Point;
import interaction.CollisionVisitor;
import detection.DetectionVisitor;
import interaction.MotionControl;
import object.Ball;
import object.Wall;
import object.WallCollection;

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

    private void collisionWithWalls(Ball ball) {
        for (int i = 0; i < walls.getCollection().size(); i++) {
            Wall currentWall = (Wall) walls.getCollection().elementAt(i);
            if (currentWall.collisionDetection(ball.collisionDetection(new DetectionVisitor())).detect()) {
                ball.sumPerpendicularVector(currentWall.getLine().getNormal());
            }
        }
        for (Ball currentBall : objectList) {
            currentBall.changeVector();
        }
    }

    private void collisionWithBall(Ball mainBall, int number) {
        for (int i = number + 1; i < objectList.size(); i++) {
            Ball currentBall = objectList.get(i);
            if (mainBall.collisionDetection(currentBall.collisionDetection(new DetectionVisitor())).detect()) {
                mainBall.collisionReaction(currentBall.collisionReaction(new CollisionVisitor())).collide();
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

    public void move(MotionControl motionControl) {
        for (int i = 0; i < objectList.size(); i++) {
            collisionWithBall(objectList.get(i), i);
        }
        for (Ball currentObject : objectList) {
            collisionWithWalls(currentObject);
        }
        clear();
        for (Ball currentObject : objectList) {
            currentObject.move(motionControl);
        }
    }
}
