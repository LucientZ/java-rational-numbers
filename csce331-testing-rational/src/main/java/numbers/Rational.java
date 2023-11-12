package numbers;

/**
 * Hello world!
 *
 */
public class Rational {
    private int _numerator;
    private int _denominator;

    public Rational() {
        this(0);
    }

    public Rational(int numerator) {
        this(numerator, 1);
    }

    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator must not be 0");
        }

        this._numerator = numerator;
        this._denominator = denominator;
        this.simplify();
    }

    public Rational(Rational original){
        this._numerator = original.numerator();
        this._denominator = original.denominator();
    }

    public int numerator() {
        return this._numerator;
    }

    public int denominator() {
        return this._denominator;
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
}
