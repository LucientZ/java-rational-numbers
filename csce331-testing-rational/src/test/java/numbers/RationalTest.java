package numbers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;

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

    public void testTwoArgumentConstructorZeroSimplification() {
        Rational value1 = new Rational(0, Integer.MAX_VALUE);
        Rational value2 = new Rational(0, Integer.MIN_VALUE);

        assertThat("Numerator of value1 is 0", value1.numerator(), is(0));
        assertThat("Numerator of value2 is 0", value2.numerator(), is(0));

        assertThat("Denominator of value1 is 1", value1.denominator(), is(1));
        assertThat("Denominator of value2 is 1", value2.denominator(), is(1));
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
        Rational value = new Rational();
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

    /**
     * Test multiplying two `Rational` values
     */
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

        // Test unchanged values
        assertThat("first numerator is 2", first.numerator(), is(2));
        assertThat("first denominator is 3", first.denominator(), is(3));
        assertThat("second numerator is 5", second.numerator(), is(5));
        assertThat("second denominator is 7", second.denominator(), is(7));
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

    public void testTimesWithZeroNumerator() {
        Rational first = new Rational(0, 124);
        Rational second = new Rational(2194, -214);

        Rational result = first.times(second);
        assertThat("0 * 2194 = 0", result.numerator(), is(0));
        assertThat("124 * -214 = -26536 simplifies to 1", result.denominator(), is(1));

        // Ensure commutative property of multiplication holds
        result = second.times(first);
        assertThat("0 * 2194 = 0", result.numerator(), is(0));
        assertThat("124 * -214 = -26536 simplifies to 1", result.denominator(), is(1));
    }

    /**
     * Normally with a naiive approach, this would overflow, but with a little bit
     * of trickery in
     * Rational::times, it doesn't
     */
    public void testTimesNaiiveOverflow() {
        Rational value1 = new Rational(2147483646, 5);
        Rational value2 = new Rational(50, 49981); // Note: 2147483646 / 49981 = 42966

        Rational result = value1.times(value2);
        assertThat("2147483646/5 * 50/49981 = 429660/1", result.numerator(), is(429660));
        assertThat("2147483646/5 * 50/49981 = 429660/1", result.denominator(), is(1));
    }

    /**
     * Test dividedBy() function which returns first / second
     * (a / b) / (c / d) = (a / b) * (d / c)
     */
    public void testDividedBy() {
        Rational first = new Rational(7, 5);
        Rational second = new Rational(2, 3);

        Rational result = first.dividedBy(second);
        assertThat("Result numerator is 21: 7 * 3 = 21", result.numerator(), is(21));
        assertThat("Result denominator is 10: 5 * 2 = 10", result.denominator(), is(10));
    }

    /**
     * If the divisor has a numerator of 0, this should throw an
     * IllegalArgumentException
     */
    public void testDividedByZero() {
        Rational first = new Rational(7, 5);
        Rational second = new Rational(0, 3);

        assertThrows(IllegalArgumentException.class, () -> first.dividedBy(second));
    }

    /**
     * This should work if the first value's numerator is 0
     */
    public void testDividingZero() {
        Rational first = new Rational(0, 5);
        Rational second = new Rational(2, 3);

        Rational result = first.dividedBy(second);
        assertThat("0 * 3 = 0", result.numerator(), is(0));
        assertThat("5 * 2 = 10 simplifies to 1", result.denominator(), is(1));
    }

    /**
     * The result of the division should be simplified
     */
    public void testDividingWithSimplification() {
        Rational first = new Rational(12, 3);
        Rational second = new Rational(-2, 1);

        Rational result = first.dividedBy(second);
        assertThat("12 * 1 = 12 simplifies to -2", result.numerator(), is(-2));
        assertThat("3 * -2 = 6 simplifies to 1", result.denominator(), is(1));
    }

    /**
     * Tests if two numbers with the same denominator add correctly
     */
    public void testPlusSameDenominator() {
        Rational first = new Rational(38, 5);
        Rational second = new Rational(2, 5);

        Rational result = first.plus(second);
        assertThat("Numerator of result is 8: 38 + 2 = 40 simplifies to 8", result.numerator(), is(8));
        assertThat("Denominator of result is 1: 5 simplifies to 1", result.denominator(), is(1));

        // Ensure commutative property of addition holds
        result = first.plus(second);
        assertThat("Numerator of result is 8: 38 + 2 = 40 simplifies to 8", result.numerator(), is(8));
        assertThat("Denominator of result is 1: 5 simplifies to 1", result.denominator(), is(1));
    }

    /**
     * Before adding, each `Rational` must first have the same denominator. This
     * means the numerator
     */
    public void testPlusDifferentDenominator() {
        Rational first = new Rational(1, 2);
        Rational second = new Rational(3, 7);

        Rational result = first.plus(second);
        assertThat("Numerator of result is 13: 7 + 6 = 13", result.numerator(), is(13));
        assertThat("Denominator of result is 14 because LCM(2, 7) = 14", result.denominator(), is(14));

        // Ensure commutative property of addition holds
        result = first.plus(second);
        assertThat("Numerator of result is 13: 7 + 6 = 13", result.numerator(), is(13));
        assertThat("Denominator of result is 14 because LCM(2, 7) = 14", result.denominator(), is(14));
    }

    /**
     * Tests addition with first numerator being negative.
     */
    public void testPlusNegativeNumerator() {
        Rational first = new Rational(-2, 20);
        Rational second = new Rational(5, 20);

        Rational result = first.plus(second);
        assertThat("Numerator of result is 3", result.numerator(), is(3));
        assertThat("Denominator of result is 20", result.denominator(), is(20));

        // Ensure commutative property of addition holds
        result = second.plus(first);
        assertThat("Numerator of result is 3: -2 + 5 = 3", result.numerator(), is(3));
        assertThat("Denominator of result is 20", result.denominator(), is(20));
    }

    /**
     * With the naiive approach, this would normally overflow
     */
    public void testPlusNaiiveOverflow() {
        Rational first = new Rational(1073741823, 2);
        Rational second = new Rational(1073741825, 2);

        Rational result = first.plus(second);
        assertThat("Numerator of result is 536870912", result.numerator(), is(1073741824));
        assertThat("Denominator of result is 1", result.denominator(), is(1));

        result = second.plus(first);
        assertThat("Numerator of result is 536870912", result.numerator(), is(1073741824));
        assertThat("Denominator of result is 1", result.denominator(), is(1));
    }

    public void testPlusZero() {
        Rational first = new Rational(4, 5);
        Rational second = new Rational(0);

        Rational result = first.plus(second);
        assertThat("Numerator of result is 4", result.numerator(), is(4));
        assertThat("Denominator of result is 5", result.denominator(), is(5));

        result = second.plus(first);
        assertThat("Numerator of result is 4", result.numerator(), is(4));
        assertThat("Denominator of result is 5", result.denominator(), is(5));
    }

    /**
     * Tests basic subtraction with same denominator
     */
    public void testMinusSameDenominator() {
        Rational first = new Rational(35, 1);
        Rational second = new Rational(9, 1);

        Rational result = first.minus(second);
        assertThat("Numerator of result is 26: 35 - 9 = 26", result.numerator(), is(26));
        assertThat("Denominator of result is 1", result.denominator(), is(1));

        // Subtracts other way to ensure negative result is correct
        result = second.minus(first);
        assertThat("Numerator of result is -26: 9 - 35 = -26", result.numerator(), is(-26));
        assertThat("Denominator of result is 1", result.denominator(), is(1));
    }

    /**
     * Tests subtraction when denominators are different
     */
    public void testMinusDifferentDenominator() {
        Rational first = new Rational(1, 2);
        Rational second = new Rational(3, 7);

        Rational result = first.minus(second);
        assertThat("Numerator of result is 1", result.numerator(), is(1));
        assertThat("Denominator of result is 14", result.denominator(), is(14));

        // Subtracts other way to ensure negative result is correct
        result = second.minus(first);
        assertThat("Numerator of result is -1", result.numerator(), is(-1));
        assertThat("Denominator of result is 14", result.denominator(), is(14));
    }

    /**
     * Let's see what happens when we subtract zero or subtract from zero
     */
    public void testMinusZero() {
        Rational first = new Rational(4, 5);
        Rational second = new Rational(0);

        Rational result = first.minus(second);
        assertThat("Numerator is 4", result.numerator(), is(4));
        assertThat("Denominator is 4", result.denominator(), is(5));

        result = second.minus(first);
        assertThat("Numerator is -4", result.numerator(), is(-4));
        assertThat("Denominator is 5", result.denominator(), is(5));
    }

    /**
     * Tests power when the numerator is 1
     */
    public void testPower() {
        Rational value = new Rational(4, 12);

        Rational result = value.raisedToThePowerOf(2);
        assertThat("Numerator of result is 1: 4^2 = 16 simplifies to 1", result.numerator(), is(1));
        assertThat("Denominator of result is 9: 12^2 = 144 simplifies to 9", result.denominator(), is(9));
    }

    /**
     * This should return a `Rational` with the same values
     */
    public void testPowerOne() {
        Rational value = new Rational(1234, 4321);

        Rational result = value.raisedToThePowerOf(1);
        assertThat("Numerator is 1234", result.numerator(), is(1234));
        assertThat("Denominator of result is 4321", result.denominator(), is(4321));
    }

    /**
     * This should return the reciprocal of the `Rational`
     */
    public void testPowerNegativeOne() {
        Rational value = new Rational(1234, 4321);

        Rational result = value.raisedToThePowerOf(-1);
        assertThat("Numerator of result is 4321", result.numerator(), is(4321));
        assertThat("Denominator of result is 1234", result.denominator(), is(1234));
    }

    /**
     * Anything to the power of zero should be 1 / 1
     */
    public void testPowerZero() {
        Rational value1 = new Rational(13532, 2314);
        Rational value2 = new Rational(-24829, 242);
        Rational value3 = new Rational(4732, -235235);

        Rational result1 = value1.raisedToThePowerOf(0);
        Rational result2 = value2.raisedToThePowerOf(0);
        Rational result3 = value3.raisedToThePowerOf(0);

        assertThat("Numerator of result1 is 1", result1.numerator(), is(1));
        assertThat("Numerator of result2 is 1", result2.numerator(), is(1));
        assertThat("Numerator of result3 is 1", result3.numerator(), is(1));

        assertThat("Denominator of result1 is 1", result1.denominator(), is(1));
        assertThat("Denominator of result2 is 1", result2.denominator(), is(1));
        assertThat("Denominator of result3 is 1", result3.denominator(), is(1));
    }

    public void testPowerBig() {
        Rational value = new Rational(4, 1);

        Rational result = value.raisedToThePowerOf(10);
        assertThat("Numerator of result is 1048576: 4^10 = 1048576", result.numerator(), is(1048576));
        assertThat("Denominator of result is 1", result.denominator(), is(1));
    }

    public void testPowerBigNegative() {
        Rational value = new Rational(4, 1);

        Rational result = value.raisedToThePowerOf(-10);
        assertThat("Numerator of result is 1", result.numerator(), is(1));
        assertThat("Denominator of result is 1048576: 4^10 = 1048576", result.denominator(), is(1048576));
    }

    /**
     * Should throw when numerator is 0 and exponent is negative
     */
    public void testPowerZeroNumeratorAndNegativeExponent() {
        Rational value = new Rational(0, 1);
        assertThrows(IllegalArgumentException.class, () -> value.raisedToThePowerOf(-1));
    }

    public void testPowerNegativeNumeratorOddExponent() {
        Rational value = new Rational(-5, 7);

        Rational result = value.raisedToThePowerOf(3);
        assertThat("Numerator of result is -125: -5^3 = -125", result.numerator(), is(-125));
        assertThat("Denominator of result is 343: 7^3 = 343", result.denominator(), is(343));
    }

    public void testPowerNegativeNumeratorEvenExponent() {
        Rational value = new Rational(-5, 7);

        Rational result = value.raisedToThePowerOf(2);
        assertThat("Numerator of result is 25: -5^2 = 25", result.numerator(), is(25));
        assertThat("Denominator of resut is 49", result.denominator(), is(49));
    }

    public void testIsZero() {
        Rational value1 = new Rational(0, -2523);
        Rational value2 = new Rational();
        Rational value3 = new Rational(24, 1);

        assertThat("0 / -2523 is 0", value1.isZero());
        assertThat("0 / 1 is 0", value2.isZero());
        assertThat("24 / 1 is NOT 0", !value3.isZero());
    }

    public void testIsOne() {
        Rational value1 = new Rational(124, 124);
        Rational value2 = new Rational();
        Rational value3 = new Rational(-24, -24);
        Rational value4 = new Rational(-500, 2);
        Rational value5 = new Rational(-1, 1);

        assertThat("124 / 124 is 1", value1.isOne());
        assertThat("0 / 1 is NOT 1", !value2.isOne());
        assertThat("-24 / -24 is 1", value3.isOne());
        assertThat("-500 / 2 is NOT 1", !value4.isOne());
        assertThat("-1 / 1 is NOT 1", !value5.isOne());
    }

    public void testIsMinusOne() {
        Rational value1 = new Rational(-124, 124);
        Rational value2 = new Rational();
        Rational value3 = new Rational(24, -24);
        Rational value4 = new Rational(-500, 2);
        Rational value5 = new Rational(1, 1);

        assertThat("-124 / 124 is -1", value1.isMinusOne());
        assertThat("0 / 1 is NOT -1", !value2.isMinusOne());
        assertThat("24 / -24 is -1", value3.isMinusOne());
        assertThat("-500 / 2 is NOT -1", !value4.isMinusOne());
        assertThat("1 / 1 is NOT -1", !value5.isMinusOne());
    }

    public void testIntValue() {
        Rational value1 = new Rational(2583, 1);
        Rational value2 = new Rational(1, 2);
        Rational value3 = new Rational(-1, 2);
        Rational value4 = new Rational(-24352, 2);
        Rational value5 = new Rational(0, 25213);
        Rational value6 = new Rational(24351, 546);

        assertThat("Integer conversion: 2583/1 -> 2583", value1.intValue(), is(2583));
        assertThat("Integer conversion: 1/2 -> 0", value2.intValue(), is(0));
        assertThat("Integer conversion: -1/2 -> 0", value3.intValue(), is(0));
        assertThat("Integer conversion: -24352/2 = -12176/1 -> -12176", value4.intValue(), is(-12176));
        assertThat("Integer conversion: 0/25213 = 0/1 -> 0", value5.intValue(), is(0));
        assertThat("Integer conversion: 24351/546 = 44.5989... -> 44", value6.intValue(), is(44));
    }

    public void testLongValue() {
        Rational value1 = new Rational(2583, 1);
        Rational value2 = new Rational(1, 2);
        Rational value3 = new Rational(-1, 2);
        Rational value4 = new Rational(-24352, 2);
        Rational value5 = new Rational(0, 25213);
        Rational value6 = new Rational(24351, 546);

        assertThat("Long conversion: 2583/1 -> 2583", value1.longValue(), is(2583L));
        assertThat("Long conversion: 1/2 -> 0", value2.longValue(), is(0L));
        assertThat("Long conversion: -1/2 -> 0", value3.longValue(), is(0L));
        assertThat("Long conversion: -24352/2 = -12176/1 -> -12176", value4.longValue(), is(-12176L));
        assertThat("Long conversion: 0/25213 = 0/1 -> 0", value5.longValue(), is(0L));
        assertThat("Long conversion: 24351/546 = 44.5989... -> 44", value6.longValue(), is(44L));
    }

    public void testFloatValue() {
        Rational value1 = new Rational(2583, 1);
        Rational value2 = new Rational(1, 2);
        Rational value3 = new Rational(-1, 2);
        Rational value4 = new Rational(-24352, 2);
        Rational value5 = new Rational(0, 25213);
        Rational value6 = new Rational(24351, 546);

        assertThat("Float conversion: 2583/1 -> 2583.0", value1.floatValue(), is(2583F));
        assertThat("Float conversion: 1/2 -> 0.5", value2.floatValue(), is(0.5F));
        assertThat("Float conversion: -1/2 -> -0.5", value3.floatValue(), is(-0.5F));
        assertThat("Float conversion: -24352/2 = -12176/1 -> -12176.0", value4.floatValue(), is(-12176.0F));
        assertThat("Float conversion: 0/25213 = 0/1 -> 0", value5.floatValue(), is(0.0F));
        assertThat("Float conversion: 24351/546 = 44.5989... -> 44", value6.floatValue(), is(44.5989010989011F));
    }

    public void testDoubleValuePositive() {
        Rational value1 = new Rational(2583, 1);
        Rational value2 = new Rational(1, 2);
        Rational value3 = new Rational();
        Rational value4 = new Rational(24351, 546);

        assertThat("Double conversion: 2583/1 -> 2583.0", value1.doubleValue(), is(2583D));
        assertThat("Double conversion: 1/2 -> 0.5", value2.doubleValue(), is(0.5D));
        assertThat("Double conversion: 0/25213 = 0/1 -> 0", value3.doubleValue(), is(0.0D));
        assertThat("Double conversion: 24351/546 = 44.5989... -> 44", value4.doubleValue(), is(44.5989010989011D));
    }

    public void testDoubleValueNegative() {
        Rational value1 = new Rational(-1, 2);
        Rational value2 = new Rational(-24352, 2);

        assertThat("Double conversion: -1/2 -> -0.5", value1.doubleValue(), is(-0.5D));
        assertThat("Double conversion: -24352/2 = -12176/1 -> -12176.0", value2.doubleValue(), is(-12176.0D));
    }

    public void testLessThanPositives() {
        Rational value1 = new Rational(1, 8);
        Rational value2 = new Rational(2, 8);
        Rational value3 = new Rational(6, 2);

        assertThat("1/8 < 2/8 is true", value1.lessThan(value2));
        assertThat("2/8 < 1/8 is false", !value2.lessThan(value1));

        assertThat("2/8 < 6/2 is true", value2.lessThan(value3));
    }

    public void testLessThanNegatives() {
        Rational value1 = new Rational(-1, 8);
        Rational value2 = new Rational(-2, 8);

        assertThat("-1/8 < -2/8 is false", !value1.lessThan(value2));
        assertThat("-2/8 < -1/8 is true", value2.lessThan(value1));
    }

    public void testLessThanOneNegativeOnePositive() {
        Rational value1 = new Rational(1243, 2478);
        Rational value2 = new Rational(-1243, 2478);

        assertThat("1243/2478 < -1243/2478 is false", !value1.lessThan(value2));
        assertThat("-1243/2478 < 1243/2478 is true", value2.lessThan(value1));
    }

    public void testLessThanPositiveInt() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50/3 = 16.66... < 50 is true", positiveValue.lessThan(50), is(true));
        assertThat("50/3 = 16.66... < 15 is false", positiveValue.lessThan(15), is(false));
        assertThat("50/3 = 16.66... < 16 is false", positiveValue.lessThan(16), is(false));
        assertThat("50/3 = 16.66... < 17 is true", positiveValue.lessThan(17), is(true));

        assertThat("-50/3 = -16.66... < 50 is true", negativeValue.lessThan(50), is(true));
        assertThat("-50/3 = -16.66... < 15 is true", negativeValue.lessThan(15), is(true));
        assertThat("-50/3 = -16.66... < 16 is true", negativeValue.lessThan(16), is(true));
        assertThat("-50/3 = -16.66... < 17 is true", negativeValue.lessThan(17), is(true));
    }

    public void testLessThanNegativeInt() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50/3 = 16.66... < -50 is false", positiveValue.lessThan(-50), is(false));
        assertThat("50/3 = 16.66... < -15 is false", positiveValue.lessThan(-15), is(false));
        assertThat("50/3 = 16.66... < -16 is false", positiveValue.lessThan(-16), is(false));
        assertThat("50/3 = 16.66... < -17 is false", positiveValue.lessThan(-17), is(false));

        assertThat("-50/3 = -16.66... < -50 is false", negativeValue.lessThan(-50), is(false));
        assertThat("-50/3 = -16.66... < -15 is true", negativeValue.lessThan(-15), is(true));
        assertThat("-50/3 = -16.66... < -16 is true", negativeValue.lessThan(-16), is(true));
        assertThat("-50/3 = -16.66... < -17 is false", negativeValue.lessThan(-17), is(false));
    }

    public void testLessThanEqualInt() {
        Rational positiveValue = new Rational(50, 2);
        Rational negativeValue = new Rational(-50, 2);

        assertThat("50 / 2 = 25 < 26 is true", positiveValue.lessThan(26), is(true));
        assertThat("50 / 2 = 25 < 25 is false", positiveValue.lessThan(25), is(false));
        assertThat("50 / 2 = 25 < 24 is false", positiveValue.lessThan(24), is(false));

        assertThat("-50 / 2 = -25 < -26 is false", negativeValue.lessThan(-26), is(false));
        assertThat("-50 / 2 = -25 < -25 is false", negativeValue.lessThan(-25), is(false));
        assertThat("-50 / 2 = -25 < -24 is true", negativeValue.lessThan(-24), is(true));
    }

    public void testLessThanPositiveLong() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50/3 = 16.66... < 50 is true", positiveValue.lessThan(50L), is(true));
        assertThat("50/3 = 16.66... < 15 is false", positiveValue.lessThan(15L), is(false));
        assertThat("50/3 = 16.66... < 16 is false", positiveValue.lessThan(16L), is(false));
        assertThat("50/3 = 16.66... < 17 is true", positiveValue.lessThan(17L), is(true));

        assertThat("-50/3 = -16.66... < 50 is true", negativeValue.lessThan(50L), is(true));
        assertThat("-50/3 = -16.66... < 15 is true", negativeValue.lessThan(15L), is(true));
        assertThat("-50/3 = -16.66... < 16 is true", negativeValue.lessThan(16L), is(true));
        assertThat("-50/3 = -16.66... < 17 is true", negativeValue.lessThan(17L), is(true));
    }

    public void testLessThanNegativeLong() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50/3 = 16.66... < -50 is false", positiveValue.lessThan(-50L), is(false));
        assertThat("50/3 = 16.66... < -15 is false", positiveValue.lessThan(-15L), is(false));
        assertThat("50/3 = 16.66... < -16 is false", positiveValue.lessThan(-16L), is(false));
        assertThat("50/3 = 16.66... < -17 is false", positiveValue.lessThan(-17L), is(false));

        assertThat("-50/3 = -16.66... < -50 is false", negativeValue.lessThan(-50L), is(false));
        assertThat("-50/3 = -16.66... < -15 is true", negativeValue.lessThan(-15L), is(true));
        assertThat("-50/3 = -16.66... < -16 is true", negativeValue.lessThan(-16L), is(true));
        assertThat("-50/3 = -16.66... < -17 is false", negativeValue.lessThan(-17L), is(false));
    }

    public void testLessThanEqualLong() {
        Rational positiveValue = new Rational(50, 2);
        Rational negativeValue = new Rational(-50, 2);

        assertThat("50 / 2 = 25 < 26 is true", positiveValue.lessThan(26L), is(true));
        assertThat("50 / 2 = 25 < 25 is false", positiveValue.lessThan(25L), is(false));
        assertThat("50 / 2 = 25 < 24 is false", positiveValue.lessThan(24L), is(false));

        assertThat("-50 / 2 = -25 < -26 is false", negativeValue.lessThan(-26L), is(false));
        assertThat("-50 / 2 = -25 < -25 is false", negativeValue.lessThan(-25L), is(false));
        assertThat("-50 / 2 = -25 < -24 is true", negativeValue.lessThan(-24L), is(true));
    }

    public void testLessThanPositiveFloat() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50 / 3 = 16.66.. < 17.0 is true", positiveValue.lessThan(17.0F), is(true));
        assertThat("50 / 3 = 16.66.. < 16.667 is true", positiveValue.lessThan(16.667F), is(true));
        assertThat("50 / 3 = 16.66.. < 16.665 is false", positiveValue.lessThan(16.665F), is(false));
        assertThat("50 / 3 = 16.66.. < 16.0 is false", positiveValue.lessThan(16.0F), is(false));

        assertThat("-50 / 3 = -16.66.. < 17.0 is true", negativeValue.lessThan(17.0F), is(true));
        assertThat("-50 / 3 = -16.66.. < 16.667 is true", negativeValue.lessThan(16.667F), is(true));
        assertThat("-50 / 3 = -16.66.. < 16.665 is true", negativeValue.lessThan(16.665F), is(true));
        assertThat("-50 / 3 = -16.66.. < 16.0 is true", negativeValue.lessThan(16.0F), is(true));
    }

    public void testLessThanNegativeFloat() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50 / 3 = 16.66.. < -17.0 is false", positiveValue.lessThan(-17.0F), is(false));
        assertThat("50 / 3 = 16.66.. < -16.667 is false", positiveValue.lessThan(-16.667F), is(false));
        assertThat("50 / 3 = 16.66.. < -16.665 is false", positiveValue.lessThan(-16.665F), is(false));
        assertThat("50 / 3 = 16.66.. < -16.0 is false", positiveValue.lessThan(-16.0F), is(false));

        assertThat("-50 / 3 = -16.66.. < -17.0 is false", negativeValue.lessThan(-17.0F), is(false));
        assertThat("-50 / 3 = -16.66.. < -16.667 is false", negativeValue.lessThan(-16.667F), is(false));
        assertThat("-50 / 3 = -16.66.. < -16.665 is true", negativeValue.lessThan(-16.665F), is(true));
        assertThat("-50 / 3 = -16.66.. < -16.0 is true", negativeValue.lessThan(-16.0F), is(true));
    }

    public void testLessThanEqualFloat() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50 / 3 = 16.66.. < 50.1/3.0 is true", positiveValue.lessThan(50.1F / 3.0F), is(true));
        assertThat("50 / 3 = 16.66.. < 50.0/3.0 is false", positiveValue.lessThan(50.0F / 3.0F), is(false));
        assertThat("50 / 3 = 16.66.. < 49.9/3.0 is false", positiveValue.lessThan(49.9F / 3.0F), is(false));

        assertThat("50 / 3 = 16.66.. < -50.1/3.0 is false", negativeValue.lessThan(-50.1F / 3.0F), is(false));
        assertThat("50 / 3 = 16.66.. < -50.0/3.0 is false", negativeValue.lessThan(-50.0F / 3.0F), is(false));
        assertThat("50 / 3 = 16.66.. < -49.9/3.0 is true", negativeValue.lessThan(-49.9F / 3.0F), is(true));
    }

    public void testLessThanPositiveDouble() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50 / 3 = 16.66.. < 17.0 is true", positiveValue.lessThan(17.0D), is(true));
        assertThat("50 / 3 = 16.66.. < 16.667 is true", positiveValue.lessThan(16.667D), is(true));
        assertThat("50 / 3 = 16.66.. < 16.665 is false", positiveValue.lessThan(16.665D), is(false));
        assertThat("50 / 3 = 16.66.. < 16.0 is false", positiveValue.lessThan(16.0D), is(false));

        assertThat("-50 / 3 = -16.66.. < 17.0 is true", negativeValue.lessThan(17.0D), is(true));
        assertThat("-50 / 3 = -16.66.. < 16.667 is true", negativeValue.lessThan(16.667D), is(true));
        assertThat("-50 / 3 = -16.66.. < 16.665 is true", negativeValue.lessThan(16.665D), is(true));
        assertThat("-50 / 3 = -16.66.. < 16.0 is true", negativeValue.lessThan(16.0D), is(true));
    }

    public void testLessThanNegativeDouble() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50 / 3 = 16.66.. < -17.0 is false", positiveValue.lessThan(-17.0D), is(false));
        assertThat("50 / 3 = 16.66.. < -16.667 is false", positiveValue.lessThan(-16.667D), is(false));
        assertThat("50 / 3 = 16.66.. < -16.665 is false", positiveValue.lessThan(-16.665D), is(false));
        assertThat("50 / 3 = 16.66.. < -16.0 is false", positiveValue.lessThan(-16.0D), is(false));

        assertThat("-50 / 3 = -16.66.. < -17.0 is false", negativeValue.lessThan(-17.0D), is(false));
        assertThat("-50 / 3 = -16.66.. < -16.667 is false", negativeValue.lessThan(-16.667D), is(false));
        assertThat("-50 / 3 = -16.66.. < -16.665 is true", negativeValue.lessThan(-16.665D), is(true));
        assertThat("-50 / 3 = -16.66.. < -16.0 is true", negativeValue.lessThan(-16.0D), is(true));
    }

    public void testLessThanEqualDouble() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50 / 3 = 16.66.. < 50.1/3.0 is true", positiveValue.lessThan(50.1D / 3.0D), is(true));
        assertThat("50 / 3 = 16.66.. < 50.0/3.0 is false", positiveValue.lessThan(50.0D / 3.0D), is(false));
        assertThat("50 / 3 = 16.66.. < 49.9/3.0 is false", positiveValue.lessThan(49.9D / 3.0D), is(false));

        assertThat("-50 / 3 = 16.66.. < -50.1/3.0 is false", negativeValue.lessThan(-50.1D / 3.0D), is(false));
        assertThat("-50 / 3 = 16.66.. < -50.0/3.0 is false", negativeValue.lessThan(-50.0D / 3.0D), is(false));
        assertThat("-50 / 3 = 16.66.. < -49.9/3.0 is true", negativeValue.lessThan(-49.9D / 3.0D), is(true));
    }

    public void testGreaterThanThanPositives() {
        Rational value1 = new Rational(1, 8);
        Rational value2 = new Rational(2, 8);

        assertThat("1/8 > 2/8 is false", value1.greaterThan(value2), is(false));
        assertThat("2/8 > 1/8 is true", value2.greaterThan(value1), is(true));
    }

    public void testGreaterThanNegatives() {
        Rational value1 = new Rational(-1, 8);
        Rational value2 = new Rational(-2, 8);

        assertThat("-1/8 > -2/8 is true", value1.greaterThan(value2), is(true));
        assertThat("-2/8 > -1/8 is false", value2.greaterThan(value1), is(false));
    }

    public void testGreaterThanOneNegativeOnePositive() {
        Rational value1 = new Rational(1243, 2478);
        Rational value2 = new Rational(-1243, 2478);

        assertThat("1243/2478 > -1243/2478 is true", value1.greaterThan(value2), is(true));
        assertThat("-1243/2478 > 1243/2478 is false", value2.greaterThan(value1), is(false));
    }

    public void testGreaterThanPositiveInt() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50/3 = 16.66... > 50 is false", positiveValue.greaterThan(50), is(false));
        assertThat("50/3 = 16.66... > 15 is true", positiveValue.greaterThan(15), is(true));
        assertThat("50/3 = 16.66... > 16 is true", positiveValue.greaterThan(16), is(true));
        assertThat("50/3 = 16.66... > 17 is false", positiveValue.greaterThan(17), is(false));

        assertThat("-50/3 = -16.66... > 50 is false", negativeValue.greaterThan(50), is(false));
        assertThat("-50/3 = -16.66... > 15 is false", negativeValue.greaterThan(15), is(false));
        assertThat("-50/3 = -16.66... > 16 is false", negativeValue.greaterThan(16), is(false));
        assertThat("-50/3 = -16.66... > 17 is false", negativeValue.greaterThan(17), is(false));
    }

    public void testGreaterThanNegativeInt() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50/3 = 16.66... > -50 is true", positiveValue.greaterThan(-50), is(true));
        assertThat("50/3 = 16.66... > -15 is true", positiveValue.greaterThan(-15), is(true));
        assertThat("50/3 = 16.66... > -16 is true", positiveValue.greaterThan(-16), is(true));
        assertThat("50/3 = 16.66... > -17 is true", positiveValue.greaterThan(-17), is(true));

        assertThat("-50/3 = -16.66... > -50 is true", negativeValue.greaterThan(-50), is(true));
        assertThat("-50/3 = -16.66... > -15 is false", negativeValue.greaterThan(-15), is(false));
        assertThat("-50/3 = -16.66... > -16 is false", negativeValue.greaterThan(-16), is(false));
        assertThat("-50/3 = -16.66... > -17 is true", negativeValue.greaterThan(-17), is(true));
    }

    public void testGreaterThanEqualInt() {
        Rational positiveValue = new Rational(50, 2);
        Rational negativeValue = new Rational(-50, 2);

        assertThat("50 / 2 = 25 > 26 is false", positiveValue.greaterThan(26), is(false));
        assertThat("50 / 2 = 25 > 25 is false", positiveValue.greaterThan(25), is(false));
        assertThat("50 / 2 = 25 > 24 is true", positiveValue.greaterThan(24), is(true));

        assertThat("-50 / 2 = -25 > -26 is true", negativeValue.greaterThan(-26), is(true));
        assertThat("-50 / 2 = -25 > -25 is false", negativeValue.greaterThan(-25), is(false));
        assertThat("-50 / 2 = -25 > -24 is false", negativeValue.greaterThan(-24), is(false));
    }

    public void testGreaterThanPositiveLong() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50/3 = 16.66... > 50 is false", positiveValue.greaterThan(50L), is(false));
        assertThat("50/3 = 16.66... > 15 is true", positiveValue.greaterThan(15L), is(true));
        assertThat("50/3 = 16.66... > 16 is true", positiveValue.greaterThan(16L), is(true));
        assertThat("50/3 = 16.66... > 17 is false", positiveValue.greaterThan(17L), is(false));

        assertThat("-50/3 = -16.66... > 50 is false", negativeValue.greaterThan(50L), is(false));
        assertThat("-50/3 = -16.66... > 15 is false", negativeValue.greaterThan(15L), is(false));
        assertThat("-50/3 = -16.66... > 16 is false", negativeValue.greaterThan(16L), is(false));
        assertThat("-50/3 = -16.66... > 17 is false", negativeValue.greaterThan(17L), is(false));
    }

    public void testGreaterThanNegativeLong() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50/3 = 16.66... > -50 is true", positiveValue.greaterThan(-50L), is(true));
        assertThat("50/3 = 16.66... > -15 is true", positiveValue.greaterThan(-15L), is(true));
        assertThat("50/3 = 16.66... > -16 is true", positiveValue.greaterThan(-16L), is(true));
        assertThat("50/3 = 16.66... > -17 is true", positiveValue.greaterThan(-17L), is(true));

        assertThat("-50/3 = -16.66... > -50 is true", negativeValue.greaterThan(-50L), is(true));
        assertThat("-50/3 = -16.66... > -15 is false", negativeValue.greaterThan(-15L), is(false));
        assertThat("-50/3 = -16.66... > -16 is false", negativeValue.greaterThan(-16L), is(false));
        assertThat("-50/3 = -16.66... > -17 is true", negativeValue.greaterThan(-17L), is(true));
    }

    public void testGreaterThanEqualLong() {
        Rational positiveValue = new Rational(50, 2);
        Rational negativeValue = new Rational(-50, 2);

        assertThat("50 / 2 = 25 > 26 is false", positiveValue.greaterThan(26L), is(false));
        assertThat("50 / 2 = 25 > 25 is false", positiveValue.greaterThan(25L), is(false));
        assertThat("50 / 2 = 25 > 24 is true", positiveValue.greaterThan(24L), is(true));

        assertThat("-50 / 2 = -25 > -26 is true", negativeValue.greaterThan(-26L), is(true));
        assertThat("-50 / 2 = -25 > -25 is false", negativeValue.greaterThan(-25L), is(false));
        assertThat("-50 / 2 = -25 > -24 is false", negativeValue.greaterThan(-24L), is(false));
    }

    public void testGreaterThanPositiveFloat() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50 / 3 = 16.66.. > 17.0 is false", positiveValue.greaterThan(17.0F), is(false));
        assertThat("50 / 3 = 16.66.. > 16.667 is false", positiveValue.greaterThan(16.667F), is(false));
        assertThat("50 / 3 = 16.66.. > 16.665 is true", positiveValue.greaterThan(16.665F), is(true));
        assertThat("50 / 3 = 16.66.. > 16.0 is true", positiveValue.greaterThan(16.0F), is(true));

        assertThat("-50 / 3 = -16.66.. > 17.0 is false", negativeValue.greaterThan(17.0F), is(false));
        assertThat("-50 / 3 = -16.66.. > 16.667 is false", negativeValue.greaterThan(16.667F), is(false));
        assertThat("-50 / 3 = -16.66.. > 16.665 is false", negativeValue.greaterThan(16.665F), is(false));
        assertThat("-50 / 3 = -16.66.. > 16.0 is false", negativeValue.greaterThan(16.0F), is(false));
    }

    public void testGreaterThanNegativeFloat() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50 / 3 = 16.66.. > -17.0 is true", positiveValue.greaterThan(-17.0F), is(true));
        assertThat("50 / 3 = 16.66.. > -16.667 is true", positiveValue.greaterThan(-16.667F), is(true));
        assertThat("50 / 3 = 16.66.. > -16.665 is true", positiveValue.greaterThan(-16.665F), is(true));
        assertThat("50 / 3 = 16.66.. > -16.0 is true", positiveValue.greaterThan(-16.0F), is(true));

        assertThat("-50 / 3 = -16.66.. > -17.0 is true", negativeValue.greaterThan(-17.0F), is(true));
        assertThat("-50 / 3 = -16.66.. > -16.667 is true", negativeValue.greaterThan(-16.667F), is(true));
        assertThat("-50 / 3 = -16.66.. > -16.665 is false", negativeValue.greaterThan(-16.665F), is(false));
        assertThat("-50 / 3 = -16.66.. > -16.0 is false", negativeValue.greaterThan(-16.0F), is(false));
    }

    public void testGreaterThanEqualFloat() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50 / 3 = 16.66.. > 50.1/3.0 is false", positiveValue.greaterThan(50.1F / 3.0F), is(false));
        assertThat("50 / 3 = 16.66.. > 50.0/3.0 is false", positiveValue.greaterThan(50.0F / 3.0F), is(false));
        assertThat("50 / 3 = 16.66.. > 49.9/3.0 is true", positiveValue.greaterThan(49.9F / 3.0F), is(true));

        assertThat("50 / 3 = 16.66.. > -50.1/3.0 is true", negativeValue.greaterThan(-50.1F / 3.0F), is(true));
        assertThat("50 / 3 = 16.66.. > -50.0/3.0 is false", negativeValue.greaterThan(-50.0F / 3.0F), is(false));
        assertThat("50 / 3 = 16.66.. > -49.9/3.0 is false", negativeValue.greaterThan(-49.9F / 3.0F), is(false));
    }

    public void testGreaterThanPositiveDouble() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50 / 3 = 16.66.. > 17.0 is false", positiveValue.greaterThan(17.0D), is(false));
        assertThat("50 / 3 = 16.66.. > 16.667 is false", positiveValue.greaterThan(16.667D), is(false));
        assertThat("50 / 3 = 16.66.. > 16.665 is true", positiveValue.greaterThan(16.665D), is(true));
        assertThat("50 / 3 = 16.66.. > 16.0 is true", positiveValue.greaterThan(16.0D), is(true));

        assertThat("-50 / 3 = -16.66.. > 17.0 is false", negativeValue.greaterThan(17.0D), is(false));
        assertThat("-50 / 3 = -16.66.. > 16.667 is false", negativeValue.greaterThan(16.667D), is(false));
        assertThat("-50 / 3 = -16.66.. > 16.665 is false", negativeValue.greaterThan(16.665D), is(false));
        assertThat("-50 / 3 = -16.66.. > 16.0 is false", negativeValue.greaterThan(16.0D), is(false));
    }

    public void testGreaterThanNegativeDouble() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50 / 3 = 16.66.. > -17.0 is true", positiveValue.greaterThan(-17.0D), is(true));
        assertThat("50 / 3 = 16.66.. > -16.667 is true", positiveValue.greaterThan(-16.667D), is(true));
        assertThat("50 / 3 = 16.66.. > -16.665 is true", positiveValue.greaterThan(-16.665D), is(true));
        assertThat("50 / 3 = 16.66.. > -16.0 is true", positiveValue.greaterThan(-16.0D), is(true));

        assertThat("-50 / 3 = -16.66.. > -17.0 is true", negativeValue.greaterThan(-17.0D), is(true));
        assertThat("-50 / 3 = -16.66.. > -16.667 is true", negativeValue.greaterThan(-16.667D), is(true));
        assertThat("-50 / 3 = -16.66.. > -16.665 is false", negativeValue.greaterThan(-16.665D), is(false));
        assertThat("-50 / 3 = -16.66.. > -16.0 is false", negativeValue.greaterThan(-16.0D), is(false));
    }

    public void testGreaterThanEqualDouble() {
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 3);

        assertThat("50 / 3 = 16.66.. > 50.1/3.0 is false", positiveValue.greaterThan(50.1D / 3.0D), is(false));
        assertThat("50 / 3 = 16.66.. > 50.0/3.0 is false", positiveValue.greaterThan(50.0D / 3.0D), is(false));
        assertThat("50 / 3 = 16.66.. > 49.9/3.0 is true", positiveValue.greaterThan(49.9D / 3.0D), is(true));

        assertThat("-50 / 3 = 16.66.. > -50.1/3.0 is true", negativeValue.greaterThan(-50.1D / 3.0D), is(true));
        assertThat("-50 / 3 = 16.66.. > -50.0/3.0 is false", negativeValue.greaterThan(-50.0D / 3.0D), is(false));
        assertThat("-50 / 3 = 16.66.. > -49.9/3.0 is false", negativeValue.greaterThan(-49.9D / 3.0D), is(false));
    }

    public void testCompareToGreater() {
        Rational positiveValue = new Rational(2, 3);
        Rational negativeValue = new Rational(-2, 3);

        Rational greaterRational = new Rational(1, 1);
        int greaterInt = 1;
        long greaterLong = 1L;
        float greaterFloat = 1.0F;
        double greaterDouble = 1.0D;

        assertThat("2/3 < 1", positiveValue.compareTo(greaterRational), is(-1));
        assertThat("2/3 < 1", positiveValue.compareTo(greaterInt), is(-1));
        assertThat("2/3 < 1", positiveValue.compareTo(greaterLong), is(-1));
        assertThat("2/3 < 1", positiveValue.compareTo(greaterFloat), is(-1));
        assertThat("2/3 < 1", positiveValue.compareTo(greaterDouble), is(-1));

        assertThat("-2/3 < 1", negativeValue.compareTo(greaterRational), is(-1));
        assertThat("-2/3 < 1", negativeValue.compareTo(greaterInt), is(-1));
        assertThat("-2/3 < 1", negativeValue.compareTo(greaterLong), is(-1));
        assertThat("-2/3 < 1", negativeValue.compareTo(greaterFloat), is(-1));
        assertThat("-2/3 < 1", negativeValue.compareTo(greaterDouble), is(-1));
    }

    public void testCompareToLess() {
        Rational positiveValue = new Rational(2, 3);
        Rational negativeValue = new Rational(-2, 3);

        Rational greaterRational = new Rational(-1, 1);
        int greaterInt = -1;
        long greaterLong = -1L;
        float greaterFloat = -1.0F;
        double greaterDouble = -1.0D;

        assertThat("Rational: 2/3 > 1", positiveValue.compareTo(greaterRational), is(1));
        assertThat("Integer: 2/3 > 1", positiveValue.compareTo(greaterInt), is(1));
        assertThat("Long: 2/3 > 1", positiveValue.compareTo(greaterLong), is(1));
        assertThat("Float: 2/3 > 1", positiveValue.compareTo(greaterFloat), is(1));
        assertThat("Double: 2/3 > 1", positiveValue.compareTo(greaterDouble), is(1));

        assertThat("Rational: -2/3 > 1", negativeValue.compareTo(greaterRational), is(1));
        assertThat("Integer: -2/3 > 1", negativeValue.compareTo(greaterInt), is(1));
        assertThat("Long: -2/3 > 1", negativeValue.compareTo(greaterLong), is(1));
        assertThat("Float: -2/3 > 1", negativeValue.compareTo(greaterFloat), is(1));
        assertThat("Double: -2/3 > 1", negativeValue.compareTo(greaterDouble), is(1));
    }

    public void testCompareToEqual() {
        Rational positiveValue = new Rational(78, 1);
        Rational negativeValue = new Rational(-78, 1);

        // Positive values
        Rational equalPositiveRational = new Rational(156, 2);
        int equalPositiveInt = 78;
        long equalPositiveLong = 78L;
        float equalPositiveFloat = 78.0F;
        double equalPositiveDouble = 78.0D;

        assertThat("78/1 = 78", positiveValue.compareTo(equalPositiveRational), is(0));
        assertThat("78/1 = 78", positiveValue.compareTo(equalPositiveInt), is(0));
        assertThat("78/1 = 78", positiveValue.compareTo(equalPositiveLong), is(0));
        assertThat("78/1 = 78", positiveValue.compareTo(equalPositiveFloat), is(0));
        assertThat("78/1 = 78", positiveValue.compareTo(equalPositiveDouble), is(0));

        // Negative values
        Rational equalNegativeRational = new Rational(-156, 2);
        int equalNegativeInt = -78;
        long equalNegativeLong = -78L;
        float equalNegativeFloat = -78.0F;
        double equalNegativeDouble = -78.0D;

        assertThat("-78/1 = -78", negativeValue.compareTo(equalNegativeRational), is(0));
        assertThat("-78/1 = -78", negativeValue.compareTo(equalNegativeInt), is(0));
        assertThat("-78/1 = -78", negativeValue.compareTo(equalNegativeLong), is(0));
        assertThat("-78/1 = -78", negativeValue.compareTo(equalNegativeFloat), is(0));
        assertThat("-78/1 = -78", negativeValue.compareTo(equalNegativeDouble), is(0));
    }

    public void testToStringWholeNumbers() {
        Rational value1 = new Rational(26, 1);
        Rational value2 = new Rational(-26, 1);
        Rational value3 = new Rational();
        Rational value4 = new Rational(124857);

        assertThat("26/1 -> 26", value1.toString(), is("26"));
        assertThat("-26/1 -> 26", value2.toString(), is("-26"));
        assertThat("0/256 -> 0", value3.toString(), is("0"));
        assertThat("124857/1 -> 124857", value4.toString(), is("124857"));
    }

    public void testToStringPositiveFraction() {
        Rational value1 = new Rational(1, 2);
        Rational value2 = new Rational(2, 3);
        Rational value3 = new Rational(1543243, 29);
        Rational value4 = new Rational(-2, -29875333);

        assertThat("1/2 -> 1/2", value1.toString(), is("1/2"));
        assertThat("2/3 -> 2/3", value2.toString(), is("2/3"));
        assertThat("1543243/29 -> 1543243/29", value3.toString(), is("1543243/29"));
        assertThat("-2/-29875333 -> 2/29875333", value4.toString(), is("2/29875333"));
    }

    public void testToStringNegativeFraction() {
        Rational value1 = new Rational(-1, 2);
        Rational value2 = new Rational(2, -3);
        Rational value3 = new Rational(1543243, -29);
        Rational value4 = new Rational(-2, 29875333);

        assertThat("-1/2 -> -1/2", value1.toString(), is("-1/2"));
        assertThat("2/-3 -> -2/3", value2.toString(), is("-2/3"));
        assertThat("1543243/-29 -> -1543243/29", value3.toString(), is("-1543243/29"));
        assertThat("-2/29875333 -> -2/29875333", value4.toString(), is("-2/29875333"));
    }

    public void testEqualsEqualValues() {
        Rational wholeValue = new Rational(-358729);
        Rational wholeValueCopy = new Rational(wholeValue);
        assertThat("Whole: Self == Self", wholeValue.equals(wholeValueCopy), is(true));
        assertThat("Whole: Self == Copy", wholeValue.equals(wholeValueCopy), is(true));
        assertThat("Whole: Integer: -358729 == -358729", wholeValue.equals(-358729), is(true));
        assertThat("Whole: Long: -358729 == -358729", wholeValue.equals(-358729L), is(true));
        assertThat("Whole: Float: -358729 == -358729", wholeValue.equals(-358729F), is(true));
        assertThat("Whole: Double: -358729 == -358729", wholeValue.equals(-358729D), is(true));

        Rational fractionalValue = new Rational(38497, 2479);
        Rational fractionalValueCopy = new Rational(fractionalValue);
        assertThat("Fractional: Self == Self", fractionalValue.equals(fractionalValue), is(true));
        assertThat("Fractional: Self == Copy", fractionalValue.equals(fractionalValueCopy), is(true));
        assertThat("Fractional: Float: 38497/2479 == 38497/2479", fractionalValue.equals(38497F / 2479F), is(true));
        assertThat("Fractional: Double: 38497/2479 == 38497/2479", fractionalValue.equals(38497D / 2479D), is(true));
    }

    public void testEqualsUnequalValues() {
        Rational unequalRational = new Rational(125837, 32507343);

        Rational wholeValue = new Rational(-358729);
        assertThat("Whole: 'Rational' is not equal to `String`", wholeValue.equals("ascbhjd"), is(false));
        assertThat("Whole: -358729/1 != 125837/32507343", wholeValue.equals(unequalRational), is(false));
        assertThat("Whole: -358729/1 != 3", wholeValue.equals(3), is(false));

        Rational fractionalValue = new Rational(38497, 2479);
        assertThat("Fractional: 'Rational' is not equal to `String`", fractionalValue.equals("ascbhjd"), is(false));
        assertThat("Fractional: 38497/2479 != 125837/32507343", fractionalValue.equals(unequalRational), is(false));
        assertThat("Integer: 38497/2479 != 38497/2479", fractionalValue.equals(38497 / 2479), is(false));
        assertThat("Long: 38497/2479 != 38497/2479", fractionalValue.equals(38497L / 2479L), is(false));
        assertThat("Long: 38497/2479 != 0.5", fractionalValue.equals(0.5), is(false));
        assertThat("Fractional: Float: 38497/2479 != -38497/2479", fractionalValue.equals(-38497F / 2479F), is(false));
    }

    /////////////////////////////////////////
    // NULL TESTS
    /////////////////////////////////////////

    public void testConstructorNull() {
        assertThrows(NullPointerException.class, () -> {
            new Rational(null);
        });
    }

    public void testTimesNull() {
        Rational value = new Rational(1234);
        assertThrows(NullPointerException.class, () -> value.times(null));
    }

    public void testDividedByNull() {
        Rational value = new Rational(1234);
        assertThrows(NullPointerException.class, () -> value.dividedBy(null));
    }

    public void testPlusNull() {
        Rational value = new Rational(1234);
        assertThrows(NullPointerException.class, () -> value.plus(null));
    }

    public void testMinusNull() {
        Rational value = new Rational(1234);
        assertThrows(NullPointerException.class, () -> value.minus(null));
    }

    public void testLessThanNull() {
        Rational value = new Rational(1234);
        assertThat("value is not less than null", value.lessThan((Rational) null), is(false));
        assertThat("value is not less than null", value.lessThan((Number) null), is(false));
    }

    public void testGreaterThanNull() {
        Rational value = new Rational(1234);
        assertThat("value is not greater than null", value.greaterThan((Rational) null), is(false));
        assertThat("value is not greater than null", value.greaterThan((Number) null), is(false));
    }

    public void testCompareToNull() {
        Rational value = new Rational(1234);
        assertThat("compareTo(null) returns not 0", value.compareTo(null), is(-1));
    }

    public void testEqualsNull() {
        Rational value = new Rational(1234);
        assertThat("value is not equal to null", value.equals(null), is(false));
    }

    public void testCompareToLongUpperLimit() {
        Rational value = new Rational(Integer.MAX_VALUE);

        assertThat("2147483647 < 9223372036854775807", value.lessThan(Long.MAX_VALUE), is(true));
        assertThat("2147483647 < 9223372036854775807", value.greaterThan(Long.MAX_VALUE), is(false));
        assertThat("2147483647 < 9223372036854775807", value.compareTo(Long.MAX_VALUE), is(-1));
    }

    public void testCompareToLongLowerLimit() {
        Rational value = new Rational(Integer.MIN_VALUE);

        assertThat("2147483647 > -9223372036854775808", value.lessThan(Long.MIN_VALUE), is(false));
        assertThat("2147483647 > -9223372036854775808", value.greaterThan(Long.MIN_VALUE), is(true));
        assertThat("2147483647 > -9223372036854775808", value.compareTo(Long.MIN_VALUE), is(1));
    }

    public void testCompareToIntegerLimits() {
        Rational maxValue = new Rational(Integer.MAX_VALUE);
        Rational minValue = new Rational(Integer.MIN_VALUE);

        assertThat("MAX_INT < MIN_INT", maxValue.lessThan(minValue), is(false));
        assertThat("MAX_INT > MIN_INT", maxValue.greaterThan(minValue), is(true));
        assertThat("MAX_INT > MIN_INT", maxValue.compareTo(minValue), is(1));
        assertThat("MAX_INT > MIN_INT", minValue.lessThan(maxValue), is(true));
        assertThat("MAX_INT < MIN_INT", minValue.greaterThan(maxValue), is(false));
        assertThat("MAX_INT > MIN_INT", minValue.compareTo(maxValue), is(-1));
    }

    public void testHashCode() {
        Rational value = new Rational(1, 2);
        assertThat("Hashcode comes from string of numerator and denominator", value.hashCode(), is("1/2".hashCode()));
        value = new Rational(2, 1);
        assertThat("Hashcode comes from string of numerator and denominator", value.hashCode(), is("2".hashCode()));
    }

    public void testComparisonCloseValues() {
        Rational value = new Rational(1);

        assertThat("0.99999999999998 == 1", value.compareTo(0.99999999999998F), is(0));
        assertThat("0.99999999999998 == 1", value.compareTo(0.99999999999998D), is(0));
        assertThat("1.00000000000009 == 1", value.compareTo(1.00000000000009F), is(0));
        assertThat("1.00000000000009 == 1", value.compareTo(1.00000000000009D), is(0));
    }

    public void testNormalUsage1() {
        Rational value1 = new Rational(1, 10);
        Rational value2 = new Rational(2, 10);

        Rational result = value1.plus(value2);

        float resultFloat = 0.1F + 0.2F;
        double resultDouble = 0.1 + 0.2;

        assertThat("1/10 + 2/10 == 0.1+0.2", result.equals(resultFloat));
        assertThat("1/10 + 2/10 == 0.1+0.2", result.equals(0.30000000000000004));
        assertThat("1/10 + 2/10 == 0.1+0.2", result.equals(resultDouble));
        assertThat("Rational != ", !result.equals(0.1 + 0.203));

    }

    public void testNormalUsage2() {
        Rational value1 = new Rational(1, 10);

        Rational result = value1.minus(new Rational(3, 10));
        assertThat("1/10 - 3/10 = -0.19999999999999998", result.compareTo(-0.19999999999999998), is(0));
        assertThat("1/10 - 3/10 = -0.19999999999999998", result.lessThan(-0.19999999999999998F), is(false));
        assertThat("1/10 - 3/10 = -0.19999999999999998", result.greaterThan(-0.19999999999999998F), is(false));
    }

    public void testNormalUsage3() {
        Rational value1 = new Rational(1, 10);
        Rational value2 = new Rational(-1, 10);

        assertThat("value1 - value1 = 0", value1.plus(value1.opposite()).compareTo(0), is(0));
        assertThat("value1 - (-value1) = 2/10", value1.minus(value1.opposite()).compareTo((double) 2 / 10), is(0));
        assertThat("value1*value1^-1 = 1", value1.times(value1.raisedToThePowerOf(-1)).compareTo(1), is(0));
        assertThat("value1*value1^-1 = 1", value1.times(value1.reciprocal()).isOne(), is(true));
        assertThat("value1*value1^-2 != 1", value1.times(value1.raisedToThePowerOf(-2)).equals(1), is(false));

        assertThat("value2 - value2 = 0", value2.plus(value2.opposite()).compareTo(0), is(0));
        assertThat("value2 - (-value2) = 0", value2.minus(value2.opposite()).compareTo((double) -2 / 10), is(0));
        assertThat("value2*value2^-1 = 1", value2.times(value2.raisedToThePowerOf(-1)).compareTo(1), is(0));
        assertThat("value2*value2^-1 = 1", value2.times(value2.reciprocal()).isOne(), is(true));
        assertThat("value2*value2^-2 != 1", value2.times(value2.raisedToThePowerOf(-2)).equals(1), is(false));
    }

    public void testNormalUsage4() {
        assertThat("0 * number = 0", (new Rational(0, 1)).dividedBy(new Rational(4325341, 1)).equals(0), is(true));
        assertThat("", (new Rational(1)).dividedBy(new Rational(1, 3).reciprocal()).equals(3000001F / 9000000F),
                is(true));
        assertThat("Subtracting zero doesn't change the number",
                (new Rational(21523, 2314)).minus(new Rational()).equals(new Rational(21523, 2314)), is(true));
    }

    public void testNormalUsage5() {
        Rational value = new Rational(2, -4);

        for (int i = 0; i < 31; i++) {
            if (i % 2 == 0) {
                assertThat("value^" + i + " numerator is 1", value.raisedToThePowerOf(i).numerator(), is(1));
                assertThat("value^" + i + " denominator is correct", value.raisedToThePowerOf(i).denominator(),
                        is((int) Math.pow(2, i)));
            } else {
                assertThat("value^" + i + " numerator is -1", value.raisedToThePowerOf(i).numerator(), is(-1));
                assertThat("value^" + i + " denominator is correct", value.raisedToThePowerOf(i).denominator(),
                        is((int) Math.pow(2, i)));
            }
        }
    }

    public void testNormalUsage6() {
        Rational value = new Rational(0, 124);

        assertThat("value^0 = 1", value.raisedToThePowerOf(0).compareTo(1), is(0));
        for (int i = 1; i < 100; i++) {
            assertThat("value^" + i + " = 0", value.raisedToThePowerOf(i).compareTo(0), is(0));
        }
    }

    public void testNormalUsage7() {
        assertThat("Large String", new Rational(Integer.MAX_VALUE, Integer.MAX_VALUE - 1).toString(),
                is("2147483647/2147483646"));
        assertThat("Large String", new Rational(Integer.MIN_VALUE, 3).toString(), is("-2147483648/3"));
    }

    public void testNormalUsage8() {
        Rational value1 = new Rational(-1, 1);
        Rational value2 = new Rational(-1, 2);
        assertThat("-1 != -1/2", value1.equals(value2), is(false));
    }

    public void testNormalUsage9() {
        Rational value1 = new Rational(new Rational());
        Rational value2 = new Rational(new Rational(0));
        Rational value3 = new Rational(new Rational(0, -1));
        Rational value4 = new Rational(new Rational(1));
        Rational value5 = new Rational(new Rational(-1));
        Rational value6 = new Rational(new Rational(4, 11));
        Rational value7 = new Rational(new Rational(4, -11));

        assertThat(value1.numerator(), is(0));
        assertThat(value1.denominator(), is(1));
        assertThat(value2.numerator(), is(0));
        assertThat(value2.denominator(), is(1));
        assertThat(value3.numerator(), is(0));
        assertThat(value3.denominator(), is(1));
        assertThat(value4.numerator(), is(1));
        assertThat(value4.denominator(), is(1));
        assertThat(value5.numerator(), is(-1));
        assertThat(value5.denominator(), is(1));
        assertThat(value6.numerator(), is(4));
        assertThat(value6.denominator(), is(11));
        assertThat(value7.numerator(), is(-4));
        assertThat(value7.denominator(), is(11));
    }

    public void testNormalUsage10() {
        assertThat(new Rational(1, 2).equals(new Rational(1, 2)), is(true)); // trivially true
        assertThat(new Rational(2, 3).equals(new Rational(1, 2)), is(false)); // trivially false
        assertThat(new Rational(1, 2).equals(new Rational(2, 4)), is(true)); // true after reduction
    }

    public void testComparisonToNaN() {
        Rational value = new Rational(1, 2);

        assertThat("1/2 isn't equal to Double.NaN", value.equals(Double.NaN), is(false));
        assertThat("1/2 isn't less than Double.NaN", value.lessThan(Double.NaN), is(false));
        assertThat("1/2 isn't greater than Double.NaN", value.greaterThan(Double.NaN), is(false));
        assertThat("compareTo(Double.NaN) = -1", value.compareTo(Double.NaN), is(-1));

        assertThat("1/2 isn't equal to Float.NaN", value.equals(Float.NaN), is(false));
        assertThat("1/2 isn't less than Float.NaN", value.lessThan(Float.NaN), is(false));
        assertThat("1/2 isn't greater than Float.NaN", value.greaterThan(Float.NaN), is(false));
        assertThat("compareTo(Double.NaN) = -1", value.compareTo(Float.NaN), is(-1));
    }

    public void testComparisonInfinity() {
        Rational value1 = new Rational(Integer.MAX_VALUE);
        Rational value2 = new Rational(Integer.MIN_VALUE);
        Rational value3 = new Rational(1, Integer.MIN_VALUE);
        Rational value4 = new Rational(1, Integer.MIN_VALUE);

        assertThat("finite int < (float) infinity", value1.compareTo(Float.POSITIVE_INFINITY), is(-1));
        assertThat("finite int > (float) -infinity", value1.compareTo(Float.NEGATIVE_INFINITY), is(1));
        assertThat("finite int < (double) infinity", value1.compareTo(Double.POSITIVE_INFINITY), is(-1));
        assertThat("finite int < (double) infinity", value1.compareTo(Double.NEGATIVE_INFINITY), is(1));

        assertThat("finite int < (float) infinity", value2.compareTo(Float.POSITIVE_INFINITY), is(-1));
        assertThat("finite int > (float) -infinity", value2.compareTo(Float.NEGATIVE_INFINITY), is(1));
        assertThat("finite int < (double) infinity", value2.compareTo(Double.POSITIVE_INFINITY), is(-1));
        assertThat("finite int < (double) infinity", value2.compareTo(Double.NEGATIVE_INFINITY), is(1));

        assertThat("finite int < (float) infinity", value3.compareTo(Float.POSITIVE_INFINITY), is(-1));
        assertThat("finite int > (float) -infinity", value3.compareTo(Float.NEGATIVE_INFINITY), is(1));
        assertThat("finite int < (double) infinity", value3.compareTo(Double.POSITIVE_INFINITY), is(-1));
        assertThat("finite int < (double) infinity", value3.compareTo(Double.NEGATIVE_INFINITY), is(1));

        assertThat("finite int < (float) infinity", value4.compareTo(Float.POSITIVE_INFINITY), is(-1));
        assertThat("finite int > (float) -infinity", value4.compareTo(Float.NEGATIVE_INFINITY), is(1));
        assertThat("finite int < (double) infinity", value4.compareTo(Double.POSITIVE_INFINITY), is(-1));
        assertThat("finite int < (double) infinity", value4.compareTo(Double.NEGATIVE_INFINITY), is(1));
    }

    public void testArrayList() {
        ArrayList<Rational> list = new ArrayList<Rational>();

        Rational value1 = new Rational(-500, 1);
        Rational value2 = new Rational(-1, 52);
        Rational value3 = new Rational(1, 5);
        Rational value4 = new Rational(5, 5);

        list.add(value3);
        list.add(value2);
        list.add(value1);
        list.add(value4);

        Collections.sort(list);

        assertThat("value 1 is first", list.get(0), is(value1));
        assertThat("value 2 is second", list.get(1), is(value2));
        assertThat("value 3 is third", list.get(2), is(value3));
        assertThat("value 4 is fourth", list.get(3), is(value4));
    }

    public void testComparisonWithZeroSelf() {
        Rational value = new Rational(0);
        assertThat(value.equals(value), is(true));
        assertThat(value.compareTo(value), is(0));
    }

    public void testArithmeticZeroToZero() {
        Rational value = new Rational();

        assertThat(value.lessThan(value), is(false));
        assertThat(value.greaterThan(value), is(false));
        assertThat(value.times(value).compareTo(0), is(0));
        assertThat(value.plus(new Rational()).intValue(), is(0));
        assertThat(value.minus(new Rational()).intValue(), is(0));
        assertThat(value.times(new Rational()).intValue(), is(0));
        assertThat(value.dividedBy(new Rational(1)).intValue(), is(0));
    }

    public void testArithmeticWithZero() {
        Rational positiveValue = new Rational(1234);
        Rational negativeValue = new Rational(-1234, 98765);

        assertThat(positiveValue.plus(new Rational()).equals(positiveValue), is(true));
        assertThat(positiveValue.minus(new Rational()).equals(positiveValue), is(true));
        assertThat(positiveValue.times(new Rational()).equals(new Rational()), is(true));

        assertThat(new Rational().plus(positiveValue).equals(positiveValue), is(true));
        assertThat(new Rational().minus(positiveValue).equals(positiveValue.opposite()), is(true));
        assertThat(new Rational().times(positiveValue).equals(new Rational()), is(true));

        assertThat(negativeValue.plus(new Rational()).equals(negativeValue), is(true));
        assertThat(negativeValue.minus(new Rational()).equals(negativeValue), is(true));
        assertThat(negativeValue.times(new Rational()).equals(new Rational()), is(true));

        assertThat(new Rational().plus(negativeValue).equals(negativeValue), is(true));
        assertThat(new Rational().minus(negativeValue).equals(negativeValue.opposite()), is(true));
        assertThat(new Rational().times(negativeValue).equals(new Rational()), is(true));
    }

    public void testLargeNumbers() {
        Rational largeNumber = new Rational(Integer.MAX_VALUE, Integer.MAX_VALUE - 1);
        assertThat(largeNumber.numerator(), is(Integer.MAX_VALUE));
        assertThat(largeNumber.denominator(), is(Integer.MAX_VALUE - 1));
    }

    public void testClone() {
        Rational value = new Rational(6, 2);
        Rational value2 = value.clone();
        assertThat("Clone equals original", value.equals(value2), is(true));
        assertThat("value2 is not value", value == value2, is(false));
    }

    public void testInsanity(){
        Rational value = new Rational(-5214, 0x121243);

        value.byteValue();
        value.clone();
        value.compareTo(value);
        value.denominator();
        value.dividedBy(new Rational(1));
        value.doubleValue();
        value.equals(value);
        value.floatValue();
        value.getClass();
        value.greaterThan(1);
        value.greaterThan(value);
        value.hashCode();
        value.intValue();
        value.isMinusOne();
        value.isOne();
        value.isZero();
        value.lessThan(value);
        value.lessThan(1);
        value.longValue();
        value.minus(value);
        value.numerator();
        value.opposite();
        value.plus(value);
        value.raisedToThePowerOf(0);
        value.reciprocal();
        value.shortValue();
        value.times(value);
        value.toString();
    }
}
