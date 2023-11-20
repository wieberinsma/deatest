package threads;

class Counter implements Runnable
{
    private int c = 0;

    public synchronized void increment()
    {
        c++;
    }

    public synchronized void decrement()
    {
        c--;
    }

    public synchronized int getValue()
    {
        return c;
    }

    @Override
    public void run()
    {
        String threadName = Thread.currentThread().getName();


        //incrementing
        this.increment();
        synchronized (this)
        {
            System.out.println("Value for " + threadName + ": " + this.getValue());
        }

        //decrementing
        this.decrement();
        synchronized (this)
        {
            System.out.println("Value for " + threadName + ": " + this.getValue());
        }
    }
}
