package lambdas;

import lambdas.person.Gender;
import lambdas.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListMaker {

    private static final int ADULT_AGE = 18;

    private final Predicate<Person> isAdult = (person) -> person.isAdult(ADULT_AGE);
    private final Predicate<Person> isMale = (person) -> person.isOfGender(Gender.MALE);
    private final Predicate<Person> isFemale = (person) -> person.isOfGender(Gender.FEMALE);

    public List<Person> createMaleAdultList(List<Person> allPersons) {
        return createAdultList(allPersons, isMale);
    }

    public List<Person> createFemaleAdultList(List<Person> allPersons) {
        return createAdultList(allPersons, isFemale);
    }

    private List<Person> createAdultList(List<Person> allPersons, Predicate<Person> byGender) {
        if (allPersons == null) {
            return new ArrayList<>();
        }

        return allPersons.stream().filter(isAdult).filter(byGender).collect(Collectors.toList());
    }
}
