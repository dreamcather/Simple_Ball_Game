package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegularCollisionTest {
    @Test
    public void collisionTest1() {
        AnchorPane anchorPane = new AnchorPane();
        Enemy first = new Enemy(new CircleModel(100,100,3, Color.BLUE,anchorPane),
                1,0,3);
        Prize second = new Prize(new CircleModel(110,100,3, Color.RED,anchorPane),
                -1,0,3);
        first.accept(second.accept(new CollisionVisitor())).collide();

        double expectedXCoefficient =-1;
        double expectedYCoefficient =0;

        double actualXCoefficient = first.ball.xCoefficient;
        double actualYCoefficient = first.ball.yCoefficient;

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
