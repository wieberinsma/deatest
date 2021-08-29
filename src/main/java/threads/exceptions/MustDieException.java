package threads.exceptions;

public class MustDieException extends Exception
{
    public MustDieException(String message)
    {
        super(message);
    }
}