import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = -1;
        Necklace necklace = new Necklace();
        boolean isBreak = false;

        while (true) {
            if (isBreak)
                break;

            System.out.print("""
                    1)Добавить камень в ожерелье
                    2)Выбрать камень из католога
                    3)Добавить в католог
                    4)Просмотр ожерелья
                    5)Очистить ожерелье
                    6)Общая стоимость
                    7)Общий вес
                    8)Найти камни в ожерелье, соответствующие заданному диапазону прозрачности
                    9)Отсортировать камни по ценности по убыванию
                    10)Отсортировать камни по ценности по возрастанию
                    11)Запись в файл
                    12)Чтение с файла
                    13)Выход
                    """);
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Некоректный ввод");
                continue;
            }
            switch (choice) {
                case 1 -> {
                    try {
                        necklace.addStone(makeStone());
                        System.out.println("Камень добавлен");
                    } catch (StoneTypeNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        scanner.next();
                        System.out.println("Некоректный ввод");
                    }
                }
                case 2 -> {
                    ArrayList<Stone> stonesCatalog = new ArrayList<>();
                    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("StoneCatalog.txt"))) {
                        stonesCatalog = (ArrayList<Stone>) objectInputStream.readObject();
                    } catch (EOFException e) {
                        System.out.println("Каталог пуст");
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Выберите камень");
                    int i = 1;
                    for (var stone : stonesCatalog)
                        System.out.println(i++ + ")" + stone);
                    try {
                        choice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        scanner.next();
                        System.out.println("Некоректный ввод");
                    }
                    if (choice <= 0 || choice > stonesCatalog.size()) {
                        System.out.println("Выход за допустимые границы");
                        break;
                    }
                    necklace.addStone(stonesCatalog.get(choice - 1));
                    System.out.println("Камень добавлен");
                }
                case 3 -> {
                    ArrayList<Stone> stonesCatalog = new ArrayList<>();
                    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("StoneCatalog.txt"))) {
                        stonesCatalog = (ArrayList<Stone>) objectInputStream.readObject();
                    } catch (EOFException e) {
                        System.out.println("Каталог пуст");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    try {
                        stonesCatalog.add(makeStone());
                        System.out.println("Камень добавлен");
                    } catch (StoneTypeNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        scanner.next();
                        System.out.println("Некоректный ввод");
                    }
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("StoneCatalog.txt"))) {
                        objectOutputStream.writeObject(stonesCatalog);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case 4 -> necklace.show();
                case 5 -> necklace.clear();
                case 6 -> System.out.println("Общая стоимость: " + necklace.calculatePrice());
                case 7 -> System.out.println("Общий вес: " + necklace.calculateWeight());
                case 8 -> {
                    try {
                        System.out.println("Введите нижнюю границу");
                        var lowerBound = scanner.nextFloat();
                        if (lowerBound < 0) {
                            System.out.println("Это значение не может быть отрицательным");
                            break;
                        }
                        System.out.println("Введите верхнюю границу");
                        var upperBound = scanner.nextFloat();
                        if (upperBound < 0) {
                            System.out.println("Это значение не может быть отрицательным");
                            break;
                        }
                        if (lowerBound > upperBound) {
                            System.out.println("Верхняя граница ниже верхней");
                            break;
                        }
                        System.out.println(necklace.filterStonesByTransparency(lowerBound, upperBound)
                                .stream()
                                .map(Stone::toString)
                                .reduce((a, b) -> a + "\n" + b).orElse("Таких камней нет"));
                    } catch (InputMismatchException e) {
                        System.out.println("Некоректный ввод");
                        scanner.next();
                    }
                }
                case 9 -> necklace.sortByPriceDescending();
                case 10 -> necklace.sortByPriceAscending();
                case 11 -> {
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("NecklaceStones.txt"))) {
                        objectOutputStream.writeObject(necklace.getNecklace());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case 12 -> {
                    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("NecklaceStones.txt"))) {
                        ArrayList<Stone> stones = (ArrayList<Stone>) objectInputStream.readObject();
                        necklace.addStones(stones);
                    } catch (EOFException e) {
                        System.out.println("Файл пуст");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case 13 -> isBreak = true;
                default -> System.out.println("Невозможное значение");
            }
        }
    }

    public static int price() {
        System.out.println("Введите стоимость");
        return scanner.nextInt();
    }

    public static float weight() {
        System.out.println("Введите вес");
        return scanner.nextFloat();
    }

    public static float transparency() {
        System.out.println("Введите прозрачность");
        return scanner.nextFloat();
    }

    public static int magicPower() {
        System.out.println("Введите магическую силу");
        return scanner.nextInt();
    }

    public static Stone makeStone() throws StoneTypeNotFoundException, InputMismatchException {
        System.out.print("""
                Выберите тип камня
                1)Алмаз
                2)Аметист
                3)Рубин
                4)Топаз
                """);

        return switch (scanner.nextInt()) {
            case 1 -> new Diamond(price(), weight(), transparency());
            case 2 -> new Amethyst(price(), weight(), transparency(), magicPower());
            case 3 -> new Ruby(price(), weight(), transparency());
            case 4 -> new Topaz(price(), weight(), transparency(), magicPower());
            default -> throw new StoneTypeNotFoundException("Данный тип камня не найден");
        };
    }
}