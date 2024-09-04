package lambdas.bewd;

public class Main
{
    public static void main(String[] args)
    {
        Cat cat = new Cat("Felix");
        print(prefix -> System.out.println(prefix + " " + cat.getName()));

    }

    public static void print(Printable printable) {
        printable.print("Hello");
    }
}
