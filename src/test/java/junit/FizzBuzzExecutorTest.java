package junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FizzBuzzExecutorTest
{
    private FizzBuzzExecutor fizzBuzzExecutor;

    @BeforeEach
    public void setup()
    {
        fizzBuzzExecutor = new FizzBuzzExecutor();
    }

    @Test
    public void executeWithValidIntTestFor37()
    {
        Assertions.assertEquals("37", fizzBuzzExecutor.execute(37));
    }

    @Test
    public void executeWithValidIntTestDivideBy3()
    {
        //
        Assertions.assertEquals("Fizz", fizzBuzzExecutor.execute(6));
    }

    @Test
    public void executeWithValidIntTestDivideBy5()
    {
        //
        Assertions.assertEquals("Buzz", fizzBuzzExecutor.execute(20));
    }

    @Test
    public void executeWithValidIntTestDivideBy3And5()
    {
        Assertions.assertEquals("FizzBuzz", fizzBuzzExecutor.execute(45));
    }
}
