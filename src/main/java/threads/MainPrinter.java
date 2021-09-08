package threads;

public class MainPrinter
{
    public static void main(String[] args)
    {
        NumberPrinter numberPrinter = new NumberPrinter();
        StringPrinter stringPrinter = new StringPrinter();

        Thread t1 = new Thread(numberPrinter);
        Thread t2 = new Thread(() -> stringPrinter.printStrings());
        Thread t3 = new Thread(() ->
        {
            for (int i = 0; i < 1000000; i++)
            {
                System.out.println("Inner: " + i + 1);
            }
        });

        t1.stop();

        t1.start();
        t2.start();
        t3.start();
    }
}
