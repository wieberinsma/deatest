package exceptions;

public class EndOfTheWorldException extends RuntimeException
{
    public EndOfTheWorldException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
