package numbers;

/**
 * Hello world!
 *
 */
public class Rational 
{
    private int numerator;
    private int denominator;

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public Rational(){
        this.numerator = 0;
        this.denominator = 1;
    }

    public int numerator(){
        return this.numerator;
    }

    public int denominator(){
        return this.denominator;
    }
}
