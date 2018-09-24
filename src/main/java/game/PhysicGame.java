package game;

import geometry.Point;
import detection.DetectionVisitor;
import interaction.MotionControl;
import interaction.ObjectInteractionVisitor;
import object.Ball;
import object.GameObject;
import object.Wall;
import object.WallCollection;

import java.util.ArrayList;

public class PhysicGame {
    private ArrayList<Ball> objectList;
    private ArrayList<GameObject> gameObjectList;
    private WallCollection walls;

    public PhysicGame() {
        objectList = new ArrayList<>();
        gameObjectList = new ArrayList<>();
        walls = new WallCollection();
    }

    public void addBall(Ball ball) {
        objectList.add(ball);
        gameObjectList.add(ball);
    }

    public void addWall(Point start, Point end) {
        Wall wall = new Wall(start, end);
        walls.getCollection().add(wall);
        gameObjectList.add(wall);
    }

    private void collision(GameObject gameObject, int number) {
        for (int i = number + 1; i < gameObjectList.size(); i++) {
            GameObject currentObject = gameObjectList.get(i);
            if (gameObject.collisionDetection(currentObject.collisionDetection(new DetectionVisitor())).detect()) {
                gameObject.collisionReaction(currentObject.collisionReaction(new ObjectInteractionVisitor())).collide();
            }
        }
        for(GameObject currentGameObject: gameObjectList){
            currentGameObject.changeVector();
        }
    }

    private void clear() {
        for (int i = 0; i < gameObjectList.size(); i++) {
            if (gameObjectList.get(i).isAlive() == false) {
                gameObjectList.remove(i);
            }
        }
    }

    public void move(MotionControl motionControl) {
        for (int i = 0; i < gameObjectList.size(); i++){
            collision(gameObjectList.get(i),i);
        }
        clear();
        for (GameObject currentObject : objectList) {
            currentObject.move(motionControl);
        }
    }
}
