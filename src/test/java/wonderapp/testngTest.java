package wonderapp;

import org.testng.Assert;
import org.testng.annotations.*;

public class testngTest {
    @BeforeClass
    public void setUp(){
        System.out.println("hello world");
        System.out.println("true = " + true);
        Assert.assertEquals(1,1);

    }

    @Test
    public void test(){
        Assert.assertEquals(1,1);

    }

    @AfterClass
    public void tearDown(){
        System.out.println("hello world2222");
        Assert.assertEquals(1,1);
    }
}
