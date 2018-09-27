package save;

import game.Game;
import gameObject.Wall;

import java.io.*;

public class Rider {
    File file;
    FileReader fileReader;

    public Rider(String path, Game physicGame) throws IOException {
        file = new File(path);
        fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string = bufferedReader.readLine();
        String[] strmas = string.split("  ", 2);
        int wallCount = Integer.parseInt(strmas[1]);
        for (int i = 0; i < wallCount; i++) {
            Wall wall = new Wall(bufferedReader.readLine());
            physicGame.addWall(wall.getStart(), wall.getEnd());
        }
        string = bufferedReader.readLine();
        physicGame.addHero(bufferedReader.readLine());
        string = bufferedReader.readLine();
        strmas = string.split("  ", 2);
        int enemyCount = Integer.parseInt(strmas[1]);
        for (int i = 0; i < enemyCount; i++) {
            physicGame.addEnemy(bufferedReader.readLine());

        }
        string = bufferedReader.readLine();
        strmas = string.split("  ", 2);
        int prizeCount = Integer.parseInt(strmas[1]);
        for (int i = 0; i < prizeCount; i++) {
            physicGame.addPrize(bufferedReader.readLine());

        }
    }

}
