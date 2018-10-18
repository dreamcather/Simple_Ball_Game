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
            physicGame.addWall(bufferedReader.readLine());
        }
        bufferedReader.readLine();
        physicGame.addPlayer(bufferedReader.readLine());
        string = bufferedReader.readLine();
        strmas = string.split(" ", 2);
        int enemyCount = Integer.parseInt(strmas[1]);
        for (int i = 0; i < enemyCount; i++) {
            physicGame.addEnemy(bufferedReader.readLine());

        }
        string = bufferedReader.readLine();
        strmas = string.split(" ", 2);
        int prizeCount = Integer.parseInt(strmas[1]);
        for (int i = 0; i < prizeCount; i++) {
            physicGame.addPrize(bufferedReader.readLine());

        }
    }

}