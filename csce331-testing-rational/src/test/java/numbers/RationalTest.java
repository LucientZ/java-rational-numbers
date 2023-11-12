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
        Rational value2 = new Rational(0);
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
        Rational value2 = new Rational(0);
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

    public void testDoubleValue() {
        Rational value1 = new Rational(2583, 1);
        Rational value2 = new Rational(1, 2);
        Rational value3 = new Rational(-1, 2);
        Rational value4 = new Rational(-24352, 2);
        Rational value5 = new Rational(0, 25213);
        Rational value6 = new Rational(24351, 546);

        assertThat("Double conversion: 2583/1 -> 2583.0", value1.doubleValue(), is(2583D));
        assertThat("Double conversion: 1/2 -> 0.5", value2.doubleValue(), is(0.5D));
        assertThat("Double conversion: -1/2 -> -0.5", value3.doubleValue(), is(-0.5D));
        assertThat("Double conversion: -24352/2 = -12176/1 -> -12176.0", value4.doubleValue(), is(-12176.0D));
        assertThat("Double conversion: 0/25213 = 0/1 -> 0", value5.doubleValue(), is(0.0D));
        assertThat("Double conversion: 24351/546 = 44.5989... -> 44", value6.doubleValue(), is(44.5989010989011D));
    }

    public void testLessThanPositives() {
        Rational value1 = new Rational(1, 8);
        Rational value2 = new Rational(2, 8);

        assertThat("1/8 < 2/8 is true", value1.lessThan(value2));
        assertThat("2/8 < 1/8 is false", !value2.lessThan(value1));
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

    public void testLessThanPositiveInt(){
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 2);
        
        assertThat("50/3 = 16.66... < 50 is true", positiveValue.lessThan(50));
        assertThat("50/3 = 16.66... < 16 is false", !positiveValue.lessThan(16));
        
        assertThat("-50/2 = -25 < 50 is true", negativeValue.lessThan(50));
        assertThat("-50/2 = -25 < 16 is true", negativeValue.lessThan(16));
    }
    
    public void testLessThanNegativeInt(){
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 2);
        
        assertThat("50/3 = 16.66... < -50 is false", !positiveValue.lessThan(50));
        assertThat("50/3 = 16.66... < -16 is false", !positiveValue.lessThan(-16));

        assertThat("-50/2 = -25 < -50 is false", !negativeValue.lessThan(-50));
        assertThat("-50/2 = -25 < -16 is true", negativeValue.lessThan(-16));
    }

    public void testLessThanPositiveLong(){
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 2);
        
        assertThat("50/3 = 16.66... < 50 is true", positiveValue.lessThan(50L));
        assertThat("50/3 = 16.66... < 16 is false", !positiveValue.lessThan(16L));
        
        assertThat("-50/2 = -25 < 50 is true", negativeValue.lessThan(50L));
        assertThat("-50/2 = -25 < 16 is true", negativeValue.lessThan(16L));
    }
    
    public void testLessThanNegativeLong(){
        Rational positiveValue = new Rational(50, 3);
        Rational negativeValue = new Rational(-50, 2);
        
        assertThat("50/3 = 16.66... < -50 is false", !positiveValue.lessThan(50L));
        assertThat("50/3 = 16.66... < -16 is false", !positiveValue.lessThan(-16L));

        assertThat("-50/2 = -25 < -50 is false", !negativeValue.lessThan(-50L));
        assertThat("-50/2 = -25 < -16 is true", negativeValue.lessThan(-16L));
    }

}
