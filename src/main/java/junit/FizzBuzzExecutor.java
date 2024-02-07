package junit;

public class FizzBuzzExecutor
{
    public String execute(int i)
    {
        var sb = new StringBuilder();
        if (i % 3 == 0) sb.append("Fizz");
        if (i % 5 == 0) sb.append("Buzz");
        if (sb.isEmpty()) sb.append(i);
        return sb.toString();
    }

}
