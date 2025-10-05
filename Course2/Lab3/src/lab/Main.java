package lab;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        List<Integer> numbers = random.ints(9, -15, 16)
                .boxed()
                .toList();
        System.out.println("Исходные числа:");
        System.out.println(numbers);


        List<Integer> sortedNumbers = numbers.stream().sorted(Comparator.reverseOrder()).toList();
        System.out.println("Сортировка по убыванию:");
        System.out.println(sortedNumbers);

        List<Integer> filteredNumbers = numbers.stream().filter(num -> num >= 0).toList();
        System.out.println("Отфильтрованные числа:");
        System.out.println(filteredNumbers);

        List<String> stringNumbers = numbers.stream().map(String::valueOf).toList();
        System.out.println("Числа в виде строк:");
        System.out.println(stringNumbers);
    }
}