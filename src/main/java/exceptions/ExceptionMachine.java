package exceptions;

public class ExceptionMachine
{
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
}
