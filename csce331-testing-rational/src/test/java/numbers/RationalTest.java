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
    public void testTwoArgumentConstructorInvalidDenominator() {
        // This should throw an error because a denominator value of 0 is not allowed
        assertThrows(IllegalArgumentException.class, () -> new Rational(1, 0));
    }

    /**
     * Given a negative numerator and positive denominator, these values should hold
     * the same
     */
    public void testTwoArgumentConstructorNegativeNumerator() {
        Rational value = new Rational(-9, 5);
        assertThat("Numerator is -9", value.numerator(), is(-9));
        assertThat("Denominator is 5", value.denominator(), is(5));
    }

    /**
     * Given a positive numerator and negative denominator, the signs should flip
     */
    public void testTwoArgumentConstructorNegativeDenominator() {
        Rational value = new Rational(9, -5);
        assertThat("Numerator is -9", value.numerator(), is(-9));
        assertThat("Denominator is 5", value.denominator(), is(5));
    }

    /**
     * Given a negative numerator and negative denominator, the values should both
     * be positive
     */
    public void testTwoArgumentConstructorNegativeNumeratorAndDenominator() {
        Rational value = new Rational(-9, -5);
        assertThat("Numerator is 9", value.numerator(), is(9));
        assertThat("Denominator is 5", value.denominator(), is(5));
    }

    /**
     * When the numerator is 0, this should simplify to 0 / 1 given any denominator
     */
    public void testTwoArgumentConstructorZeroNumerator() {
        Rational value = new Rational(0, 3295);
        assertThat("Numerator is 0", value.numerator(), is(0));
        assertThat("Denominator is 1", value.denominator(), is(1));
    }

    /**
     * Tests when the numerator and denominator arguments are equal to each other.
     * This should simplify to 1 / 1
     */
    public void testTwoArgumentConstructorEqualNumeratorAndDenominator() {
        Rational value = new Rational(125, 125);
        assertThat("Numerator is 1", value.numerator(), is(1));
        assertThat("Denominator is 1", value.denominator(), is(1));
    }

    /**
     * Tests constructor with very big numbers (integer limits)
     */
    public void testTwoArgumentConstructorVeryBigNumbers() {
        Rational value = new Rational(-2147483647, 2147483647);

        assertThat("Numerator is -1", value.numerator(), is(-1));
        assertThat("Denominator is 1", value.denominator(), is(1));
    }

    /**
     * Tests a copy constructor which creates a `Rational` value given another
     * `Rational`
     */
    public void testCopyConstructor() {
        // Create a rational
        Rational original = new Rational(2, 3);

        // Creates a rational which copies original
        Rational value = new Rational(original);

        assertThat("Numerator is 2", value.numerator(), is(2));
        assertThat("Denominator is 3", value.denominator(), is(3));
    }

    /**
     * Tests that copy works with a negative numerator
     */
    public void testCopyConstructorNegativeNumerator() {
        Rational original = new Rational(-23, 2);
        Rational value = new Rational(original);

        assertThat("Numerator is -23", value.numerator(), is(-23));
        assertThat("Denominator is 2", value.denominator(), is(2));
    }

    /**
     * Tests the creation of an additive inverse of value given a positive numerator
     */
    public void testOppositePositiveNumerator() {
        Rational value = new Rational(2, 3);
        Rational opposite = value.opposite();

        // Ensure that `value` is unchanged
        assertThat("value Numerator is 2", value.numerator(), is(2));
        assertThat("value Denominator is 3", value.denominator(), is(3));

        // Ensure that `opposite` is the additive inverse
        assertThat("opposite Numerator is -2", opposite.numerator(), is(-2));
        assertThat("opposite Denominator is 3", opposite.denominator(), is(3));
    }

    /**
     * Tests the creation of an additive inverse given a negative numerator
     */
    public void testOppositeNegativeNumerator() {
        Rational value = new Rational(2, -3);
        Rational opposite = value.opposite();

        // Ensure that `value` is unchanged
        assertThat("value Numerator is -2", value.numerator(), is(-2));
        assertThat("value Denominator is 3", value.denominator(), is(3));

        // Ensure that `opposite` is the additive inverse
        assertThat("opposite Numerator is 2", opposite.numerator(), is(2));
        assertThat("opposite Denominator is 3", opposite.denominator(), is(3));
    }

    /**
     * Tests the reciprocal function.
     * a / b -> b / a
     */
    public void testReciprocal() {
        Rational value = new Rational(2, 3);
        Rational reciprocal = value.reciprocal();

        // Ensure that `value` is unchanged
        assertThat("value Numerator is 2", value.numerator(), is(2));
        assertThat("value Denominator is 3", value.denominator(), is(3));

        // Ensure that `reciprocal` is the reciprocal
        assertThat("reciprocal Numerator is 3", reciprocal.numerator(), is(3));
        assertThat("reciprocal Denominator is 2", reciprocal.denominator(), is(2));
    }

    /**
     * Ensures that the reciprocal follows the convention that the numerator is
     * negative
     * -a / b -> -b / a
     */
    public void testReciprocalNegativeNumerator() {
        Rational value = new Rational(-2, 3);
        Rational reciprocal = value.reciprocal();

        // Ensure that `value` is unchanged
        assertThat("value Numerator is -2", value.numerator(), is(-2));
        assertThat("value Denominator is 3", value.denominator(), is(3));

        // Ensure that `reciprocal` is the reciprocal
        assertThat("reciprocal Numerator is -3", reciprocal.numerator(), is(-3));
        assertThat("reciprocal Denominator is 2", reciprocal.denominator(), is(2));
    }

    /**
     * Because the denominator should not be 0, if the numerator of a value is 0,
     * its reciprocal should throw an exception.
     * 0 / b -> b / 0 (NOT ALLOWED!)
     */
    public void testReciprocalZeroNumerator() {
        Rational value = new Rational(0);
        assertThrows(IllegalArgumentException.class, () -> value.reciprocal());
    }

    public void testTimes() {
        Rational first = new Rational(2, 3);
        Rational second = new Rational(5, 7);
        
        Rational result = first.times(second);
        assertThat("2 * 5 = 10", result.numerator(), is(10));
        assertThat("3 * 7 = 21", result.denominator(), is(21));
        
        // Ensure commutative property of multiplication holds
        result = second.times(first);
        assertThat("2 * 5 = 10", result.numerator(), is(10));
        assertThat("3 * 7 = 21", result.denominator(), is(21));
    }

    public void testTimesNegativeNumeratorFirst() {
        Rational first = new Rational(-2, 3);
        Rational second = new Rational(5, 7);
        
        Rational result = first.times(second);
        assertThat("-2 * 5 = -10", result.numerator(), is(-10));
        assertThat("3 * 7 = 21", result.denominator(), is(21));
        
        // Ensure commutative property of multiplication holds
        result = second.times(first);
        assertThat("-2 * 5 = -10", result.numerator(), is(-10));
        assertThat("3 * 7 = 21", result.denominator(), is(21));
    }

    public void testTimesNegativeNumeratorSecond() {
        Rational first = new Rational(2, 3);
        Rational second = new Rational(-5, 7);
        
        Rational result = first.times(second);
        assertThat("2 * -5 = -10", result.numerator(), is(-10));
        assertThat("3 * 7 = 21", result.denominator(), is(21));
        
        // Ensure commutative property of multiplication holds
        result = second.times(first);
        assertThat("2 * -5 = -10", result.numerator(), is(-10));
        assertThat("3 * 7 = 21", result.denominator(), is(21));
    }

    public void testTimesNegativeNumeratorBoth() {
        Rational first = new Rational(-2, 3);
        Rational second = new Rational(-5, 7);
        
        Rational result = first.times(second);
        assertThat("-2 * -5 = 10", result.numerator(), is(10));
        assertThat("3 * 7 = 21", result.denominator(), is(21));
        
        // Ensure commutative property of multiplication holds
        result = second.times(first);
        assertThat("-2 * -5 = 10", result.numerator(), is(10));
        assertThat("3 * 7 = 21", result.denominator(), is(21));
    }

    public void testTimesWithSimplification() {
        Rational first = new Rational(4, 3);
        Rational second = new Rational(18, 3);
        
        Rational result = first.times(second);
        assertThat("4 * 18 = 72 simplifies to 8", result.numerator(), is(8));
        assertThat("3 * 3 = 9 simplifies to 1", result.denominator(), is(1));
        
        // Ensure commutative property of multiplication holds
        result = second.times(first);
        assertThat("4 * 18 = 72 simplifies to 8", result.numerator(), is(8));
        assertThat("3 * 3 = 9 simplifies to 1", result.denominator(), is(1));
    }
}
