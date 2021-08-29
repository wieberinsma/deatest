package lambdas.person;

import java.time.LocalDate;
import java.time.Period;

public class Person
{
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;

    public boolean isOfGender(Gender gender)
    {
        return gender.equals(getGender());
    }

    public boolean isAdult(int adultAge)
    {
        Period age = Period.between(getBirthDate(), LocalDate.now());
        return age.getYears() >= adultAge;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getFirstName()
    {
        return firstName;
    }
}
