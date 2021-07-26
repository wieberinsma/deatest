public class FizzBuzzRunner {

    public static void main(String[] args) {
        var fizzBuzzExecutor = new FizzBuzzExecutor();

        for (int i = 1; i <= 100; ++i) {
            var result = fizzBuzzExecutor.execute(i);
            System.out.println(result);
        }
    }
}
