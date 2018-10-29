package main.geometry;

import geometry.GeometricalCalculation;
import geometry.Line;
import geometry.MyPoint;
import geometry.Vector;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LineTest {
    @Test
    public void twoPointConstructorTest1() {
        MyPoint start = new MyPoint(0, 0);
        MyPoint end = new MyPoint(1, 1);
        Line line = new Line(start, end);

        double expectedXCoefficient = 1;
        double expectedYCoefficient = -1;
        double expectedFreeCoefficient = 0;

        double actualXCoefficient = line.getXCoefficient();
        double actualYCoefficient = line.getYCoefficient();
        double actualFreeCoefficient = line.getFreeCoefficient();

        boolean xCompare = false;
        boolean yCompare = false;
        boolean freeCompare = false;

        double eps = 0.0000001;

        if (Math.abs(actualXCoefficient) - Math.abs(expectedXCoefficient) < eps)
            xCompare = true;
        if (Math.abs(actualYCoefficient) - Math.abs(expectedYCoefficient) < eps)
            yCompare = true;
        if (Math.abs(actualFreeCoefficient) - Math.abs(expectedFreeCoefficient) < eps)
            freeCompare = true;

        Assert.assertTrue(xCompare);
        Assert.assertTrue(yCompare);
        Assert.assertTrue(freeCompare);
    }

    @Test
    public void twoPointConstructorTest2() {
        MyPoint start = new MyPoint(0, 0);
        MyPoint end = new MyPoint(0, 1);
        Line line = new Line(start, end);

        double expectedXCoefficient = 1;
        double expectedYCoefficient = 0;
        double expectedFreeCoefficient = 0;

        double actualXCoefficient = line.getXCoefficient();
        double actualYCoefficient = line.getYCoefficient();
        double actualFreeCoefficient = line.getFreeCoefficient();

        boolean xCompare = false;
        boolean yCompare = false;
        boolean freeCompare = false;

        double eps = 0.0000001;

        if (Math.abs(actualXCoefficient) - Math.abs(expectedXCoefficient) < eps)
            xCompare = true;
        if (Math.abs(actualYCoefficient) - Math.abs(expectedYCoefficient) < eps)
            yCompare = true;
        if (Math.abs(actualFreeCoefficient) - Math.abs(expectedFreeCoefficient) < eps)
            freeCompare = true;

        Assert.assertTrue(xCompare);
        Assert.assertTrue(yCompare);
        Assert.assertTrue(freeCompare);
    }

    @Test
    public void twoPointConstructorTest3() {
        MyPoint start = new MyPoint(0, 0);
        MyPoint end = new MyPoint(1, 0);
        Line line = new Line(start, end);

        double expectedXCoefficient = 0;
        double expectedYCoefficient = 1;
        double expectedFreeCoefficient = 0;

        double actualXCoefficient = line.getXCoefficient();
        double actualYCoefficient = line.getYCoefficient();
        double actualFreeCoefficient = line.getFreeCoefficient();

        boolean xCompare = false;
        boolean yCompare = false;
        boolean freeCompare = false;

        double eps = 0.0000001;

        if (Math.abs(actualXCoefficient) - Math.abs(expectedXCoefficient) < eps)
            xCompare = true;
        if (Math.abs(actualYCoefficient) - Math.abs(expectedYCoefficient) < eps)
            yCompare = true;
        if (Math.abs(actualFreeCoefficient) - Math.abs(expectedFreeCoefficient) < eps)
            freeCompare = true;

        Assert.assertTrue(xCompare);
        Assert.assertTrue(yCompare);
        Assert.assertTrue(freeCompare);
    }

    @Test
    public void vectorPointConstructorTest1() {
        MyPoint point = new MyPoint(0, 0);
        Vector vector = new Vector(1, 1);
        Line line = new Line(point, vector);

        double expectedXCoefficient = 1;
        double expectedYCoefficient = -1;
        double expectedFreeCoefficient = 0;

        double actualXCoefficient = line.getXCoefficient();
        double actualYCoefficient = line.getYCoefficient();
        double actualFreeCoefficient = line.getFreeCoefficient();

        boolean xCompare = false;
        boolean yCompare = false;
        boolean freeCompare = false;

        double eps = 0.0000001;

        if (Math.abs(actualXCoefficient) - Math.abs(expectedXCoefficient) < eps)
            xCompare = true;
        if (Math.abs(actualYCoefficient) - Math.abs(expectedYCoefficient) < eps)
            yCompare = true;
        if (Math.abs(actualFreeCoefficient) - Math.abs(expectedFreeCoefficient) < eps)
            freeCompare = true;

        Assert.assertTrue(xCompare);
        Assert.assertTrue(yCompare);
        Assert.assertTrue(freeCompare);
    }

    @Test
    public void vectorPointConstructorTest2() {
        MyPoint point = new MyPoint(5, 5);
        Vector vector = new Vector(1, 1);
        Line line = new Line(point, vector);

        double expectedXCoefficient = 1;
        double expectedYCoefficient = -1;
        double expectedFreeCoefficient = 0;

        double actualXCoefficient = line.getXCoefficient();
        double actualYCoefficient = line.getYCoefficient();
        double actualFreeCoefficient = line.getFreeCoefficient();

        boolean xCompare = false;
        boolean yCompare = false;
        boolean freeCompare = false;

        double eps = 0.0000001;

        if (Math.abs(actualXCoefficient) - Math.abs(expectedXCoefficient) < eps)
            xCompare = true;
        if (Math.abs(actualYCoefficient) - Math.abs(expectedYCoefficient) < eps)
            yCompare = true;
        if (Math.abs(actualFreeCoefficient) - Math.abs(expectedFreeCoefficient) < eps)
            freeCompare = true;

        Assert.assertTrue(xCompare);
        Assert.assertTrue(yCompare);
        Assert.assertTrue(freeCompare);
    }

    @Test
    public void vectorPointConstructorTest3() {
        MyPoint point = new MyPoint(1, 0);
        Vector vector = new Vector(0, 1);
        Line line = new Line(point, vector);

        double expectedXCoefficient = 1;
        double expectedYCoefficient = 0;
        double expectedFreeCoefficient = -1;

        double actualXCoefficient = line.getXCoefficient();
        double actualYCoefficient = line.getYCoefficient();
        double actualFreeCoefficient = line.getFreeCoefficient();

        boolean xCompare = false;
        boolean yCompare = false;
        boolean freeCompare = false;

        double eps = 0.0000001;

        if (Math.abs(actualXCoefficient) - Math.abs(expectedXCoefficient) < eps)
            xCompare = true;
        if (Math.abs(actualYCoefficient) - Math.abs(expectedYCoefficient) < eps)
            yCompare = true;
        if (Math.abs(actualFreeCoefficient) - Math.abs(expectedFreeCoefficient) < eps)
            freeCompare = true;

        Assert.assertTrue(xCompare);
        Assert.assertTrue(yCompare);
        Assert.assertTrue(freeCompare);
    }

    @Test
    public void intersectLineTest1() {
        MyPoint firstStart = new MyPoint(1, 1);
        MyPoint firstEnd = new MyPoint(45, 12);
        Line firstLine = new Line(firstStart, firstEnd);

        MyPoint secondStart = new MyPoint(76, 34);
        MyPoint secondEnd = new MyPoint(1, 1);
        Line secondLine = new Line(secondStart, secondEnd);

        MyPoint actualIntersectionPoint = GeometricalCalculation.lineIntersection(firstLine, secondLine);
        MyPoint expectedIntersectionPoint = new MyPoint(1, 1);

        boolean res = actualIntersectionPoint.equals(expectedIntersectionPoint);

        Assert.assertTrue(res);

    }

    @Test
    public void intersectLineTest2() {
        MyPoint firstStart = new MyPoint(1, 1);
        MyPoint firstEnd = new MyPoint(17, 54);
        Line firstLine = new Line(firstStart, firstEnd);

        MyPoint secondStart = new MyPoint(36, 11);
        MyPoint secondEnd = new MyPoint(1, 1);
        Line secondLine = new Line(secondStart, secondEnd);

        MyPoint actualIntersectionPoint = GeometricalCalculation.lineIntersection(firstLine, secondLine);
        MyPoint expectedIntersectionPoint = new MyPoint(1, 1);

        boolean res = false;
        if (actualIntersectionPoint != null) {
            res = actualIntersectionPoint.equals(expectedIntersectionPoint);
        }

        Assert.assertTrue(res);

    }

    @Test
    public void intersectLineTest3() {
        MyPoint firstStart = new MyPoint(1, 75);
        Vector firstVector = new Vector(0, 1);
        Line firstLine = new Line(firstStart, firstVector);

        MyPoint secondStart = new MyPoint(36, 1);
        Vector secondVector = new Vector(1, 0);
        Line secondLine = new Line(secondStart, secondVector);

        MyPoint actualIntersectionPoint = GeometricalCalculation.lineIntersection(firstLine, secondLine);
        MyPoint expectedIntersectionPoint = new MyPoint(1, 1);

        boolean res = actualIntersectionPoint.equals(expectedIntersectionPoint);

        Assert.assertTrue(res);

    }

    @Test
    public void intersectLineTest4() {
        MyPoint firstStart = new MyPoint(1, 1);
        MyPoint firstEnd = new MyPoint(34, 61);
        Vector firstVector = new Vector(firstStart, firstEnd);
        Line firstLine = new Line(firstStart, firstVector);

        MyPoint secondStart = new MyPoint(59, 33);
        MyPoint secondEnd = new MyPoint(1, 1);
        Vector secondVector = new Vector(secondStart, secondEnd);

        MyPoint actualIntersectionPoint = firstLine.getLineIntersectionPoint(secondStart, secondVector);
        MyPoint expectedIntersectionPoint = new MyPoint(1, 1);

        boolean res = actualIntersectionPoint.equals(expectedIntersectionPoint);

        Assert.assertTrue(res);

    }

    @Test
    public void distanceToPointTest() {
        MyPoint start = new MyPoint(0, 0);
        MyPoint end = new MyPoint(1, 0);
        MyPoint currentPoint = new MyPoint(1, 1);

        Line line = new Line(start, end);

        double actualDistance = line.calculateDistanceToPoint(currentPoint);
        double expectedDistance = 1;

        boolean res = false;
        double eps = 0.00001;
        if (Math.abs(actualDistance - expectedDistance) < eps)
            res = true;
        Assert.assertTrue(res);
    }

    @Test
    public void projectionPointTest() {
        MyPoint start = new MyPoint(2, 0);
        MyPoint end = new MyPoint(0, 2);
        MyPoint currentPoint = new MyPoint(0, 0);

        Line line = new Line(start, end);

        MyPoint actualProjectionPoint = line.getProjectionPointToLine(currentPoint);
        MyPoint expectedProjectionPoint = new MyPoint(1, 1);

        boolean res = actualProjectionPoint.equals(expectedProjectionPoint);

        Assert.assertTrue(res);
    }

    @Test
    public void getNormalTest() {
        MyPoint start = new MyPoint(2, 0);
        MyPoint end = new MyPoint(0, 2);
        Line line = new Line(start, end);
        Vector actualVector = line.getNormal();
        Vector expectedVector = new Vector(2, 2);
        expectedVector.norm();

        boolean res = actualVector.equals(expectedVector);
        Assert.assertTrue(res);

    }

}
