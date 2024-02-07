package junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FizzBuzzExecutorTest
{
    private FizzBuzzExecutor sut;

    @BeforeEach
    public void setup()
    {
        sut = new FizzBuzzExecutor();
    }

    @Test
    public void executeWithValidIntTestFor37()
    {
        Assertions.assertEquals("37", sut.execute(37));
    }

    @Test
    public void executeWithValidIntTestDivideBy3()
    {
        Assertions.assertEquals("Fizz", sut.execute(6));
    }

    @Test
    public void executeWithValidIntTestDivideBy5()
    {
        Assertions.assertEquals("Buzz", sut.execute(20));
    }

    @Test
    public void executeWithValidIntTestDivideBy3And5()
    {
        Assertions.assertEquals("FizzBuzz", sut.execute(45));
    }
}
