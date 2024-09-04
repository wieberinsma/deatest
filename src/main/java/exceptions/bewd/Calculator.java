package exceptions.bewd;

public class Calculator
{
    private int number;

    public Calculator(int number)
    {
        this.number = number;
    }

    public int divideByNumber(int division)
    {
        if (isDivisibleBy(division))
        {
            return number / division;
        }

        return 0;
    }

    public boolean isDivisibleBy(int division)
    {
        if (division == 0)
        {
            throw new ArithmeticException("Division by zero");
        }

        if (number % division != 0)
        {
            throw new ArithmeticException("Division leads to truncated result");
        }

        return false;
    }
}
