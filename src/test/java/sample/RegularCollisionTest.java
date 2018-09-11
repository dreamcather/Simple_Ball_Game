package sample;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RegularCollisionTest {
    @Test
    public void a() {
        int expected =4;
        int actual =5;
        Assert.assertEquals(expected,actual);

    }
}
