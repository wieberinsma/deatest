package threads;

class Counter implements Runnable
{
    private int c = 0;

    public void increment()
    {
        c++;
    }

    public void decrement()
    {
        c--;
    }

    public int getValue()
    {
        return c;
    }

    @Override
    public void run()
    {
        String threadName =  Thread.currentThread().getName();

        //incrementing
        this.increment();
        System.out.println("Value for " + threadName + ": " + this.getValue());

        //decrementing
        this.decrement();
        System.out.println("Value for " + threadName + ": " + this.getValue());
    }
}
