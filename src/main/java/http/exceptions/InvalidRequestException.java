package http.exceptions;

public class InvalidRequestException extends Throwable
{
    public InvalidRequestException(String message)
    {
        super(message);
    }
}
