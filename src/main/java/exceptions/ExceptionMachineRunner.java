package exceptions;

public class ExceptionMachineRunner
{
    public static void main(String[] args)
    {
        var exceptionMachine = new ExceptionMachine();

        System.out.println(exceptionMachine.finallyWithReturn());
    }
}
