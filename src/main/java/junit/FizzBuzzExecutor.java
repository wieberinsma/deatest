package junit;

public class FizzBuzzExecutor
{
    public String execute(int number)
    {
        var sb = new StringBuilder();

        var divideBy3 = number % 3 == 0;
        var divideBy5 = number % 5 == 0;

        if (divideBy3)
        {
            sb.append("Fizz");
        }

        if (divideBy5)
        {
            sb.append("Buzz");
        }

        if (!divideBy3 && !divideBy5)
        {
            sb.append(number);
        }

        return sb.toString();
    }
}
