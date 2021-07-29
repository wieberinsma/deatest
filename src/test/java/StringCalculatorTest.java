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
    }

    @Test
    public void addWithInvalidInt()
    {
        Assertions.assertEquals(999, stringCalculator.add("25,91,16"));
        Assertions.assertEquals(999, stringCalculator.add("123,,15"));
    }
}
