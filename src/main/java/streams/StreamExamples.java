package streams;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static streams.Streaming.persons;

public class StreamExamples
{
    private final String[] hobbits = {"Frodo", "Samwise", "Merry", "Pippin"};

    private final List<String> hobbitList = Arrays.asList(hobbits);

    public static void main(String[] args)
    {
        StreamExamples examples = new StreamExamples();
        examples.example();

//        f1();
//        f3();
//        f10();
//        f11();
        f_par();
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
//        hobbitList.stream().forEach(hobbit -> System.out.println(hobbit));
//        hobbitList.forEach(hobbit -> System.out.println(hobbit));

        // Maar zie dat hier de 2 stacktraces verschillen, en dus verschillend gedrag _kunnen_ vertonen
//        hobbitList.stream().forEach(hobbit -> hobbitList.remove(hobbit));
//        hobbitList.forEach(hobbit -> hobbitList.remove(hobbit));
    }

    static void f1()
    {
        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

        myList.stream() // creates stream
                .filter(element -> element.startsWith("c")) //  leaves a stream with only c2 and c1
                .map(String::toUpperCase) // leaves a stream with only C2 and C1
                .sorted() // sorts so, C1 and C2 change order
                .forEach(System.out::println); // terminate the Stream
    }

    static void f2()
    {
        Arrays.asList("a1", "a2", "a3")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);
    }

    static void f3()
    {
        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);
    }

    static void f4()
    {
        IntStream.range(1, 4)
                .forEach(System.out::println);
    }

    static void f5()
    {
        Arrays.stream(new int[] {1, 2, 3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);
    }

    static void f6()
    {
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);
    }

    static void f7()
    {
        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
    }

    static void f8()
    {
        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
    }

    static void f9()
    {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s ->
                {
                    System.out.println("filter: " + s);
                    return true;
                }).forEach(s -> System.out.println("forEach: " + s));
    }

    static void f10()
    {
        // Vertical vs horizontal iteration resp. stateless vs. stateful (e.g. sorted()) operations
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s ->
                {
                    return s.toUpperCase();
                })
                .sorted()
                .anyMatch(s ->
                {
                    return s.startsWith("A");
                });
    }

    static void f11()
    {
        Stream<String> stream =
                Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        System.out.println(stream.anyMatch(s -> true));
        System.out.println(stream.noneMatch(s -> true));
    }

    static void f12()
    {
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));
        System.out.println(streamSupplier.get().anyMatch(s -> true));
        System.out.println(streamSupplier.get().noneMatch(s -> true));

    }

    static void f13()
    {
        List<Person> filtered = persons.stream()
                .filter(p -> p.getName().endsWith("e"))
                .collect(Collectors.toList());

        System.out.println(filtered);
    }

    static void f14()
    {
        String phrase = persons.stream()
                .filter(p -> (Period.between(p.getBirthDate(), LocalDate.now()).getYears()) >= 18)
                .map(Person::getName)
                .collect(Collectors.joining(" and ", "In Holland ", " are of legal age."));

        System.out.println(phrase);
    }

    static void f15()
    {
        System.out.println(persons.stream()
                .map(p -> Period.between(p.getBirthDate(), LocalDate.now()).getYears())
                .filter(a -> a > 20)
                .reduce(0, (a, b) -> a + b));
    }

    static void f16()
    {
        IntStream.rangeClosed(1, 100)
                .mapToObj(i -> i % 3 == 0 ? (i % 5 == 0 ? "FizzBuzz" : "Fizz") : (i % 5 == 0 ? "Buzz" : i))
                .forEach(System.out::println);
    }

    static void f_par()
    {
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s ->
                {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s ->
                {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));
    }
}
