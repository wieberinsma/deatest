public class FizzBuzzRunner {

    public static void main(String[] args) {
        var fizzBuzzExecutor = new FizzBuzzExecutor();

        for (int i = 1; i <= 100; ++i) {
            System.out.println(fizzBuzzExecutor.execute(i));
        }
    }
}
