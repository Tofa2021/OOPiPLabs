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
        add(new ProgrammingCourse(
                "Курс С#",
                150,
                StudyForm.ONLINE,
                DayTime.EVENING,
                "Java"
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

    public static ArrayList<Course> getAvailableCourses(Client client, StudyForm studyForm, DayTime dayTime) {
        return filterCourses(course -> course.isAvailable(studyForm, dayTime) && !course.isEnrolled(client));
    }

    public static ArrayList<Course> filterCourses(CourseFilter courseFilter) {
        ArrayList<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (courseFilter.filter(course)) {
                result.add(course);
            }
        }
        return result;
    }
}
