import javax.swing.text.Style;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new LanguageCourse("Языковые курсы",
                                        250,
                                        new ArrayList<StudyForm>() {{
                                            add(StudyForm.OFFLINE);
                                            add(StudyForm.ONLINE);
                                        }},
                                        new ArrayList<DayTime>() {{
                                            add(DayTime.MORNING);
                                            add(DayTime.AFTERNOON);
                                        }},
                                        "Английский",
                                        "Intermediate"));

        System.out.println("Выберите время проведения");
        System.out.println("1) Утро");
        System.out.println("2) День");
        System.out.println("3) Вечер");
        int dayTimesIndex = scanner.nextInt();
        DayTime dayTime = DayTime.MORNING; //
        switch (dayTimesIndex){
            case 1:
                dayTime = DayTime.MORNING;
                break;
            case 2:
                dayTime = DayTime.AFTERNOON;
                break;
            case 3:
                dayTime = DayTime.EVENING;
                break;
            default:
                System.out.println("Ran out of range");
        }

        System.out.println("Выберите форму обученяи");
        System.out.println("1) Очно");
        System.out.println("2) Дистанционно");
        int studyFormIndex = scanner.nextInt();
        StudyForm studyForm = StudyForm.OFFLINE; //
        switch (studyFormIndex){
            case 1:
                studyForm = StudyForm.OFFLINE;
                break;
            case 2:
                studyForm = StudyForm.ONLINE;
                break;
            default:
                System.out.println("Ran out of range");
        }

        StudyForm finalStudyForm = studyForm;
        DayTime finalDayTime = dayTime;
        List<Course> filteredCourses = courses.stream()
                                                   .filter(course -> course.checkAvailability(finalStudyForm, finalDayTime))
                                                   .toList();
        if (filteredCourses.isEmpty()){
            System.out.println("Нет подходящих курсов");
            return;
        }

        for (int i = 0; i < filteredCourses.size(); i++){
            System.out.println((i + 1) + ". ");
            filteredCourses.get(i).printInfo();
        }

        System.out.println("Введите номер курса");
        int courseIndex = scanner.nextInt() - 1;
        Course chosenCourse = filteredCourses.get(courseIndex);

        System.out.println("Введите срок обучения (количество месяцев)");
        int duration = scanner.nextInt();
        if (duration < 0){
            System.out.println("Invalid duration");
            duration = 0;
        }

        Trainee trainee = new Trainee();
        trainee.addCourse(chosenCourse, duration, dayTime, studyForm);

        for (var courseOrder : trainee.getCoursesOrders()){
            courseOrder.printInfo();
        }
    }
}