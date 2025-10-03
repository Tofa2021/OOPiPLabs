package lab;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        System.out.println("Введите ваше имя");
        Client client = new Client(Utils.scanner.next());

        outerLoop:
        while (true) {
            System.out.println("1) Добавить курс");
            System.out.println("2) Расчитать стоимость всех курсов");
            System.out.println("3) Сортировать курсы по возрастанию");
            System.out.println("4) Сортировать курсы по убыванию");
            System.out.println("5) Просмотр всех доступных курсов");
            System.out.println("6) Выход");
            int choice = Utils.scanInt();
            switch (choice) {
                case 1:
                    System.out.println("Выберите время проведения");
                    DayTime dayTime = Utils.select(DayTime.values());

                    System.out.println("Выберите форму обученяи");
                    StudyForm studyForm = Utils.select(StudyForm.values());

                    System.out.println("Выберите курс");
                    List<Course> availableCourses = CoursesManager.getAvailableCourses(client, studyForm, dayTime);
                    Course selectedCourse;
                    if (availableCourses.isEmpty()) {
                        System.out.println("Нет подходящих курсов");
                        break;
                    } else {
                        selectedCourse = Utils.select(availableCourses.toArray(new Course[0]));
                    }

                    System.out.println("Сколько месяцев вы хотите заниматься?");
                    int monthDuration = Utils.scanBorderInt(0, 24);

                    CoursesManager.enroll(client, selectedCourse, monthDuration);
                    break;
                case 2:
                    client.printCoursesInfo();
                    System.out.println("Стоимость всех курсов, на которые вы записались: " + client.calculateTotalPrice());
                    break;
                case 3:
                    executorService.submit(
                            new SortRunnable(
                                    CoursesManager.getAllCourses(),
                                    Comparator.comparingInt(Course::getPricePerMonth)
                            )
                    );
                    break;
                case 4:
                    executorService.submit(
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
                    break outerLoop;
                default:
                    System.out.println("Невозможное значение");
            }
        }
    }
}