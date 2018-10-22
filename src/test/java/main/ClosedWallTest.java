package main;

import gameObject.Ball;
import gameObject.ClosedWall;
import gameObject.Prize;
import geometry.MyPoint;
import geometry.Vector;
import org.testng.Assert;
import org.testng.annotations.Test;
import visual.Camera;
import visual.visualInformation.VisualInformation;

public class ClosedWallTest {
    @Test
    public void ClosedWallDistanceToPointTest1() {
        ClosedWall closedWall = new ClosedWall(new MyPoint[] {
                new MyPoint(0, 0),
                new MyPoint(10, 0),
                new MyPoint(10, 10),
                new MyPoint(0, 10) }, 0);
        MyPoint point = new MyPoint(-5, 5);

        double actual = closedWall.getDistance(point);
        double expected = 5;

        Assert.assertEquals(actual, expected);

    }

    @Test
    public void getResultPerpendicularVectorTest1() {
        ClosedWall closedWall = new ClosedWall(new MyPoint[] {
                new MyPoint(0, 0),
                new MyPoint(10, 0),
                new MyPoint(10, 10),
                new MyPoint(0, 10) }, 0);
        Ball ball = new Prize(1, 1, 5, 13, 5, 10, 0);

        Vector actual = closedWall.getResultPerpendicularVector(ball);
        Vector expected = new Vector(1, 0);

        Assert.assertTrue(actual.equals(expected));

    }

    @Test
    public void getResultPerpendicularVectorTest2() {
        ClosedWall closedWall = new ClosedWall(new MyPoint[] {
                new MyPoint(0, 0),
                new MyPoint(10, 0),
                new MyPoint(10, 10),
                new MyPoint(0, 10) }, 0);
        Ball ball = new Prize(1, 1, 5, 13, 13, 5, 0);

        Vector actual = closedWall.getResultPerpendicularVector(ball);
        Vector expected = new Vector(1, 1);

        Assert.assertTrue(actual.equals(expected));

    }

    @Test
    public void isVisibleTest1(){
        ClosedWall closedWall = new ClosedWall(new MyPoint[] {
                new MyPoint(0, 0),
                new MyPoint(750, 0),
                new MyPoint(750, 750),
                new MyPoint(0, 750) }, 0);
        Camera camera = new Camera(new MyPoint(200,200),0,0,1000,1000,1000);

        VisualInformation visualInformation =closedWall.isVisible(camera);


    }

}
