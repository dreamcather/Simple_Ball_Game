package Save;

import game.PhysicGame;
import gameObject.Wall;
import geometry.Point;

import java.io.*;

public class Rider {
    File file;
    FileReader fileReader;

    public Rider(String path, PhysicGame physicGame) throws IOException {
        file = new File(path);
        fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Wall wall = new Wall(bufferedReader.readLine());
        physicGame.addWall(new Point(400,450),new Point(400,400));
        //physicGame.addWall(wall.getStart(),wall.getEnd());

    }
}
