package main.object;

import gameObject.Ball;
import gameObject.Prize;
import geometry.MyPoint;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BallTest {
    @Test
    public void moveTest1(){
        Ball ball = new Prize(3,4,5,0,0,5,0);

        ball.move();

        MyPoint actual = ball.getPosition();
        MyPoint expected = new MyPoint(3,4);

        Assert.assertTrue(actual.equals(expected));
    }
}
