package sample;

import javafx.scene.layout.AnchorPane;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegularCollisionTest {
    @Test
    public void collisionTest1() {
        AnchorPane anchorPane = new AnchorPane();
        Enemy first = new Enemy(1,0,1,100,100,anchorPane);
        Enemy second  = new Enemy(-1,0,1,100,105,anchorPane);
        first.accept(second.accept(new CollisionVisitor())).collide();

        double expectedXCoefficient =1;
        double expectedYCoefficient =0;

        double actualXCoefficient = first.xCoefficient;
        double actualYCoefficient = first.yCoefficient;

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
