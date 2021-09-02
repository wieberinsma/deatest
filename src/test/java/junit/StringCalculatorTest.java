package junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest
{
    private StringCalculator stringCalculator;

    @BeforeEach
    public void setup()
    {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void addWithValidIntTestEmptyString()
    {
        Assertions.assertEquals(0, stringCalculator.add(""));
        Assertions.assertEquals(0, stringCalculator.add("\n  "));
    }

    @Test
    public void addWithValidIntTestOneInteger()
    {
        Assertions.assertEquals(14, stringCalculator.add("14"));
        Assertions.assertEquals(0, stringCalculator.add("  0  "));
    }

    @Test
    public void addWithValidIntTestTwoIntegers()
    {
        Assertions.assertEquals(116, stringCalculator.add("25,\n 91"));
        Assertions.assertEquals(1, stringCalculator.add("33,-32"));
        Assertions.assertEquals(5, stringCalculator.add(",5"));
    }

    @Test
    public void addWithValidIntTestMultipleIntegers()
    {
        Assertions.assertEquals(1337, stringCalculator.add("44,346,2,945"));
        Assertions.assertEquals(138, stringCalculator.add("123, ,15"));
    }

    @Test
    public void addWithValidIntTestReturnDelimiter()
    {
        Assertions.assertEquals(101, stringCalculator.add("44,32\n25"));
        Assertions.assertEquals(306, stringCalculator.add("44,32\n25:205"));
    }

    @Test
    public void addWithValidIntTestAnyDelimiter()
    {
        Assertions.assertEquals(111, stringCalculator.add("44&32_25.10"));
        Assertions.assertEquals(101, stringCalculator.add("//&\n44&32&25"));
        Assertions.assertEquals(101, stringCalculator.add("//\n\n44\n32\n25"));
        Assertions.assertEquals(101, stringCalculator.add("//\n\n\n \r44\n32\n25\n \r"));
        Assertions.assertEquals(101, stringCalculator.add("//0\n044032025"));
        Assertions.assertEquals(101, stringCalculator.add("//][\n44][32][25"));
        Assertions.assertEquals(101, stringCalculator.add("// \n 44 32 25"));
    }

    @Test
    public void addWithValidIntTestNumberRange()
    {
        Assertions.assertEquals(-1, stringCalculator.add("//[X]\n1001"));
        Assertions.assertEquals(6, stringCalculator.add("//+\n1+2+3+" + -2147483648));
    }

    @Test
    public void addWithValidIntTestAnyLengthDelimiter()
    {
        Assertions.assertEquals(101, stringCalculator.add("//<DELIMITER>\n44<DELIMITER>32<DELIMITER>25"));
    }

    @Test
    public void addWithValidIntTestAnyMultipleDelimiters()
    {
        Assertions.assertEquals(166, stringCalculator.add("//[0-0][|][:]\n1:2:3:4|50-06|70-08:9:100-0110-0100"));
        Assertions.assertEquals(14, stringCalculator.add("//[1][][]\n012131415"));
    }

    @Test
    public void addWithInvalidDelimiterAny()
    {
        Assertions.assertThrows(NumberFormatException.class, () -> stringCalculator.add("//\n\n1;2;3"));
    }
}
