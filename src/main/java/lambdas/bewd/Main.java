package lambdas.bewd;

public class Main
{
    public static void main(String[] args)
    {
        Cat cat = new Cat("Felix");

        printCatName(prefix -> System.out.println(prefix + " " + cat.getName()));
    }

    public static void printCatName(Printable printable) {
        printable.print("Hello");
    }
}
