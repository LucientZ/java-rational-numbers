package numbers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple Rational.
 */
public class RationalTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RationalTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(RationalTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testRational() {
        // Given no preconditions

        Rational value = new Rational();

        assertThat("The numerator is 0", value.numerator(), is(0));
        assertThat("The denominator is 1", value.denominator(), is(1));
    }
}
