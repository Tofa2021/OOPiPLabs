package lab;

public class EnrollRunnable implements Runnable {
    private final Client client;
    private final Course course;
    private final int duration;

    public EnrollRunnable(Client client, Course course, int duration) {
        this.client = client;
        this.course = course;
        this.duration = duration;
    }

    @Override
    public void run() {
        CoursesManager.enroll(client, course, duration);
    }
}
