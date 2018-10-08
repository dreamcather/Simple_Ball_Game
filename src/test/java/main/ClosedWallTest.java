package main;

import gameObject.ClosedWall;
import geometry.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ClosedWallTest {
    @Test
    public void ClosedWallDistanceToPointTest1(){
        ClosedWall closedWall = new ClosedWall(new Point[]{new Point(0,0),new Point(10,0),
        new Point(10,10),new Point(10,0)});
        Point point = new Point(-5,5);

        double actual = closedWall.getDistance(point);
        double expected = 5;

        Assert.assertEquals(actual,expected);

    }

}
