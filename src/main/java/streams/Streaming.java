package streams;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class Streaming
{
    public static List<Person> persons = Arrays.asList(
            new Person("Rody", LocalDate.of(1975, Month.SEPTEMBER, 13)),
            new Person("Janneke", LocalDate.of(1973, Month.JULY, 28)),
            new Person("Kai", LocalDate.of(2003, Month.JULY, 7))
    );
}
