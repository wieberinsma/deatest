package junit;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator
{
    private static final String NON_NUMERIC_REGEX = "[^0-9-]";

    private static final String MULTIPLE_DELIMITER_REGEX = "(?s)//.+\n.*";

    public int add(String str)
    {
        int numberStartIndex = getNumberStartIndex(str);

        if (numberStartIndex == -1)
        {
            return 0;
        }

        final String[] numberArray = getNumberArray(str, numberStartIndex);

        return calculateSum(numberArray);
    }

    private int getNumberStartIndex(String str)
    {
        int numberStartIndex = -1;
        int delimiterEndIndex = str.indexOf("\n");

        for (int i = (delimiterEndIndex + 1); i < str.length(); i++)
        {
            if (Character.isDigit(str.charAt(i)))
            {
                numberStartIndex = i;
                break;
            }
        }

        return numberStartIndex;
    }

    private String getDelimiterRegex(String str, int numberStartIndex)
    {
        var delimiters = getDelimiters(str, numberStartIndex);
        String[] delimiterArray = getDelimiterArray(delimiters);

        List<String> delimiterList = Arrays.stream(delimiterArray).map(Pattern::quote).collect(Collectors.toList());
        return "(" + String.join("|", delimiterList) + ")+";
    }

    private String getDelimiters(String str, int numberStartIndex)
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
        }
        else
        {
            return new String[] {delimiters};
        }
    }

    private String[] getNumberArray(String str, int numberStartIndex)
    {
        final String[] numberArray;

        if (str.matches(MULTIPLE_DELIMITER_REGEX))
        {
            String numberString = str.substring(numberStartIndex).strip();

            String delimiterRegex = getDelimiterRegex(str, numberStartIndex);
            numberArray = numberString.split(delimiterRegex);
        }
        else
        {
            numberArray = str.strip().split(NON_NUMERIC_REGEX);
        }

        return numberArray;
    }

    private int calculateSum(String[] numberArray)
    {
        if (numberArray.length == 1)
        {
            var number = Integer.parseInt(numberArray[0]);
            return isValidNumber(number) ? number : -1;
        }
        else if (numberArray.length >= 2)
        {
            return Arrays.stream(numberArray)
                    .map(String::strip)
                    .filter(number -> !number.isBlank())
                    .mapToInt(Integer::parseInt)
                    .filter(this::isValidNumber)
                    .sum();
        }
        else
        {
            return -1;
        }
    }

    public boolean isValidNumber(int num)
    {
        return num >= -1000 && num <= 1000;
    }
}
