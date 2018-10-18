package main;

import gameObject.ClosedWall;
import geometry.MyPoint;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ClosedWallTest {
    @Test
    public void ClosedWallDistanceToPointTest1(){
        ClosedWall closedWall = new ClosedWall(new MyPoint[]{new MyPoint(0,0),new MyPoint(10,0),
        new MyPoint(10,10),new MyPoint(10,0)},0);
        MyPoint point = new MyPoint(-5,5);

        double actual = closedWall.getDistance(point);
        double expected = 5;

        Assert.assertEquals(actual,expected);

    }

}
