package Save;

import java.io.*;

public class Rider {
    File file;
    FileReader fileReader;

    public Rider(String path) throws IOException {
        file = new File(path);
        fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        System.out.print(bufferedReader.readLine());
    }
}
