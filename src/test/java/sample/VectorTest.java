package sample;

import org.testng.Assert;
import org.testng.annotations.Test;

public class VectorTest {
    @Test
    public void reflectionTest1(){
        Vector mainVector =new Vector(1,-1);
        Vector perpendicularVector = new Vector(0,1);
        Vector actual = mainVector.getReflection(perpendicularVector);

        Vector expected = new Vector(1,1);

        boolean flag = actual.equals(expected);

        Assert.assertTrue(flag);
    }
}
