package lab;

public class ClientCourse {
    private final Course course;
    private final int monthDuration;

    public ClientCourse(Course course, int monthDuration) {
        this.course = course;
        this.monthDuration = monthDuration;
    }

    public int calculatePrice() {
        return course.getPricePerMonth() * monthDuration;
    }

    public Course getCourse() {
        return course;
    }

    public void printInfo() {
        course.printInfo();
        System.out.println("Количество месяцев: " + monthDuration);
    }
}
