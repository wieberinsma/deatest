package threads;

import org.apache.commons.math3.primes.Primes;
import threads.exceptions.MustDieException;
import threads.exceptions.OuchIFoundUnluckyNumberAndHenceMustDieException;

import java.util.List;

public class PrimeTester implements Runnable
{
    private final NumberUnderTest numberUnderTest;
    private final int highestNumberToTest;
    private final List<Integer> unluckyNumbers = List.of(37, 101, 821, 1303);

    public PrimeTester(NumberUnderTest numberUnderTest, int highestNumberToTest)
    {
        this.numberUnderTest = numberUnderTest;
        this.highestNumberToTest = highestNumberToTest;
    }

    @Override
    public void run()
    {
//        var currentThread = Thread.currentThread();
//
//        System.out.println(currentThread.getId());
        boolean interrupted = Thread.currentThread().isInterrupted();

        try
        {
//            Thread.sleep(1000);

            if (interrupted)
            {
                System.out.println("THREAD IS INTERRUPTED");
            }
            else
            {
                startTesting();
            }
        }
        catch (MustDieException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void startTesting() throws MustDieException
    {
        while (true)
        {
            var number = numberUnderTest.getNumber();

            if (number > highestNumberToTest)
            {
                break;
            }

//            if (number == 37)
//            {
//                throw new OuchIFoundThirtySevenAndHenceMustDieException(Thread.currentThread().getId() +
//                        " found Thirty Seven and must die.");
//            }

            if (unluckyNumbers.contains(number)) {
                throw new OuchIFoundUnluckyNumberAndHenceMustDieException(Thread.currentThread().getId() +
                        " found Unlucky Number and must die.");
            }

            boolean isPrime = Primes.isPrime(number);

            if (isPrime)
            {
                System.out.println(Thread.currentThread().getId() + " found a prime number: " + number);
            }
        }
    }
}
