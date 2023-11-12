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
     * Tests if Rational initializes to having a numerator of 0 and denominator of 1
     */
    public void testDefaultConstructor() {
        // Given no preconditions
        Rational value = new Rational();

        // numerator = 0 and denominator = 1
        assertThat("The numerator is 0", value.numerator(), is(0));
        assertThat("The denominator is 1", value.denominator(), is(1));
    }

    public void testOneArgumentConstructor(){
        // Create a `Rational` with argument `2`
        Rational value = new Rational(2);

        // numerator = 2 and denominator = 1
        assertThat("The numerator is 2", value.numerator(), is(2));
        assertThat("The denominator is 1", value.denominator(), is(1));
    }
}
