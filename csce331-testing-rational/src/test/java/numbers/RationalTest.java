package numbers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;

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

    /**
     * Given a numerator 2, the fraction should be 2/1
     */
    public void testOneArgumentConstructor() {
        // Create a `Rational` with argument `2`
        Rational value = new Rational(2);

        // numerator = 2 and denominator = 1
        assertThat("The numerator is 2", value.numerator(), is(2));
        assertThat("The denominator is 1", value.denominator(), is(1));
    }

    /**
     * Given a numerator and denominator 2/3, the numerator and denominator should
     * reflect this
     */
    public void testTwoArgumentConstructor() {
        // Create a rational with arguments `2` and `3`
        Rational value = new Rational(2, 3);

        // numerator = 2 and denominator = 3
        assertThat("The numerator is 2", value.numerator(), is(2));
        assertThat("The denominator is 3", value.denominator(), is(3));
    }

    /**
     * If I have 48/-72, this can be simplified to -2/3
     */
    public void testTwoArgumentConstructorSimplification() {
        // Create a `Rational` value with `48` and `-72`
        Rational value = new Rational(48, -72);

        // Numerator: `-2`
        assertThat("Rational simplifies with constructor: 48 / -72 = -2 / 3", value.numerator(), is(-2));
        // Denominator: `3`
        assertThat("Rational simplifies with constructor: 48 / -72 = -2 / 3", value.denominator(), is(3));
    }

    /**
     * Given a denominator of 0, the constructor should throw an exception
     */
    public void testTwoArgumentConstructorInvalidDenominator(){
        // This should throw
        assertThrows(IllegalArgumentException.class, () -> new Rational(1, 0));
    }
}
