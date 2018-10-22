package game;

import gameObject.*;
import geometry.AreaMap;
import geometry.MyPoint;
import interaction.DetectionVisitor;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PhysicGame {
    private ArrayList<GameObject> gameObjectList;
    private int objectCounter;
    private int prizeCount;
    private AreaMap areaMap;
    private Timer timer;
    private TimerTask timerTask;

    public PhysicGame() {
        gameObjectList = new ArrayList<>();
        areaMap = new AreaMap();
        prizeCount = 0;
        objectCounter = 0;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                move();
            }
        };
        timer = new Timer(true);

    }

    private void addPlayer(Player player) {
        gameObjectList.add(player);
    }

    private void addBall(Ball ball) {
        gameObjectList.add(ball);
    }

    public void start() {
        timer.scheduleAtFixedRate(timerTask, 0, 1);
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

    private void addWall(MyPoint start, MyPoint end) {
        Wall wall = new Wall(start, end, objectCounter);
        objectCounter++;
        gameObjectList.add(wall);
    }

    public Player createPlayer() {
        Player player = new Player(1, 0, 0.3, Math.random() * 300, Math.random() * 300, 15, objectCounter);
        MyPoint point;
        do {
            point = new MyPoint(Math.random() * 300, Math.random() * 300);
        } while (areaMap.isBelong(point));
        player.setPosition(point);
        objectCounter++;
        addPlayer(player);
        return player;
    }

    private void createPrize() {
        Prize prize;
        do {
            prize = new Prize(1, 0, 0.3, Math.random() * 300, Math.random() * 300, 15, objectCounter);
        } while (areaMap.isBelong(prize.getPosition()));
        prizeCount++;
        objectCounter++;
        gameObjectList.add(prize);
    }

    public void addClosedWall(MyPoint[] points) {
        objectCounter++;
        ClosedWall closedWall = new ClosedWall(points, objectCounter);
        gameObjectList.add(closedWall);
        areaMap.add(closedWall.getPolygon());
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
                if (gameObjectList.get(i).type.equals("Pr"))
                    prizeCount--;
                gameObjectList.remove(i);
            }
        if (prizeCount < 1) {
            createPrize();
        }
    }

    private synchronized void move() {
        for (int i = 0; i < gameObjectList.size(); i++) {
            collision(gameObjectList.get(i), i);
        }
        clear();
        for (GameObject currentObject : gameObjectList) {
            currentObject.move();
        }
    }

    public synchronized ArrayList<GameObject> getObjectList() {
        return gameObjectList;
    }

}
