package save;

import game.PhysicGame;
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
        string = bufferedReader.readLine();
        strmas = string.split(" ",2);
        int closedWallCount = Integer.parseInt(strmas[1]);
        for(int i=0;i<closedWallCount;i++){
            string = bufferedReader.readLine();
            int pointCount = Integer.parseInt(string);
            MyPoint[] points = new MyPoint[pointCount];
            for(int j=0;j<pointCount;j++){
                string = bufferedReader.readLine();
                strmas = string.split(" ",2);
                MyPoint point = new MyPoint(Integer.parseInt(strmas[0]),Integer.parseInt(strmas[1]));
                points[j] =point;
            }
            physicGame.addClosedWall(points);
        }
    }

}