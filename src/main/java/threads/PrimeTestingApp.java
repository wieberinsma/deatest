package threads;

import org.springframework.stereotype.Component;

@Component
public class PrimeTestingApp
{
    private static final int HIGHEST_NUMBER_TO_TEST = 2000;

    private static final int CPU_CORE_NUMBER = 4;

    public static void main(String[] args) throws InterruptedException
    {
        var app = new PrimeTestingApp();
        app.startTesting();
    }

    private void startTesting()
    {
        var numberUnderTest = new NumberUnderTest();

        for (int i = 1; i <= CPU_CORE_NUMBER; i++)
        {
            var tester = new PrimeTester(numberUnderTest, HIGHEST_NUMBER_TO_TEST);
            var thread = new Thread(tester);

            thread.start();
//            thread.run(); // NIET GEBRUIKEN!
            System.out.println("THREAD STARTED");

            // Als je een thread interrupt, kan deze een InterruptedException gooien, maar dit hoeft niet
//            thread.interrupt();
        }

//        getScheduler().scheduleWithFixedDelay(() -> System.out.println("SCHEDULED TASK EXECUTING"), 3000);
//        getScheduler().scheduleWithFixedDelay(new ExampleTask(), 3000);
    }

    public class ExampleTask implements Runnable {
        @Override
        public void run()
        {
            System.out.println("SCHEDULED TASK EXECUTING");
        }
    }

//    private TaskScheduler getScheduler()
//    {
//        ApplicationContext context = new AnnotationConfigApplicationContext(PrimeTestingApp.class);
//        return context.getBean(ThreadPoolTaskScheduler.class);
//    }
//
//    @Bean
//    public TaskScheduler scheduler() {
//        return new ThreadPoolTaskScheduler();
//    }
}
