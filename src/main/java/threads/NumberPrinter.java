package threads;

public class NumberPrinter implements Runnable
{
    private boolean isRunning = true;

    public void printNumbers()
    {
        for (int i = 0; i < 1000000000; i++)
        {
            System.out.println(i);
        }
    }

    public void stop()
    {
        this.isRunning = false;
    }

    @Override
    public void run()
    {
        while (isRunning)
        {
            printNumbers();
        }
    }
}
