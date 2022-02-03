package exceptions;

public class ExceptionMachineRunner
{
    public static void main(String[] args)
    {
        var exceptionMachine = new ExceptionMachine();

//        exceptionMachine.rethrowException();

        System.out.println(exceptionMachine.finallyWithReturn());
//
//        exceptionMachine.tryWithResources();
    }
}
