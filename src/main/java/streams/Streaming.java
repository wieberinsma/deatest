package streams;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Streaming {
    public static List<Person> persons = new ArrayList<>() {
        {
            add(new Person("Rody", LocalDate.of(1975, Month.SEPTEMBER, 13)));
            add(new Person("Janneke", LocalDate.of(1973, Month.JULY, 28)));
            add(new Person("Kai", LocalDate.of(2003, Month.JULY, 7)));
        }
    };
}
