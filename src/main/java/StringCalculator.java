import java.util.Arrays;

public class StringCalculator
{
    private static final String DELIMITER = ",";

    public int add(String numbers)
    {
        if (numbers.strip().equals(""))
            return 0;
        else if (!numbers.contains(DELIMITER))
            return Integer.parseInt(numbers.strip());
        else if (numbers.split(DELIMITER).length >= 2)
        {
            return Arrays.stream(numbers.split(DELIMITER))
                    .map(String::strip)
                    .filter(number -> !number.isBlank())
                    .mapToInt(Integer::parseInt)
                    .sum();
        }
        else
            return 999;
    }
}
