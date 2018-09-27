package game;

import gameObject.Player;
import geometry.Point;
import detection.DetectionVisitor;
import interaction.MotionControl;
import interaction.ObjectInteractionVisitor;
import javafx.animation.AnimationTimer;
import gameObject.Ball;
import gameObject.GameObject;
import gameObject.Wall;

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
    public void addPlayer(Player player){
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
            if (gameObject.collisionDetection(currentObject.collisionDetection(new DetectionVisitor())).detect()) {
                gameObject.collisionReaction(currentObject.collisionReaction(new ObjectInteractionVisitor())).collide();
            }
        }
        for(GameObject currentGameObject: gameObjectList){
            currentGameObject.changeVector();
        }
    }

    public void stop(){
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
        for (int i = 0; i < gameObjectList.size(); i++){
            collision(gameObjectList.get(i),i);
        }
        clear();
        for (GameObject currentObject : gameObjectList) {
            currentObject.move(motionControl);
        }
    }

    public void setMotionControl(MotionControl motionControl) {
        this.motionControl = motionControl;
    }

    public ArrayList<GameObject> getObjectList(){
        ArrayList<GameObject> output = new ArrayList<>();
        for(GameObject gameObject:gameObjectList){
                output.add(gameObject);
        }
        return output;
    }

    public  Point getPlayerPosition(){
        return player.getPosition();
    }
}
