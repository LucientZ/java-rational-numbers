package numbers;

/**
 * This class implements a rational number. That is, a value with an numerator
 * and denominator in which both values are integers.
 */
public class Rational extends Number implements Comparable<Number> {
    private int _numerator;
    private int _denominator;

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
    public Rational(int numerator, int denominator) {
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
        this._numerator = original.numerator();
        this._denominator = original.denominator();
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
        return new Rational(this._numerator * -1, this._denominator);
    }

    /**
     * Creates a new `Rational` which is the reciprocal of this object. A reciprocal
     * is where the numerator is swapped the denominator of a `Rational` number
     * 
     * @return
     * @throws IllegalArgumentException when numerator is 0
     */
    public Rational reciprocal() {
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
        return new Rational(this._numerator * multiplier._numerator, this._denominator * multiplier.denominator());
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
    public Rational dividedBy(Rational divisor) {
        if (divisor.numerator() == 0) {
            throw new IllegalArgumentException("Divisor must not have a numerator of 0");
        }
        return this.times(divisor.reciprocal());
    }

    /**
     * Adds self by another `Rational` and returns resulting `Rational` value
     * 
     * @param addend `Rational` value to be added this object
     * @return `Rational` value which is the result of summing this and
     */
    public Rational plus(Rational addend) {
        // Find the least common denominator between the two rational numbers
        int leastCommonDenominator = lcm(this._denominator, addend.denominator());

        // Calculate the new numerators based on lcd
        int firstNumerator = this._numerator * leastCommonDenominator / this._denominator;
        int secondNumerator = addend.numerator() * leastCommonDenominator / addend.denominator();

        return new Rational(firstNumerator + secondNumerator, leastCommonDenominator);
    }

    /**
     * Subtracts given `Rational` from self and returns resulting `Rational` value
     * 
     * @param subtrahend `Rational` value to be subtracted
     * @return `Rational` result of subtracting subtrahend to this object
     */
    public Rational minus(Rational subtrahend) {
        Rational negativeSubtrahend = new Rational(subtrahend.numerator() * -1, subtrahend.denominator());
        return this.plus(negativeSubtrahend);
    }

    /**
     * Implements fast powering algorithm to return a new `Rational` value.
     * The new `Rational` value will reflect this^exponent
     * 
     * @param exponent power of which this is being raised
     * @return `Rational` result of raising this object to the power of exponent
     */
    public Rational raisedToThePowerOf(int exponent) {
        if (exponent == 0) {
            return new Rational(1, 1);
        } else if (exponent < 0 && this._numerator == 0) {
            throw new IllegalArgumentException("Cannot raise 0 to the power of negative exponent.");
        }

        // Saves what is being powered (reciprocal if exponent is negative)
        Rational base = exponent < 0 ? new Rational(this.reciprocal()) : new Rational(this);

        // Object to be stored as result later
        Rational result = new Rational(1, 1);

        // Takes absolute value of exponent given.
        exponent = exponent < 0 ? exponent * -1 : exponent;

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
        return this._numerator == this._denominator;
    }

    /**
     * Function for checking if this is canonical -1
     * 
     * @return boolean as to whether this is canonical -1
     */
    public boolean isMinusOne() {
        return (this._numerator * -1) == this._denominator;
    }

    /**
     * Returns whether a given `Rational` value is less than this object's value
     * 
     * @param comparand `Rational` value to be compared to
     * @return boolean as to whether or not this value is less than other `Rational`
     *         value
     */
    public boolean lessThan(Rational comparand) {
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
        } else if (comparand instanceof Integer) {
            // When intValues are equal, need to check decimal with 0
            if (this.intValue() == comparand.intValue()) {
                return (this.doubleValue() - comparand.doubleValue()) < 0;
            } else {
                return this.intValue() < comparand.intValue();
            }
        } else if (comparand instanceof Long) {
            // When longValues are equal, need to check decimal with 0
            if (this.longValue() == comparand.longValue()) {
                return (this.doubleValue() - comparand.doubleValue()) < 0;
            } else {
                return this.longValue() < comparand.longValue();
            }
        } else if (comparand instanceof Float) {
            return this.floatValue() < comparand.floatValue();
        } else if (comparand instanceof Double) {
            return this.doubleValue() < comparand.doubleValue();
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
        } else if (comparand instanceof Integer) {
            if (this.intValue() == comparand.intValue()) {
                return (this.doubleValue() - comparand.doubleValue()) > 0;
            } else {
                return this.intValue() > comparand.intValue();
            }
        } else if (comparand instanceof Long) {
            if (this.longValue() == comparand.longValue()) {
                return (this.doubleValue() - comparand.doubleValue()) > 0;
            } else {
                return this.longValue() > comparand.longValue();
            }
        } else if (comparand instanceof Float) {
            return this.floatValue() > comparand.floatValue();
        } else if (comparand instanceof Double) {
            return this.doubleValue() > comparand.doubleValue();
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
    public boolean equals(Object object) {
        if (object instanceof Number) {
            return !(this.lessThan((Number) object) || this.greaterThan((Number) object));
        } else {
            return false;
        }
    }

    /**
     * Returns a string representation of `Rational`
     * If this is a whole number, returns string of numerator
     * If this is not a whole number, returns a fraction with negative sign at front
     * 
     * @return String representation of `Rational` object
     */
    public String toString() {
        this.simplify();
        if (this._denominator == 1) {
            return String.format("%d", this._numerator);
        } else {
            return String.format("%d/%d", this._numerator, this._denominator);
        }
    }

    /**
     * Helper method which simplifies the numerator and denominator by ensuring sign
     * is in numerator and both numbers are divided by their gcd
     */
    private void simplify() {
        // Flips both signs if we have -a / -b or a / -b
        if (this._denominator < 0) {
            this._numerator *= -1;
            this._denominator *= -1;
        }

        // Uses the absolute value of numerator for calculations
        int tempNumerator = this._numerator < 0 ? this._numerator * -1 : this._numerator;
        int tempDenominator = this._denominator;

        // Divides numerator and denominator by greatest common divisor until simplified
        while (gcd(tempNumerator, tempDenominator) != 1) {
            int divisor = gcd(tempNumerator, tempDenominator);
            tempNumerator /= divisor;
            tempDenominator /= divisor;
        }

        this._numerator = this._numerator < 0 ? tempNumerator *= -1 : tempNumerator;
        this._denominator = tempDenominator;
    }

    /**
     * Helper method which implements the euclidean algorithm for computing gcd.
     * This is most useful when simplifying a fraction
     * 
     * @param a First integer to be compared
     * @param b Second integer to be compared
     * @return greatest common divisor between two numbers
     */
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
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
        return (a * b) / gcd(a, b);
    }

    @Override
    public int compareTo(Number o) {
        if (this.lessThan(o)) {
            return -1;
        } else if (this.greaterThan(o)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int intValue() {
        return (int) this._numerator / this._denominator;
    }

    @Override
    public long longValue() {
        return (long) this._numerator / this._denominator;
    }

    @Override
    public float floatValue() {
        return (float) this._numerator / this._denominator;
    }

    @Override
    public double doubleValue() {
        return (double) this._numerator / this._denominator;
    }
}
