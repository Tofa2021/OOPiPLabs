import java.util.ArrayList;

public class Trainee {
    private ArrayList<CourseOrder> coursesOrders = new ArrayList<>();

    public ArrayList<CourseOrder> getCoursesOrders() {
        return coursesOrders;
    }

    public void addCourse(Course course, int duration, DayTime dayTime, StudyForm studyForm){
        CourseOrder courseOrder = new CourseOrder(this, course, duration, dayTime, studyForm);
        coursesOrders.add(courseOrder);
    }
}
