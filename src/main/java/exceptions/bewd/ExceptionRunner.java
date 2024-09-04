package exceptions.bewd;

public class ExceptionRunner
{
    public static void main(String[] args)
    {
        Calculator calc = new Calculator(100);

        int result = calc.divideByNumber(13);

        System.out.println(result);
    }
}
