package main;

import geometry.Line;
import geometry.Point;
import geometry.Vector;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LineTest {
    @Test
    public void twoPointConstructorTest1() {
        Point start = new Point(0, 0);
        Point end = new Point(1, 1);
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

        if (Math.abs(actualXCoefficient - expectedXCoefficient) < eps)
            xCompare = true;
        if (Math.abs(actualYCoefficient - expectedYCoefficient) < eps)
            yCompare = true;
        if (Math.abs(actualFreeCoefficient - expectedFreeCoefficient) < eps)
            freeCompare = true;

        Assert.assertTrue(xCompare);
        Assert.assertTrue(yCompare);
        Assert.assertTrue(freeCompare);
    }
    @Test
    public void twoPointConstructorTest2(){
        Point start = new Point(0,0);
        Point end  = new Point(0,1);
        Line line = new Line(start,end);

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

        if(Math.abs(actualXCoefficient-expectedXCoefficient)<eps)
            xCompare=true;
        if(Math.abs(actualYCoefficient-expectedYCoefficient)<eps)
            yCompare=true;
        if(Math.abs(actualFreeCoefficient-expectedFreeCoefficient)<eps)
            freeCompare=true;

        Assert.assertTrue(xCompare);
        Assert.assertTrue(yCompare);
        Assert.assertTrue(freeCompare);
    }
    @Test
    public void twoPointConstructorTest3(){
        Point start = new Point(0,0);
        Point end  = new Point(1,0);
        Line line = new Line(start,end);

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

        if(Math.abs(actualXCoefficient-expectedXCoefficient)<eps)
            xCompare=true;
        if(Math.abs(actualYCoefficient-expectedYCoefficient)<eps)
            yCompare=true;
        if(Math.abs(actualFreeCoefficient-expectedFreeCoefficient)<eps)
            freeCompare=true;

        Assert.assertTrue(xCompare);
        Assert.assertTrue(yCompare);
        Assert.assertTrue(freeCompare);
    }
    @Test
    public void vectorPointConstructorTest1(){
        Point point = new Point(0,0);
        Vector vector = new Vector(1,1);
        Line line = new Line(point,vector);

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

        if(Math.abs(actualXCoefficient-expectedXCoefficient)<eps)
            xCompare=true;
        if(Math.abs(actualYCoefficient-expectedYCoefficient)<eps)
            yCompare=true;
        if(Math.abs(actualFreeCoefficient-expectedFreeCoefficient)<eps)
            freeCompare=true;

        Assert.assertTrue(xCompare);
        Assert.assertTrue(yCompare);
        Assert.assertTrue(freeCompare);
    }
    @Test
    public void vectorPointConstructorTest2(){
        Point point = new Point(5,5);
        Vector vector = new Vector(1,1);
        Line line = new Line(point,vector);

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

        if(Math.abs(actualXCoefficient-expectedXCoefficient)<eps)
            xCompare=true;
        if(Math.abs(actualYCoefficient-expectedYCoefficient)<eps)
            yCompare=true;
        if(Math.abs(actualFreeCoefficient-expectedFreeCoefficient)<eps)
            freeCompare=true;

        Assert.assertTrue(xCompare);
        Assert.assertTrue(yCompare);
        Assert.assertTrue(freeCompare);
    }
    @Test
    public void vectorPointConstructorTest3(){
        Point point = new Point(1,0);
        Vector vector = new Vector(0,1);
        Line line = new Line(point,vector);

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

        if(Math.abs(actualXCoefficient-expectedXCoefficient)<eps)
            xCompare=true;
        if(Math.abs(actualYCoefficient-expectedYCoefficient)<eps)
            yCompare=true;
        if(Math.abs(actualFreeCoefficient-expectedFreeCoefficient)<eps)
            freeCompare=true;

        Assert.assertTrue(xCompare);
        Assert.assertTrue(yCompare);
        Assert.assertTrue(freeCompare);
    }
    @Test
    public  void intersectLineTest1(){
        Point firstStart = new Point(1,1);
        Point firstEnd = new Point(45,12);
        Line firstLine = new Line(firstStart,firstEnd);

        Point secondStart = new Point(76,34);
        Point secondEnd = new Point(1,1);
        Line secondLine = new Line(secondStart,secondEnd);

        Point actualIntersectionPoint = firstLine.getLineIntersectionPoint(secondLine);
        Point expectedIntesectionPoint = new Point(1,1);

        boolean res = actualIntersectionPoint.equals(expectedIntesectionPoint);

        Assert.assertTrue(res);

    }
    @Test
    public  void intersectLineTest2(){
        Point firstStart = new Point(1,1);
        Point firstEnd = new Point(17,54);
        Line firstLine = new Line(firstStart,firstEnd);

        Point secondStart = new Point(36,11);
        Point secondEnd = new Point(1,1);
        Line secondLine = new Line(secondStart,secondEnd);

        Point actualIntersectionPoint = firstLine.getLineIntersectionPoint(secondLine);
        Point expectedIntersectionPoint = new Point(1,1);

        boolean res = actualIntersectionPoint.equals(expectedIntersectionPoint);

        Assert.assertTrue(res);

    }
    @Test
    public  void intersectLineTest3(){
        Point firstStart = new Point(1,75);
        Vector firstVector = new Vector(0,1);
        Line firstLine = new Line(firstStart,firstVector);

        Point secondStart = new Point(36,1);
        Vector secondVector = new Vector(1,0);
        Line secondLine = new Line(secondStart,secondVector);

        Point actualIntersectionPoint = firstLine.getLineIntersectionPoint(secondLine);
        Point expectedIntersectionPoint = new Point(1,1);

        boolean res = actualIntersectionPoint.equals(expectedIntersectionPoint);

        Assert.assertTrue(res);

    }
    @Test
    public  void intersectLineTest4() {
        Point firstStart = new Point(1,1);
        Point firstEnd = new Point(34,61);
        Vector firstVector  = new Vector(firstStart,firstEnd);
        Line firstLine = new Line(firstStart,firstVector);

        Point secondStart = new Point(59,33);
        Point secondEnd = new Point(1,1);
        Vector secondVector = new Vector(secondStart,secondEnd);

        Point actualIntersectionPoint = firstLine.getLineIntersectionPoint(secondStart,secondVector);
        Point expectedIntersectionPoint = new Point(1,1);

        boolean res = actualIntersectionPoint.equals(expectedIntersectionPoint);

        Assert.assertTrue(res);

    }
    @Test
    public void distanceToPointTest(){
        Point start = new Point(0,0);
        Point end = new Point(1,0);
        Point curentPoint = new Point(1,1);

        Line line = new Line(start,end);

        double actualDistance = line.calculateDistanceToPoint(curentPoint);
        double expectedDistance = 1;

        boolean res = false;
        double eps =0.00001;
        if(Math.abs(actualDistance-expectedDistance)<eps)
            res = true;
        Assert.assertTrue(res);
    }
    @Test
    public void proectionPointTest(){
        Point start = new Point(2,0);
        Point end = new Point(0,2);
        Point curentPoint = new Point(0,0);

        Line line = new Line(start,end);

        Point actualProectionPoint = line.getProjectionPointToLine(curentPoint);
        Point expectedProectionPoint = new Point(1,1);

        boolean res = actualProectionPoint.equals(expectedProectionPoint);

        Assert.assertTrue(res);
    }
    @Test
    public  void getNormalTest()
    {
        Point start = new Point(2,0);
        Point end = new Point(0,2);
        Line line = new Line(start,end);
        Vector actualVector = line.getNormal();
        Vector expectedVector = new Vector(2,2);

        boolean res = actualVector.equals(expectedVector);
        Assert.assertTrue(res);

    }

}
