package numbers;

/**
 * Hello world!
 *
 */
public class Rational {
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
     * Creates a new `Rational` which is the reciprocal of this object. A reciprocal is where
     * @return
     * @throws IllegalArgumentException when numerator is 0 (This will be thrown by the constructor)
     */
    public Rational reciprocal() {
        return new Rational(this._denominator, this._numerator);
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
        return (a / gcd(a, b)) * b;
    }
}
