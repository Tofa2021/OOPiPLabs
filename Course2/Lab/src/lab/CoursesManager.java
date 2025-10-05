package lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoursesManager {
    private static final List<Course> courses = new ArrayList<>() {{
        add(new ProgrammingCourse(
                "Курс Python",
                120,
                StudyForm.OFFLINE,
                DayTime.AFTERNOON,
                "Python"
        ));
        add(new ProgrammingCourse(
                "Курс Java",
                100,
                StudyForm.ONLINE,
                DayTime.EVENING,
                "Java"
        ));
        add(new ProgrammingCourse(
                "Курс C#",
                150,
                StudyForm.ONLINE,
                DayTime.EVENING,
                "C#"
        ));
        add(new DesignCourse(
                "Курс дизайна в Figma",
                85,
                StudyForm.ONLINE,
                DayTime.MORNING,
                "Figma"
        ));
        add(new DesignCourse(
                "Курс дизайна",
                65,
                StudyForm.ONLINE,
                DayTime.EVENING,
                "Canva"
        ));
    }};

    public static void enroll(Client client, Course course, int monthDuration) {
        course.enroll(client);
        client.addClientCourse(course, monthDuration);
    }

    public static List<Course> getAvailableCourses(Client client, StudyForm studyForm, DayTime dayTime) {
        return courses.stream()
                .filter(course -> course.isAvailable(studyForm, dayTime) && !course.isEnrolled(client))
                .toList();
    }

    public static List<Course> getAllCourses() {
        return courses;
    }

    public static Course findByName(String name) {
        return courses.stream()
                .filter(course -> course.getName().equalsIgnoreCase(name.trim()))
                .findFirst()
                .orElse(null);
    }

    public static Map<String, List<Course>> groupByType() {
        return courses.stream()
                .collect(Collectors.groupingBy(course -> course.getClass().getSimpleName()));
    }

    public static List<Course> getCoursesByMaxPrice(int maxPrice) {
        return courses.stream()
                .filter(course -> course.getPricePerMonth() <= maxPrice)
                .toList();
    }

    public static List<String> getCoursePriceInfo() {
        return courses.stream()
                .map(course -> course.getName() + ": " + course.getPricePerMonth() + " руб.")
                .toList();
    }

}
