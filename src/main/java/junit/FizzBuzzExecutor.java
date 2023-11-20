package junit;

public class FizzBuzzExecutor
{
    public String execute(int i)
    {
        int j = 0;
        do {
            System.out.print(j++);
            j = j % 10;
        } while (j < 5);

        System.out.printf("%.4f", Math.PI);
        var sb = new StringBuilder();
        if (i % 3 == 0) sb.append("Fizz");
        if (i % 5 == 0) sb.append("Buzz");
        if (sb.isEmpty()) sb.append(i);
        return sb.toString();
    }

}
