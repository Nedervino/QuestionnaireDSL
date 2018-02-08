import org.junit.Test;
import ql.TestClass;

import static org.junit.Assert.*;

public class JUnitTestClass {

    @Test
    public void testMethodReturnsCorrectString() {
        assertEquals(TestClass.test(), "JUnit test");
    }

}