package main;

import geometry.MyPoint;
import org.locationtech.jts.geom.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
    @Test
    public void PointConvertTest(){
        MyPoint myPoint = new MyPoint(10,10);
        Point point = myPoint.convertPoint();

        double actual = point.getX();
        double expected = 10;

        Assert.assertEquals(actual,expected);
    }
}
