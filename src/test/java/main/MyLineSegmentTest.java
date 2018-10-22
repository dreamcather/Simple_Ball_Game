package main;

import geometry.MyLineSegment;
import geometry.MyPoint;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyLineSegmentTest {
    @Test
    public void lineSegmentDistanceToPointTest() {
        MyLineSegment lineSegment = new MyLineSegment(new MyPoint(0, 0), new MyPoint(0, 10));
        MyPoint point = new MyPoint(-5, 5);

        double actual = lineSegment.getDistanceToPoint(point);
        double expected = 5;

        Assert.assertEquals(actual, expected);
    }
}
