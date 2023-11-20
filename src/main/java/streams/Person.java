package streams;

import java.time.LocalDate;

public class Person
{
    private String name;
    private LocalDate birthDate;

    public Person(String name, LocalDate birthDate)
    {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }

    @Override
    public String toString()
    {
        return "Person {" +
                    "name='" + name + '\'' +
                '}';
    }
}
