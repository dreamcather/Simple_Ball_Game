package main;

import geometry.MyPoint;
import org.locationtech.jts.geom.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
    @Test
    public void PointConvertTest() {
        MyPoint myPoint = new MyPoint(10, 10);
        Point point = myPoint.convertPoint();

        double actual = point.getX();
        double expected = 10;

        Assert.assertEquals(actual, expected);
    }
    @Test
    public void setParamTest1(){
        MyPoint actual = new MyPoint(0,0);

        actual.setX(10);
        actual.setY(10);

        MyPoint expected = new MyPoint(10,10);

        Assert.assertTrue(actual.equals(expected));
    }
    @Test
    public void ConstructorTest(){
        MyPoint point = new MyPoint(10,10);
        MyPoint expected = new MyPoint(point);

        Assert.assertTrue(point.equals(expected));
    }
}
