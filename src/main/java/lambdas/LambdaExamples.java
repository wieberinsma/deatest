package lambdas;

import java.util.Arrays;
import java.util.stream.Stream;

public class Examples
{
    private final Stream<String> hobbitStream = Arrays.stream(new String[] {"Frodo", "Samwise", "Merry", "Pippin"});

    public static void main(String[] args)
    {
        Examples examples = new Examples();
        examples.example();
    }

    public void example()
    {
        // Op dit moment kan hier nog geen lambda van worden gemaakt, omdat de interface 2 methodes heeft
        LikeFunction<String> likeFunction = new LikeFunction<>()
        {
            @Override
            public boolean like(String hobbit)
            {
                return hobbit.startsWith("Fro");
            }

            @Override
            public boolean dislike(String hobbit)
            {
                return !hobbit.startsWith("Fro");
            }
        };

        boolean resultTrue = likeFunction.like("Frodo");
        boolean resultFalse = likeFunction.like("Samwise");

        // Door middel van methode referenties
        hobbitStream.filter(likeFunction::like).forEach(System.out::println);

        // Er zijn @FunctionalInterface objecten waarbij de methode geen argument heeft, dan zijn haakjes verplicht.
        // Merk op dat je lambdas niet in een 'var' kan initializeren; een lambda moet verwijzen naar een interface
        Runnable lambdaZonderArgument = () -> System.out.println("Test");
    }

    //    @FunctionalInterface
    public interface LikeFunction<String>
    {
        boolean like(String hobbit);

        boolean dislike(String hobbit);
    }
}
