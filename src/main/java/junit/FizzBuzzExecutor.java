package junit;

import java.util.function.BiFunction;
import java.util.stream.Stream;

public class FizzBuzzExecutor
{
    public String execute(int number)
    {
        var sb = new StringBuilder();

        // Dekt dit alle scenario's?
        var division = new Division(number, 3, "Fizz");
        sb.append(division.isNumberDivisible() ? division.getWord() : number);

        // 'Anonieme' functie
        BiFunction<Integer, Integer, Boolean> function = (a, b) -> a % b == 0;
        boolean isDivisible = function.apply(number, 3);

        // Misbruik van Stream voor 1 element
        boolean isDivisibleToo = Stream.of(number).map(n -> n % 3 == 0).findFirst().get();

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

//    public boolean isNumberDivisibleBy(int number, int divisionNumber) {
//        return number % divisionNumber == 0;
//    }
}
