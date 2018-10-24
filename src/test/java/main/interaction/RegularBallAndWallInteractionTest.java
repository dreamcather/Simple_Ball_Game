package main.interaction;

import gameObject.Ball;
import gameObject.Enemy;
import gameObject.Wall;
import interaction.DetectionVisitor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegularBallAndWallInteractionTest {
    @Test
    public void detectionTest1() {
        Ball ball = new Enemy(1, 1, 5, 5, 5, 5, 0);
        Wall wall = new Wall("7 1 7 15", 2);

        Assert.assertTrue(ball.collision(wall.collision(new DetectionVisitor())).detect());

    }

    @Test
    public void detectionTest2() {
        Ball ball = new Enemy(1, 1, 5, 0, 0, 5, 0);
        Wall wall = new Wall("7 1 7 15", 2);

        Assert.assertFalse(ball.collision(wall.collision(new DetectionVisitor())).detect());

    }

    @Test
    public void detectionTest3() {
        Ball ball = new Enemy(1, 1, 5, 5, 1, 5, 0);
        Wall wall = new Wall("5 5 5 15", 2);

        Assert.assertTrue(ball.collision(wall.collision(new DetectionVisitor())).detect());

    }

    @Test
    public void collisionTest1(){
        Ball ball = new Enemy(1, 0, 5, 0, 0, 5, 0);
        Wall wall = new Wall("5 0 5 15", 2);

        ball.collision(wall.collision(new DetectionVisitor())).collision();

        double expected = 1;
        double actual = ball.getVector().getXCoefficient();

        Assert.assertEquals(actual,expected);
    }
}
