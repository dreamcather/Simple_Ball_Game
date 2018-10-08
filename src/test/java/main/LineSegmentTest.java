package main;

import geometry.LineSegment;
import geometry.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LineSegmentTest {
    @Test
    public void lineSegmentDistanceToPointTest(){
        LineSegment lineSegment = new LineSegment(new Point(0,0),new Point(0,10));
        Point point = new Point(-5,5);

        double actual = lineSegment.getDistanceToPoint(point);
        double expected =5;

        Assert.assertEquals(actual,expected);
    }
}
