package numbers;

/**
 * Hello world!
 *
 */
public class Rational 
{
    private int numerator;
    private int denominator;

    public Rational(){
        this.numerator = 0;
        this.denominator = 1;
    }

    public Rational(int numerator){
        this.numerator = numerator;
        this.denominator = 1;
    }

    public Rational(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int numerator(){
        return this.numerator;
    }

    public int denominator(){
        return this.denominator;
    }
}
