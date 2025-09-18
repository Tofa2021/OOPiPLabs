package lab;

import java.util.ArrayList;

public class CoursesManager {
    private static final ArrayList<Course> courses = new ArrayList<>() {{
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
        add(new DesignCourse(
                "Курс дизайна",
                65,
                StudyForm.ONLINE,
                DayTime.MORNING,
                "Figma"
        ));
    }};

    public static void enroll(Client client, Course course, int monthDuration) {
        course.enroll(client);
        client.addClientCourse(course, monthDuration);
    }

    public static ArrayList<Course> getAvailableCourses(Client client, StudyForm studyForm, DayTime dayTime) {
        ArrayList<Course> availableCourses = new ArrayList<>();
        for (var course : courses) {
            if (course.isAvailable(studyForm, dayTime) && !course.isEnrolled(client)) {
                availableCourses.add(course);
            }
        }
        return availableCourses;
    }
}
