package numbers;

/**
 * Hello world!
 *
 */
public class Rational 
{
    private int _numerator;
    private int _denominator;

    public Rational(){
        this(0);
    }

    public Rational(int numerator){
        this(numerator, 1);
    }

    public Rational(int numerator, int denominator){
        this._numerator = numerator;
        this._denominator = denominator;
    }

    public int numerator(){
        return this._numerator;
    }

    public int denominator(){
        return this._denominator;
    }
}
