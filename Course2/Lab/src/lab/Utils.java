package lab;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
    public static Scanner scanner = new Scanner(System.in);

    public static <T extends Nameable> T select(T[] values) {
        for (int i = 0; i < values.length; i++) {
            System.out.print(i + 1);
            System.out.print(") ");
            System.out.println(values[i].getName());
        }
        int chosenIndex = scanBorderInt(1, values.length) - 1;
        return values[chosenIndex];
    }

    public static int scanBorderInt(int lowerBorder, int upperBorder) {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input >= lowerBorder && input <= upperBorder) {
                    return input;
                } else {
                    System.out.println("Вводимое число должно быть между " + lowerBorder + " и " + upperBorder);
                }
            } catch (InputMismatchException exception) {
                System.out.println("Ввод должны быть числом");
                scanner.next();
            }
        }
    }

    public static int scanInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("Ввод должны быть числом");
                scanner.next();
            }
        }
    }
}
