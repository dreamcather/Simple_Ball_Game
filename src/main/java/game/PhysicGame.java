package game;

import gameObject.*;
import geometry.MyPoint;
import interaction.DetectionVisitor;
import control.MotionControl;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PhysicGame {
    private ArrayList<GameObject> gameObjectList;
    private ArrayList<GameObject> currentState;
    private Player player;
    private MotionControl motionControl;
    private int objectCounter;
    private int prizeCount;
    private Timer timer;

    public PhysicGame() {
        gameObjectList = new ArrayList<>();
        currentState = (ArrayList<GameObject>) gameObjectList.clone();
        motionControl = new MotionControl();
        prizeCount = 0;
        objectCounter = 0;
        ClosedWall closedWall = new ClosedWall(new MyPoint[]{new MyPoint(600, 600), new MyPoint(650, 800), new MyPoint(700, 700), new MyPoint(600, 800)}, objectCounter);
        addClosedWall(closedWall);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                move(motionControl);
            }
        };
        timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 1);
    }

    public Player addPlayer(String string) {
        String[] strMas = string.split(" ", 6);
        double xCoefficient = Double.parseDouble(strMas[0]);
        double yCoefficient = Double.parseDouble(strMas[1]);
        double speed = Double.parseDouble(strMas[2]);
        double xCoordinate = Double.parseDouble(strMas[3]);
        double yCoordinate = Double.parseDouble(strMas[4]);
        double radius = Double.parseDouble(strMas[5]);
        Player player = new Player(xCoefficient, yCoefficient, speed, xCoordinate, yCoordinate, radius, objectCounter);
        objectCounter++;
        addPlayer(player);
        return player;
    }

    public void addPlayer(Player player) {
        gameObjectList.add(player);
    }

    public void addBall(Ball ball) {
        gameObjectList.add(ball);
    }

    public void addEnemy(String string) {
        String[] strmas = string.split(" ", 6);
        double xCoefficient = Double.parseDouble(strmas[0]);
        double yCoefficient = Double.parseDouble(strmas[1]);
        double speed = Double.parseDouble(strmas[2]);
        double xCoordinate = Double.parseDouble(strmas[3]);
        double yCoordinate = Double.parseDouble(strmas[4]);
        double radius = Double.parseDouble(strmas[5]);
        addBall(new Enemy(xCoefficient, yCoefficient, speed, xCoordinate, yCoordinate, radius, objectCounter));
        objectCounter++;
    }

    public void addPrize(String string) {
        String[] strmas = string.split(" ", 6);
        double xCoefficient = Double.parseDouble(strmas[0]);
        double yCoefficient = Double.parseDouble(strmas[1]);
        double speed = Double.parseDouble(strmas[2]);
        double xCoordinate = Double.parseDouble(strmas[3]);
        double yCoordinate = Double.parseDouble(strmas[4]);
        double radius = Double.parseDouble(strmas[5]);
        addBall(new Prize(xCoefficient, yCoefficient, speed, xCoordinate, yCoordinate, radius, objectCounter));
        objectCounter++;
    }

    public void addWall(String string) {
        String[] substr = string.split(" ");
        MyPoint start = new MyPoint(Double.parseDouble(substr[0]), Double.parseDouble(substr[1]));
        MyPoint end = new MyPoint(Double.parseDouble(substr[2]), Double.parseDouble(substr[3]));
        addWall(start, end);
    }

    public void addWall(MyPoint start, MyPoint end) {
        Wall wall = new Wall(start, end, objectCounter);
        objectCounter++;
        gameObjectList.add(wall);
    }

    public void addClosedWall(ClosedWall closedWall) {
        objectCounter++;
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

    private void clear() {
        for (int i = 0; i < gameObjectList.size(); i++)
            if (!gameObjectList.get(i).isAlive()) {
                gameObjectList.remove(i);
            }
    }

    private synchronized void move(MotionControl motionControl) {
        for (int i = 0; i < gameObjectList.size(); i++) {
            collision(gameObjectList.get(i), i);
        }
        clear();
        for (GameObject currentObject : gameObjectList) {
            currentObject.move(motionControl);
        }
        currentState = new ArrayList<>();
        for (GameObject gameObject : gameObjectList) {
            currentState.add(gameObject);
        }
    }

    public void setMotionControl(MotionControl motionControl) {
        this.motionControl = motionControl;
    }

    public synchronized ArrayList<GameObject> getObjectList() {
        return gameObjectList;
    }

    public Player getPlayer() {
        return player;
    }

    public void incrementPrizeCount() {
        prizeCount++;
    }

    public void exit() {
    }
}
