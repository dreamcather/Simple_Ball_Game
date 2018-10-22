package main;

import geometry.GeometricalCalculation;
import geometry.Vector;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VectorTest {
    @Test
    public void reflectionTest1() {
        Vector mainVector = new Vector(1, -1);
        Vector perpendicularVector = new Vector(0, 1);
        Vector actual = GeometricalCalculation.vectorReflection(mainVector, perpendicularVector);

        Vector expected = new Vector(1, 1);

        boolean flag = actual.equals(expected);

        Assert.assertTrue(flag);
    }
    @Test
    public void sumVectorTest1(){
        Vector actual = new Vector(1,1);
        Vector addVector = new Vector(1,1);

        actual.sumVector(addVector);

        Vector expected = new Vector(2,2);

        Assert.assertTrue(actual.equals(expected));
    }
}
