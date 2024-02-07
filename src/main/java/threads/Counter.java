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

    /**
     * Let op dat je _alles_ synchronized maakt. Gevolg regel 32/36: lijkt niet goed te gaan (maar is wel zo)
     */
    @Override
    public void run()
    {
        String threadName = Thread.currentThread().getName();

        this.increment();
        System.out.println("Value for " + threadName + ": " + this.getValue());

        this.decrement();
        System.out.println("Value for " + threadName + ": " + this.getValue());
    }
}
