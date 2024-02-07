package lambdas;

import java.util.Arrays;
import java.util.stream.Stream;

public class LambdaExamples
{
    private final Stream<String> hobbitStream
            = Arrays.stream(new String[] {"Frodo", "Samwise", "Merry", "Pippin"});

    public static void main(String[] args)
    {
        LambdaExamples examples = new LambdaExamples();
        examples.example();
    }

    public void example()
    {
        // Op dit moment kan hier nog geen lambda van worden gemaakt, omdat de interface 2 methodes heeft
        LikeFunction<String> likeFunction = hobbit -> hobbit.startsWith("M");

        // Methode referentie met eigen functional interface
        hobbitStream
                .filter(likeFunction::like)
                .forEach(System.out::println);

        // Methode referentie zonder eigen functional interface
        hobbitStream
                .filter(LambdaExamples::likeAsFunction)
                .forEach(System.out::println);

        // Er zijn @FunctionalInterface objecten waarbij de methode geen argument heeft, dan zijn haakjes verplicht.
        // Merk op dat je lambdas niet in een 'var' kan initializeren; een lambda moet verwijzen naar een interface
        Runnable lambdaZonderArgument = () -> System.out.println("Test");
    }

    @FunctionalInterface
    public interface LikeFunction<String>
    {
        boolean like(String hobbit);
    }

    static boolean likeAsFunction(String hobbit)
    {
        return hobbit.startsWith("Fro");
    }
}
