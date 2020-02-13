package sample.junit5;

import com.example.SampleController;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class JUnit5Test {

    private SampleController demoValue;

    @Before
    public void indexGet() {
        SampleController demoValue = new SampleController();
    }

    @Test
    public void ALL_GET() {
        Assertions.assertEquals("content002", demoValue.index());
    }

    //static class StaticClass {
    //    @Test
    //    void fail() {
    //        Assertions.assertEquals(10, 8);
    //    }
    //}
}
