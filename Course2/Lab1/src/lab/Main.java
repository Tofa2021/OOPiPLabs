package lab;

import java.util.ArrayList;

import static lab.Utils.scanner;

public class Main {
    public static void main(String[] args) {
        Client client = new Client("Name");

        outer:
        while (true) {
            System.out.println("1) Добавить курс");
            System.out.println("2) Расчитать стоимость всех курсов");
            System.out.println("3) Выход");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Выберите время проведения");
                    DayTime dayTime = Utils.select(DayTime.values());

                    System.out.println("Выберите форму обученяи");
                    StudyForm studyForm = Utils.select(StudyForm.values());

                    System.out.println("Выберите курс");
                    ArrayList<Course> availableCourses = CoursesManager.getAvailableCourses(client, studyForm, dayTime);
                    Course selectedCourse;
                    if (availableCourses.isEmpty()) {
                        System.out.println("Нет подходящих курсов");
                        break;
                    } else {
                        selectedCourse = Utils.select(availableCourses.toArray(new Course[0]));
                    }

                    System.out.println("Сколько месяцев вы хотите заниматься?");
                    int monthDuration = scanner.nextInt();

                    CoursesManager.enroll(client, selectedCourse, monthDuration);
                    break;
                case 2:
                    client.printCoursesInfo();
                    System.out.println("Стоимость всех курсов, на которые вы записались: " + client.calculateTotalPrice());
                    break;
                case 3:
                    break outer;
            }
        }
    }
}