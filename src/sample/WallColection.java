package sample;

public class WallColection {

    public Wall[] collection;

    WallColection(int count) {

        collection = new Wall[4];

        collection[0] = new Wall(0,0,0,100);

        collection[0] = new Wall(0,0,100,0);

        collection[0] = new Wall(100,100,0,100);

        collection[0] = new Wall(100,100,100,0);

    }
}
