public class FizzBuzzExecutor
{
    public String execute(int number)
    {
        var sb = new StringBuilder();

        var by3 = number % 3 == 0;
        var by5 = number % 5 == 0;

        if (by3)
            sb.append("Fizz");

        if (by5)
            sb.append("Buzz");

        if (!by3 && !by5)
            sb.append(number);

        return sb.toString();
    }
}
