package threads;

public class NumberUnderTest
{
    private int number;

    public synchronized int getNumber()
    {
        return number++;
    }
}