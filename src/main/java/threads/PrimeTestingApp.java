package threads;

public class PrimeTestingApp
{
    private static final int HIGHEST_NUMBER_TO_TEST = 2000;

    private static final int CPU_CORE_NUMBER = 4;

    public static void main(String[] args) throws InterruptedException
    {
        var app = new PrimeTestingApp();
        app.startTesting();
    }

    private void startTesting() throws InterruptedException
    {
        var numberUnderTest = new NumberUnderTest();

        for (int i = 1; i <= CPU_CORE_NUMBER; i++)
        {
            var tester = new PrimeTester(numberUnderTest, HIGHEST_NUMBER_TO_TEST);
            var thread = new Thread(tester::run);
            thread.start();
//            thread.join();
        }
    }
}
