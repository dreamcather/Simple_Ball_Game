package save;

import gameObject.Wall;

import java.io.*;

public class Writer {
    String path;
    int enemyCounter;
    int heroCounter;
    int prizeCounter;
    int wallCounter;
    File file;
    FileWriter fileWriter;

    
    public Writer(String path) throws IOException {
        this.path = path;
        file = new File(path);
        file.createNewFile();
        fileWriter = new FileWriter(file);
    }

    public void writeWall(Wall wall) throws IOException {
        fileWriter.write(wall.toString());
        wallCounter++;
        fileWriter.flush();
    }
    public void close() throws IOException {
        fileWriter.close();
    }



}
