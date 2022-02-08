package junit;

public class Division
{
    private final int number;

    private final int divisionNumber;

    private final String word;

    public Division(int number, int divisionNumber, String word)
    {
        this.number = number;
        this.divisionNumber = divisionNumber;
        this.word = word;
    }

    public boolean isNumberDivisible()
    {
        return number % divisionNumber == 0;
    }

    public String getWord()
    {
        return word;
    }

    public int getNumber()
    {
        return number;
    }
}
