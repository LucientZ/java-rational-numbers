package numbers;

import java.math.BigDecimal;

/**
 * This class implements a rational number. That is, a value with an numerator
 * and denominator in which both values are integers.
 */
public class Rational extends Number implements Comparable<Number> {
    private int _numerator = 0;
    private int _denominator = 1;
    private final static float FLOAT_PRECISION = Math.ulp(1.0F);
    private final static double DOUBLE_PRECISION = Math.ulp(1.0);

    /**
     * Default constructor. Creates a new `Rational` with a value 0 / 1
     */
    public Rational() {
        this(0);
    }

    /**
     * Constructor which creates a `Rational` given an integer
     * 
     * @param numerator Integer value which to reflect in the `Rational`
     */
    public Rational(int numerator) {
        this(numerator, 1);
    }

    /**
     * Constructor which creates a `Rational` given a specified numerator and
     * denominator
     * 
     * @param numerator   Numerator of `Rational`
     * @param denominator Denominator of `Rational`
     * @throws IllegalArgumentException when denominator is set to 0
     */
    public Rational(int numerator, int denominator) throws IllegalArgumentException {
        super();
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator must not be 0");
        }

        this._numerator = numerator;
        this._denominator = denominator;
        this.simplify();
    }

    /**
     * Constructor which copies the contents of another `Rational`
     * 
     * @param original `Rational` to be copied
     */
    public Rational(Rational original) {
        this(original.numerator(), original.denominator());
    }

    /**
     * Accessor method for private _numerator member
     * 
     * @return this._numerator
     */
    public int numerator() {
        return this._numerator;
    }

    /**
     * Accessor method for private _denominator member
     * 
     * @return this._denominator
     */
    public int denominator() {
        return this._denominator;
    }

    /**
     * Creates a new `Rational` which is the additive inverse of this object. An
     * additive inverse of a is b such that a + b = 0
     * 
     * @return Additive inverse of current object
     */
    public Rational opposite() {
        return new Rational(-this._numerator, this._denominator);
    }

    /**
     * Creates a new `Rational` which is the reciprocal of this object. A reciprocal
     * is where the numerator is swapped the denominator of a `Rational` number
     * 
     * @return
     * @throws IllegalArgumentException when numerator is 0
     */
    public Rational reciprocal() throws IllegalArgumentException {
        if (this._numerator == 0) {
            throw new IllegalArgumentException("Numerator of current object is 0. This will result in a divide by 0");
        }
        return new Rational(this._denominator, this._numerator);
    }

    /**
     * Multiplies self by another `Rational` and returns a new `Rational` with the
     * result.
     * 
     * @param multiplier `Rational` value to multiply by
     * @return `Rational` with result of multiplication
     */
    public Rational times(Rational multiplier) {
        if (this.isZero() || multiplier.isZero()) {
            return new Rational();
        }

        // Attempts to reduce overflow by switching numerators and denominators which
        // simplifies the equation.

        Rational reduced1 = new Rational(this._numerator, multiplier.denominator());
        Rational reduced2 = new Rational(multiplier.numerator(), this._denominator);
        return new Rational(reduced1.numerator() * reduced2.numerator(),
                reduced1.denominator() * reduced2.denominator());
    }

    /**
     * Divides self by another `Rational` and returns a new `Rational` with the
     * result.
     * This implementation multiplies self with reciprocal of divisor
     * 
     * @param divisor `Rational` value to divide by
     * @return `Rational` result of division
     * @throws IllegalArgumentException when divisor is equivalent to 0
     */
    public Rational dividedBy(Rational divisor) throws IllegalArgumentException {
        return this.times(divisor.reciprocal());
    }

    /**
     * Adds self by another `Rational` and returns resulting `Rational` value
     * 
     * @param addend `Rational` value to be added this object
     * @return `Rational` value which is the result of summing this and
     */
    public Rational plus(Rational addend) {
        // Find the greatest common devisor between the numerators and denominators
        int numeratorGCD = (int) this.gcd(this._numerator, addend.numerator());
        int denominatorGCD = (int) this.gcd(this._denominator, addend.denominator());

        // Create firstNumeratorTerm and secondNumeratorTerm for easier readability
        // These will be added together to form the numerator
        long firstNumeratorTerm = (this.numerator() / numeratorGCD) * (addend.denominator() / denominatorGCD);
        long secondNumeratorTerm = (addend.numerator() / numeratorGCD) * (this.denominator() / denominatorGCD);

        long tempNumerator = firstNumeratorTerm + secondNumeratorTerm;
        long tempDenominator = this.lcm(this.denominator(), addend.denominator());

        long divisor = gcd(tempNumerator, tempDenominator);
        tempNumerator /= divisor;
        tempDenominator /= divisor;

        tempNumerator *= numeratorGCD;

        return new Rational((int) tempNumerator, (int) tempDenominator);
    }

    /**
     * Subtracts given `Rational` from self and returns resulting `Rational` value
     * 
     * @param subtrahend `Rational` value to be subtracted
     * @return `Rational` result of subtracting subtrahend to this object
     */
    public Rational minus(Rational subtrahend) {
        return this.plus(subtrahend.opposite());
    }

    /**
     * Implements fast powering algorithm to return a new `Rational` value.
     * The new `Rational` value will reflect this^exponent
     * 
     * @param exponent power of which this is being raised
     * @return `Rational` result of raising this object to the power of exponent
     */
    public Rational raisedToThePowerOf(int exponent) throws IllegalArgumentException {
        if (exponent == 0) {
            return new Rational(1, 1);
        } else if (exponent < 0) {
            return this.reciprocal().raisedToThePowerOf(-exponent);
        }

        // Saves what is being powered (reciprocal if exponent is negative)
        Rational base = new Rational(this);

        // Object to be stored as result later
        Rational result = new Rational(1, 1);

        // Fast powering which multiplies base times self or result based on if exponent
        // is even
        while (exponent > 0) {
            if (exponent % 2 == 0) {
                base = base.times(base);
                exponent /= 2;
            } else {
                result = result.times(base);
                exponent -= 1;
            }
        }

        return result;
    }

    /**
     * Function for checking if this is canonical zero
     * 
     * @return boolean as to whether this is canonical 0
     */
    public boolean isZero() {
        return this._numerator == 0;
    }

    /**
     * Function for checking if this is canonical 1
     * 
     * @return boolean as to whether this is canonical 1
     */
    public boolean isOne() {
        return this._numerator > 0 && this._numerator == this._denominator;
    }

    /**
     * Function for checking if this is canonical -1
     * 
     * @return boolean as to whether this is canonical -1
     */
    public boolean isMinusOne() {
        return this._numerator < 0 && (this._numerator * -1) == this._denominator;
    }

    /**
     * Returns whether a given `Rational` value is less than this object's value
     * 
     * @param comparand `Rational` value to be compared to
     * @return boolean as to whether or not this value is less than other `Rational`
     *         value
     */
    public boolean lessThan(Rational comparand) {
        if (comparand == null || this._numerator >= 0 && comparand.numerator() < 0 || this.equals(comparand)) {
            return false;
        } else if (this._numerator < 0 && comparand.numerator() >= 0) {
            return true;
        }
        Rational result = this.minus(comparand);
        return result.numerator() < 0;
    }

    /**
     * Returns whether a given `Number` value is less than this object's value
     * 
     * @param comparand `Number` value to be compared to
     * @return boolean as to whether or not this value is less than other `Number`
     *         value
     */
    public boolean lessThan(Number comparand) {
        if (comparand instanceof Rational) {
            return this.lessThan((Rational) comparand);
        } else if (comparand instanceof Float) {
            if (Math.abs(this.floatValue() - comparand.floatValue()) < Rational.FLOAT_PRECISION
                    || Float.isNaN((Float) comparand)) {
                return false;
            }
            return Float.compare(this.floatValue(), comparand.floatValue()) < 0;
        } else if (comparand instanceof Double) {
            if (Math.abs(this.doubleValue() - comparand.doubleValue()) < Rational.DOUBLE_PRECISION
                    || Double.isNaN((Double) comparand)) {
                return false;
            }
            return Double.compare(this.doubleValue(), comparand.doubleValue()) < 0;
        } else if (comparand instanceof Number) {
            return BigDecimal.valueOf(comparand.longValue()).compareTo(BigDecimal.valueOf(this.doubleValue())) > 0;
        } else {
            return false;
        }
    }

    /**
     * Returns whether a given `Rational` value is greater than this object's value
     * 
     * @param comparand `Rational` value to be compared to
     * @return boolean as to whether or not this value is greater than other
     *         `Rational` value
     */
    public boolean greaterThan(Rational comparand) {
        if (comparand == null || this._numerator < 0 && comparand.numerator() >= 0 || this.equals(comparand)) {
            return false;
        } else if (this._numerator >= 0 && comparand.numerator() < 0) {
            return true;
        }
        Rational result = this.minus(comparand);
        return result.numerator() > 0;
    }

    /**
     * Returns whether a given `Number` value is greater than this object's value
     * 
     * @param comparand `Number` value to be compared to
     * @return boolean as to whether or not this value is greater than other
     *         `Number` value
     */
    public boolean greaterThan(Number comparand) {
        if (comparand instanceof Rational) {
            return this.greaterThan((Rational) comparand);
        } else if (comparand instanceof Float) {
            if (Math.abs(this.floatValue() - comparand.floatValue()) < Rational.FLOAT_PRECISION
                    || Float.isNaN((Float) comparand)) {
                return false;
            }
            return Float.compare(this.floatValue(), comparand.floatValue()) > 0;
        } else if (comparand instanceof Double) {
            if (Math.abs(this.doubleValue() - comparand.doubleValue()) < Rational.DOUBLE_PRECISION
                    || Double.isNaN((Double) comparand)) {
                return false;
            }
            return Double.compare(this.doubleValue(), comparand.doubleValue()) > 0;
        } else if (comparand instanceof Number) {
            return BigDecimal.valueOf(comparand.longValue()).compareTo(BigDecimal.valueOf(this.doubleValue())) < 0;
        } else {
            return false;
        }
    }

    /**
     * Determines whether this object is equivalent in value to another object.
     * If given object is not an instance of Number, returns false.
     * If given object is an instance of number, returns if value is same.
     * 
     * @param object object to check equality of
     * @return boolean as to whether given object is is equivalent to this
     *         `Rational` value
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Number)) {
            return false;
        } else if (this == object) {
            return true;
        } else if (object instanceof Float && Float.isNaN((Float) object)) {
            return false;
        } else if (object instanceof Double && Double.isNaN((Double) object)) {
            return false;
        } else if (object instanceof Rational) {
            return this.numerator() == ((Rational) object).numerator()
                    && this.denominator() == ((Rational) object).denominator();
        } else {
            return this.compareTo((Number) object) == 0;
        }
    }

    /**
     * Returns hashcode based on the string representation of this value
     * 
     * @return consistent int hashcode
     */
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    /**
     * Returns a string representation of `Rational`
     * If this is a whole number, returns string of numerator
     * If this is not a whole number, returns a fraction with negative sign at front
     * 
     * @return String representation of `Rational` object
     */
    public String toString() {
        if (this._denominator == 1) {
            return this._numerator + "";
        } else if (this._denominator < 0 && this._numerator > 0) {
            // In the case where the denominator is the min_value, simplifying did not work,
            // so we have to represent it differently.
            return "-" + this._numerator + "/" + String.format("%d", this._denominator).substring(1);
        } else if (this._numerator < 0 && this._denominator < 0) {
            return String.format("%d", this._numerator).substring(1) + "/"
                    + String.format("%d", this._denominator).substring(1);
        } else {
            return this._numerator + "/" + this._denominator;
        }
    }

    /**
     * Helper method which simplifies the numerator and denominator by ensuring sign
     * is in numerator and both numbers are divided by their gcd
     */
    private void simplify() {
        // If numerator is 0, denominator is always 1
        if (this._numerator == 0) {
            this._denominator = 1;
            return;
        }
        
        // Divides numerator and denominator by greatest common divisor to simplify
        long divisor = gcd((long) this._numerator, (long) this._denominator);
        this._numerator /= divisor;
        this._denominator /= divisor;
        
        // Flips both signs if we have -a / -b or a / -b
        // Don't do this if denominator is -2147483648
        if (this._denominator < 0 && this._denominator != Integer.MIN_VALUE) {
            this._numerator *= -1;
            this._denominator *= -1;
        }
    }

    /**
     * Helper method which implements the euclidean algorithm for computing gcd.
     * This is most useful when simplifying a fraction
     * 
     * @param a First long to be compared
     * @param b Second long to be compared
     * @return greatest common divisor between two numbers
     */
    private long gcd(long a, long b) {
        if (a == 0 && b == 0) {
            return 1;
        }
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            long tmp = b;
            b = a % b;
            a = tmp;
        }
        return Math.abs(a);
    }

    /**
     * Helper function which returns the least common multiple of two numbers.
     * This is useful when adding/subtracting two different `Rational` values.
     * 
     * @param a First number to be compared
     * @param b Second number to be compared
     * @return least common multiple of two numbers
     */
    private int lcm(int a, int b) {
        return a * (b / (int) gcd(a, b));
    }

    @Override
    public int compareTo(Number o) {
        if (o == null || this.lessThan(o) || (o instanceof Double && Double.isNaN((Double) o))
                || (o instanceof Float && Float.isNaN((Float) o))) {
            return -1;
        } else if (this.greaterThan(o)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int intValue() {
        return (int) this._numerator / (int) this._denominator;
    }

    @Override
    public long longValue() {
        return (long) this._numerator / (long) this._denominator;
    }

    @Override
    public float floatValue() {
        return (float) this._numerator / this._denominator;
    }

    @Override
    public double doubleValue() {
        return (double) this._numerator / this._denominator;
    }

    @Override
    public Rational clone() {
        return new Rational(this);
    }
}
