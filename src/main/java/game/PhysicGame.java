package game;

import gameObject.Player;
import geometry.Point;
import detection.DetectionVisitor;
import interaction.MotionControl;
import interaction.ObjectVisitor;
import javafx.animation.AnimationTimer;
import gameObject.Ball;
import gameObject.GameObject;
import gameObject.Wall;
import visual.Camera;
import visual.VisualInformation;
import visual.VisualVisitor;

import java.util.ArrayList;

public class PhysicGame {
    private ArrayList<GameObject> gameObjectList;
    Player player;
    MotionControl motionControl;
    private AnimationTimer animationTimer;

    public PhysicGame() {
        gameObjectList = new ArrayList<>();
        motionControl = new MotionControl();
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                move(motionControl);
            }
        };
        animationTimer.start();
    }

    public void addPlayer(Player player) {
        this.player = player;
        gameObjectList.add(player);
    }

    public void addBall(Ball ball) {
        gameObjectList.add(ball);
    }

    public void addWall(Point start, Point end) {
        Wall wall = new Wall(start, end);
        gameObjectList.add(wall);
    }

    private void collision(GameObject gameObject, int number) {
        for (int i = number + 1; i < gameObjectList.size(); i++) {
            GameObject currentObject = gameObjectList.get(i);
            if (gameObject.collision(currentObject.collision(new DetectionVisitor())).detect()) {
                gameObject.collision(currentObject.collision(new ObjectVisitor())).collide();
            }
        }
        for (GameObject currentGameObject : gameObjectList) {
            currentGameObject.changeVector();
        }
    }

    public void stop() {
        animationTimer.stop();
    }

    private void clear() {
        for (int i = 0; i < gameObjectList.size(); i++) {
            if (gameObjectList.get(i).isAlive() == false) {
                gameObjectList.remove(i);
            }
        }
    }

    public void move(MotionControl motionControl) {
        for (int i = 0; i < gameObjectList.size(); i++) {
            collision(gameObjectList.get(i), i);
        }
        clear();
        for (GameObject currentObject : gameObjectList) {
            currentObject.move(motionControl);
        }
    }

    public void setMotionControl(MotionControl motionControl) {
        this.motionControl = motionControl;
    }

    public ArrayList<VisualInformation> getObjectList(Camera camera) {
        camera.setPosition(player.getPosition());
        ArrayList<VisualInformation> output = new ArrayList<>();
        for (GameObject gameObject : gameObjectList) {
            VisualInformation visualInformation = gameObject.collision(new VisualVisitor()).isVisible(camera);
            if (visualInformation != null)
                output.add(visualInformation);
        }
        return output;
    }
}
