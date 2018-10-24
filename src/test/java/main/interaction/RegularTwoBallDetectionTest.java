package main.interaction;

import gameObject.Ball;
import gameObject.Player;
import gameObject.Prize;
import interaction.DetectionVisitor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegularTwoBallDetectionTest {
    @Test
    public void detectionTest1(){
        Ball first = new Player(1,1,5,10,10,5,0);
        Ball second = new Prize(1,1,5,12,12,5,1);

        Assert.assertTrue(first.collision(second.collision(new DetectionVisitor())).detect());
    }

    @Test
    public void detectionTest2(){
        Ball first = new Player(1,1,5,10,10,5,0);
        Ball second = new Prize(1,1,5,21,20,5,1);

        Assert.assertFalse(first.collision(second.collision(new DetectionVisitor())).detect());
    }
}
