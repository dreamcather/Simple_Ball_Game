package sample;
import game.Wall;
import geometry.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WallTest  {
    @Test
    public void isBetweenTest1(){
        Wall wall = new Wall(new Point(1,1),new Point(3,3));
        Point point = new Point(1,3);

        boolean res = wall.isBetween(point);

        Assert.assertTrue(res);
    }
    @Test
    public void isBetweenTest2(){
        Wall wall = new Wall(new Point(1,1),new Point(3,3));
        Point point = new Point(4,4);

        boolean res = wall.isBetween(point);

        Assert.assertFalse(res);
    }
}
