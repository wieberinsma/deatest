package exceptions;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExceptionMachine
{
    public void rethrowException()
    {
        try
        {
            interrupt();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public String finallyWithReturn() {
        System.out.println("Booting machine...");

        try {
            System.out.println("Increasing heat...");
            explode();

            return "System has exploded!";
        }
        catch(IllegalArgumentException ex) {
            System.out.println("Unable to recover...");
        }
//        catch(ExplosionException ex) {
//            throw new EndOfTheWorldException(ex.getMessage(), ex.getCause());
//        }
        finally
        {
            System.out.println("Shutdown initiated...");
        }

        return "All good!";
    }

    private void explode()
    {
        throw new ExplosionException("Heat too intense, EXPLOSION!!");
    }

    public void tryWithResources() {
        try (var fis = new FileInputStream(""); var bis = new BufferedInputStream(fis))
        {
            if (fis.read() == -1 || bis.read() == -1) {
                throw new FileNotFoundException();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void interrupt() throws InterruptedException
    {
        throw new InterruptedException("INTERRUPT!");
    }

}
