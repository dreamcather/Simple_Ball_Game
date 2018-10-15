package game;

import gameObject.*;
import geometry.MyPoint;
import interaction.DetectionVisitor;
import control.MotionControl;
import javafx.animation.AnimationTimer;
import visual.Camera;
import visual.visualInformation.VisualInformation;

import java.util.ArrayList;

public class PhysicGame {
    private ArrayList<GameObject> gameObjectList;
    Player player;
    MotionControl motionControl;

    public PhysicGame() {
        gameObjectList = new ArrayList<>();
        motionControl = new MotionControl();
        ClosedWall closedWall = new ClosedWall(new MyPoint[] {
                new MyPoint(600, 600),
                new MyPoint(650, 800),
                new MyPoint(700, 700),
                new MyPoint(600, 800) });
        addClosedWall(closedWall);
        AnimationTimer animationTimer = new AnimationTimer() {
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

    public void addWall(MyPoint start, MyPoint end) {
        Wall wall = new Wall(start, end);
        gameObjectList.add(wall);
    }

    public void addClosedWall(ClosedWall closedWall) {
        gameObjectList.add(closedWall);
    }

    private void collision(GameObject gameObject, int number) {
        for (int i = number + 1; i < gameObjectList.size(); i++) {
            GameObject currentObject = gameObjectList.get(i);
            if (gameObject.collision(currentObject.collision(new DetectionVisitor())).detect()) {
                gameObject.collision(currentObject.collision(new DetectionVisitor())).collision();
            }
        }
        for (GameObject currentGameObject : gameObjectList)
            currentGameObject.changeVector();
    }

    // не будет ли проскакивания?
    private void clear() {
        for (int i = 0; i < gameObjectList.size(); i++)
            if (gameObjectList.get(i).isAlive() == false) {
                gameObjectList.remove(i);
            }
    }

    private void move(MotionControl motionControl) {
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
            VisualInformation visualInformation = gameObject.isVisible(camera);
            if (visualInformation != null)
                output.add(visualInformation);
        }
        return output;
    }
}
