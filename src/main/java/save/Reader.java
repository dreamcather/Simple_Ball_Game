package save;

import game.PhysicGame;
import gameObject.Enemy;
import gameObject.Player;
import gameObject.Prize;
import geometry.MyPoint;

import java.io.*;

public class Reader {
    PhysicGame physicGame;

    public Reader(String path, PhysicGame game) throws IOException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        physicGame = game;
        String string = bufferedReader.readLine();
        String[] strmas = string.split(" ", 2);
        int wallCount = Integer.parseInt(strmas[1]);
        for (int i = 0; i < wallCount; i++) {
            addWall(bufferedReader.readLine());
        }
        bufferedReader.readLine();
        addHero(bufferedReader.readLine());
        string = bufferedReader.readLine();
        strmas = string.split(" ", 2);
        int enemyCount = Integer.parseInt(strmas[1]);
        for (int i = 0; i < enemyCount; i++) {
            addEnemy(bufferedReader.readLine());

        }
        string = bufferedReader.readLine();
        strmas = string.split(" ", 2);
        int prizeCount = Integer.parseInt(strmas[1]);
        for (int i = 0; i < prizeCount; i++) {
            addPrize(bufferedReader.readLine());

        }
    }

    private void addWall(String string) {
        String[] substr = string.split(" ");
        MyPoint start = new MyPoint(Double.parseDouble(substr[0]), Double.parseDouble(substr[1]));
        MyPoint end = new MyPoint(Double.parseDouble(substr[2]), Double.parseDouble(substr[3]));
        physicGame.addWall(start, end);
    }

    public void addHero(String string) {
        String[] strMas = string.split(" ", 6);
        double xCoefficient = Double.parseDouble(strMas[0]);
        double yCoefficient = Double.parseDouble(strMas[1]);
        double speed = Double.parseDouble(strMas[2]);
        double xCoordinate = Double.parseDouble(strMas[3]);
        double yCoordinate = Double.parseDouble(strMas[4]);
        double radius = Double.parseDouble(strMas[5]);
        Player player = new Player(xCoefficient, yCoefficient, speed, xCoordinate, yCoordinate, radius);
        physicGame.addPlayer(player);
    }

    public void addEnemy(String string) {
        String[] strmas = string.split(" ", 6);
        double xCoefficient = Double.parseDouble(strmas[0]);
        double yCoefficient = Double.parseDouble(strmas[1]);
        double speed = Double.parseDouble(strmas[2]);
        double xCoordinate = Double.parseDouble(strmas[3]);
        double yCoordinate = Double.parseDouble(strmas[4]);
        double radius = Double.parseDouble(strmas[5]);
        physicGame.addBall(new Enemy(xCoefficient, yCoefficient, speed, xCoordinate, yCoordinate, radius));

    }

    public void addPrize(String string) {
        String[] strmas = string.split(" ", 6);
        double xCoefficient = Double.parseDouble(strmas[0]);
        double yCoefficient = Double.parseDouble(strmas[1]);
        double speed = Double.parseDouble(strmas[2]);
        double xCoordinate = Double.parseDouble(strmas[3]);
        double yCoordinate = Double.parseDouble(strmas[4]);
        double radius = Double.parseDouble(strmas[5]);
        physicGame.addBall(new Prize(xCoefficient, yCoefficient, speed, xCoordinate, yCoordinate, radius));
        physicGame.incrementPrizeCount();
    }

}