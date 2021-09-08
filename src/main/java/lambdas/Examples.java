package lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Examples
{
    private final String[] hobbits = {"Frodo", "Samwise", "Merry", "Pippin"};

    private final Stream<String> hobbitStream =  Arrays.stream(hobbits);

    public static void main(String[] args)
    {
        Examples examples = new Examples();
        examples.example();
    }

    public void example()
    {
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

        // Door middel van methode referentie
        List<String> likedHobbits = hobbitStream.filter(likeFunction::like).collect(Collectors.toList());
        likedHobbits.forEach(System.out::println);
    }

//    @FunctionalInterface
    public interface LikeFunction<String> {
        boolean like(String hobbit);
        boolean dislike(String hobbit);
    }
}
