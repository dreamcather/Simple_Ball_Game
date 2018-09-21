package sample;

import interaction.CollisionVisitor;
import object.Enemy;
import object.Prize;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegularCollisionTest {
    @Test
    public void collisionTest1() {
        Enemy first = new Enemy(1,0,3,
                100,100,5);
        Prize second = new Prize(-1,0,3,
                110,100,5);
        first.accept(second.accept(new CollisionVisitor())).collide();

        double expectedXCoefficient =-1;
        double expectedYCoefficient =0;

        double actualXCoefficient = first.getVector().getXCoefficient();
        double actualYCoefficient = first.getVector().getYCoefficient();

        boolean xCompare = false;
        boolean yCompare = false;

        double eps = 0.0000001;

        if (Math.abs(actualXCoefficient - expectedXCoefficient) < eps)
            xCompare = true;
        if (Math.abs(actualYCoefficient - expectedYCoefficient) < eps)
            yCompare = true;

        Assert.assertTrue(xCompare);
        Assert.assertTrue(yCompare);

    }
}
