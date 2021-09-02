package junit;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class NextLevelExceptions
{
    public static void main(String[] args)
    {
        exceptionThrowing();
    }

    public static void exceptionThrowing() {
        ByteArrayInputStream bais = new ByteArrayInputStream("Test".getBytes());
        var contents = bais.read();

        try
        {
            var divide = contents / 0;
        }
        catch (NullPointerException ex) {
            System.out.println("Exception thrown!");
        }
        finally
        {
            try
            {
                bais.close();
                System.out.println("Safely closing the InputStream");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        System.out.println("Last statement in method call");
    }
}
