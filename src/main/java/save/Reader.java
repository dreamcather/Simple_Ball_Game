package save;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import game.PhysicGame;
import geometry.MyPoint;

public class Reader {

    public Reader(String path, PhysicGame game) throws IOException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string = bufferedReader.readLine();
        String[] strmas = string.split(" ", 2);
        int wallCount = Integer.parseInt(strmas[1]);
        for (int i = 0; i < wallCount; i++) {
            game.addWall(bufferedReader.readLine());
        }
        string = bufferedReader.readLine();
        strmas = string.split(" ", 2);
        int enemyCount = Integer.parseInt(strmas[1]);
        for (int i = 0; i < enemyCount; i++) {
            game.addEnemy(bufferedReader.readLine());

        }
        string = bufferedReader.readLine();
        strmas = string.split(" ", 2);
        int prizeCount = Integer.parseInt(strmas[1]);
        for (int i = 0; i < prizeCount; i++) {
            game.addPrize(bufferedReader.readLine());
        }
        string = bufferedReader.readLine();
        strmas = string.split(" ", 2);
        int closedWallCount = Integer.parseInt(strmas[1]);
        for (int i = 0; i < closedWallCount; i++) {
            string = bufferedReader.readLine();
            int pointCount = Integer.parseInt(string);
            MyPoint[] points = new MyPoint[pointCount];
            for (int j = 0; j < pointCount; j++) {
                string = bufferedReader.readLine();
                strmas = string.split(" ", 2);
                MyPoint point = new MyPoint(Integer.parseInt(strmas[0]), Integer.parseInt(strmas[1]));
                points[j] = point;
            }
            game.addClosedWall(points);
        }
        game.start();
    }

}