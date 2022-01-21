package src.dojo2backup;

import java.util.Scanner;

abstract public class BaseClass
{
    protected static Scanner scanner = new Scanner(System.in);

    protected void output(String output)
    {
        System.out.println(output);
    }
}
