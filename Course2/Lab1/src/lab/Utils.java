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

        while (true) {
            System.out.print("Выберите: ");
            try {
                int chosenIndex = scanner.nextInt() - 1;
                if (chosenIndex < 0 || chosenIndex >= values.length) {
                    System.out.println("Неправильный ввод");
                } else {
                    return values[chosenIndex];
                }
            } catch (InputMismatchException exception) {
                System.out.println("Ввод должны быть числом");
                scanner.next();
            }
        }
    }
}
