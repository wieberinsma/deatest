package exceptions;

public class EndOfTheWorldException extends RuntimeException
{
    public EndOfTheWorldException(String message, Throwable cause)
    {
        super("Something went wrong in the world: " + message, cause);
    }
}
