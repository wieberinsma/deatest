package streams;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Examples
{
    private final String[] hobbits = {"Frodo", "Samwise", "Merry", "Pippin"};

    private final List<String> hobbitList = Arrays.asList(hobbits);

    public static void main(String[] args)
    {
        Examples examples = new Examples();
        examples.example();
    }

    public void example()
    {
        // Stream.of() heeft een varargs (ongespecifeerd aantal) argument(en) van verschillende types
        Stream<String> hobbitStream = Stream.of(hobbits);
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Boolean> booleanStream = Stream.of(true);

        // Arrays.stream() vraagt specifiek om een array
        Stream<String> hobbitStream2 = Arrays.stream(hobbits);

        // IntStream is een andere implementatie van BaseStream, net als Stream dat is
        IntStream integerStream2 = IntStream.range(0, 37);

        // Aanroepen van .stream() kan op alle Collection objecten
        Stream<String> hobbitStream3 = hobbitList.stream();
        Stream<String> hobbitStream4 = new HashSet<>(hobbitList).stream();

        // Er zijn twee methodes genaamd .forEach() die verschillend kunnen werken, hieronder niet
        hobbitList.stream().forEach(hobbit -> System.out.println(hobbit));
        hobbitList.forEach(hobbit -> System.out.println(hobbit));

        // Maar zie dat hier de 2 stacktraces verschillen, en dus verschillend gedrag _kunnen_ vertonen
        hobbitList.stream().forEach(hobbit -> hobbitList.remove(hobbit));
        hobbitList.forEach(hobbit -> hobbitList.remove(hobbit));
    }
}
