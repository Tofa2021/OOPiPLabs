package lab;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {

            System.out.println("Введите ваше имя");
            Client client = new Client(Utils.scanner.next());

            outerLoop:
            while (true) {
                System.out.println("1) Добавить курс");
                System.out.println("2) Расчитать стоимость выбранных курсов");
                System.out.println("3) Сортировать курсы по возрастанию");
                System.out.println("4) Сортировать курсы по убыванию");
                System.out.println("5) Просмотр всех доступных курсов");
                System.out.println("6) Добавить курс по названию");
                System.out.println("7) Просмотр всех курсов, сгруппированных по типу");
                System.out.println("8) Найти курсы до определенной цены");
                System.out.println("9) Просмотр цены курсов");
                System.out.println("10) Выход");
                int monthDuration;
                int choice = Utils.scanInt();
                switch (choice) {
                    case 1:
                        System.out.println("Выберите время проведения");
                        DayTime dayTime = Utils.select(DayTime.values());

                        System.out.println("Выберите форму обученяи");
                        StudyForm studyForm = Utils.select(StudyForm.values());

                        List<Course> availableCourses = CoursesManager.getAvailableCourses(client, studyForm, dayTime);
                        if (availableCourses.isEmpty()) {
                            System.out.println("Нет подходящих курсов");
                            break;
                        }

                        System.out.println("Выберите курс");
                        Course selectedCourse = Utils.select(availableCourses.toArray(new Course[0]));

                        System.out.println("Сколько месяцев вы хотите заниматься?");
                        monthDuration = Utils.scanBorderInt(0, 24);

                        executorService.execute(new EnrollRunnable(client, selectedCourse, monthDuration));
                        break;
                    case 2:
                        client.printCoursesInfo();
                        System.out.println("Стоимость всех курсов, на которые вы записались: " + client.calculateTotalPrice());
                        break;
                    case 3:
                        executorService.execute(
                                new SortRunnable(
                                        CoursesManager.getAllCourses(),
                                        Comparator.comparingInt(Course::getPricePerMonth)
                                )
                        );
                        break;
                    case 4:
                        executorService.execute(
                                new SortRunnable(
                                        CoursesManager.getAllCourses(),
                                        Comparator.comparingInt(Course::getPricePerMonth).reversed()
                                )
                        );
                        break;
                    case 5:
                        for (var course : CoursesManager.getAllCourses()) {
                            course.printInfo();
                        }
                        break;
                    case 6:
                        System.out.println("Введите название курса:");
                        Utils.scanner.nextLine();
                        String name = Utils.scanner.nextLine();
                        Course foundCourse = CoursesManager.findByName(name);
                        if (foundCourse == null) {
                            System.out.println("Нет курса с таким названием");
                            break;
                        }

                        foundCourse.printInfo();
                        System.out.println("Сколько месяцев вы хотите заниматься?");
                        monthDuration = Utils.scanBorderInt(0, 24);

                        executorService.execute(new EnrollRunnable(client, foundCourse, monthDuration));
                        break;
                    case 7:
                        Map<String, List<Course>> groupedCourses = CoursesManager.groupByType();
                        groupedCourses.forEach((type, courses) -> {
                            System.out.println(type);
                            courses.forEach(Course::printInfo);
                            System.out.println();
                        });
                        break;
                    case 8:
                        System.out.println("Введите максимальную стоимость курса:");
                        Utils.scanner.nextLine();
                        int maxPrice = Utils.scanBorderInt(0, 1000);

                        CoursesManager.getCoursesByMaxPrice(maxPrice).forEach(Course::printInfo);
                        break;
                    case 9:
                        CoursesManager.getCoursePriceInfo().forEach(System.out::println);
                        break;
                    case 10:
                        break outerLoop;
                    default:
                        System.out.println("Невозможное значение");
                }
            }
        }
    }
}