package generics;

import java.util.ArrayList;
import java.util.List;

public class NotReallyGenerics
{
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException
    {
        List<String> strings = new ArrayList<>();
        strings.add("Definately a String");
        strings.add("Also a String");

        // Demonstrate type erasure from internal array using array copy
        System.out.println(strings.toArray()[0].getClass().getSimpleName());

        // Add breakpoint, evaluate 'strings.add(new Integer(123));'. Below prints fine, ClassCastException in for-loop
        System.out.println(strings);

        for (String s : strings)
        {
            System.out.println(s);
        }
    }
}
