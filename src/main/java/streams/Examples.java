package streams;

import java.util.*;
import java.util.stream.Stream;

public class Examples
{
    private final String[] hobbits = {"Frodo", "Samwise", "Merry", "Pippin"};

    private final List<String> hobbitList =  Arrays.asList(hobbits);

    public static void main(String[] args)
    {
        Examples examples = new Examples();
        examples.example();
    }

    public void example()
    {
        // Stream.of() heeft een varargs argument van verschillende types
        Stream<String> hobbitStream = Stream.of(hobbits);
        Stream<String> enemyStream = Stream.of("Saruman", "Sauron");

        // Arrays.stream() is altijd 1 array
        Stream<String> hobbitStream2 = Arrays.stream(hobbits);

        // Aanroepen van .stream() kan op alle Collection objecten
        Stream<String> hobbitStream3 = hobbitList.stream();
        Stream<String> hobbitStream4 = new HashSet<>(hobbitList).stream();

        // Er zijn twee methodes genaamd .forEach() die verschillend kunnen werken
        hobbitList.stream().forEach(hobbit -> System.out.println(hobbit));
        hobbitList.forEach(hobbit -> System.out.println(hobbit));

        hobbitList.stream().forEach(hobbit -> hobbitList.remove(hobbit));
        hobbitList.forEach(hobbit -> hobbitList.remove(hobbit));
    }
}
