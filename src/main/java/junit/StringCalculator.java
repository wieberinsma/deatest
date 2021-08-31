package junit;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator
{
    private static final String DELIMITER_REGEX = "(?s)//.+\n.*";

    public int add(String str)
    {
        int numberStartIndex = getNumberStartIndex(str);

        String[] delimiterArray;
        String numbers;

        String delimiterRegex;
        String[] numberArray;

        if (numberStartIndex == -1)
        {
            return 0;
        }

        if (str.matches(DELIMITER_REGEX))
        {
            var delimiters = getDelimitersForRegex(str, numberStartIndex);

            delimiterArray = getDelimiterArray(delimiters);
            numbers = str.substring(numberStartIndex).strip();
        } else
        {
            delimiterArray = new String[]{"\n", ","};
            numbers = str.strip();
        }

        delimiterRegex = getDelimiterRegexForArray(delimiterArray);
        numberArray = numbers.split(delimiterRegex);

        // Case handling
        if (numbers.equals(""))
        {
            return 0;
        } else if (numberArray.length == 1)
        {
            var number = Integer.parseInt(numbers);
            return isValidNumber(number) ? number : -1;
        } else if (numberArray.length >= 2)
        {
            return Arrays.stream(numberArray)
                    .map(String::strip)
                    .filter(number -> !number.isBlank())
                    .mapToInt(Integer::parseInt)
                    .filter(this::isValidNumber)
                    .sum();
        } else
        {
            return -1;
        }
    }

    private int getNumberStartIndex(String str)
    {
        int numberStartIndex = -1;
        int returnIndex = str.indexOf("\n");

        for (int i = 0; i < str.length(); i++)
        {
            if (Character.isDigit(str.charAt(i)) && i > returnIndex)
            {
                numberStartIndex = i;
                break;
            }
        }

        return numberStartIndex;
    }

    private String getDelimitersForRegex(String str, int numberStartIndex)
    {
        String delimiters = str.substring(2, numberStartIndex - 1).strip();

        if (delimiters.isEmpty())
        {
            delimiters = Character.toString(str.charAt(2));
        }

        return delimiters;
    }

    private String[] getDelimiterArray(String delimiters)
    {
        if (delimiters.contains("][") && !delimiters.equals("]["))
        {
            return delimiters.substring(1, delimiters.length() - 1).split("]\\[");
        } else
        {
            return new String[]{delimiters};
        }
    }

    private String getDelimiterRegexForArray(String[] delimiters)
    {
        List<String> delimiterList = Arrays.stream(delimiters).map(Pattern::quote).collect(Collectors.toList());
        return "(" + String.join("|", delimiterList) + ")+";
    }

    public boolean isValidNumber(int num)
    {
        return num <= 1000;
    }
}
