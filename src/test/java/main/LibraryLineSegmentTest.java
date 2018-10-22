package main;

import geometry.LibraryLineSegment;
import geometry.LineSegment;
import geometry.MyPoint;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LibraryLineSegmentTest {
    @Test
    public void isBelongTest1() {
        MyPoint start = new MyPoint(0, 0);
        MyPoint end = new MyPoint(10, 10);
        LineSegment lineSegment = new LibraryLineSegment(start, end);
        MyPoint testPoint = new MyPoint(5, 5);

        Assert.assertTrue(lineSegment.isBelong(testPoint));
    }

    @Test
    public void isBelongTest2() {
        MyPoint start = new MyPoint(0, 0);
        MyPoint end = new MyPoint(10, 10);
        LineSegment lineSegment = new LibraryLineSegment(start, end);
        MyPoint testPoint = new MyPoint(10, 5);

        Assert.assertFalse(lineSegment.isBelong(testPoint));
    }

    @Test
    public void getDistanceTest1() {
        MyPoint start = new MyPoint(0, 0);
        MyPoint end = new MyPoint(10, 0);
        LineSegment lineSegment = new LibraryLineSegment(start, end);
        MyPoint testPoint = new MyPoint(5, 5);

        double actual = lineSegment.getDistanceToPoint(testPoint);
        double expected = 5;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getDistanceTest2() {
        MyPoint start = new MyPoint(0, 0);
        MyPoint end = new MyPoint(10, 10);
        LineSegment lineSegment = new LibraryLineSegment(start, end);
        MyPoint testPoint = new MyPoint(6, 4);

        double actual = lineSegment.getDistanceToPoint(testPoint);
        double expected = Math.sqrt(2);

        Assert.assertEquals(actual, expected);
    }
}
