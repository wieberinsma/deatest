package threads;

public class StringPrinter {
    public void printStrings() {
        for (int i = 0; i < 1000000000; i++) {
            System.out.println("" + i + "s");
        }
    }
}
