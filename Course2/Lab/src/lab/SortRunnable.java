package lab;

import java.util.Comparator;
import java.util.List;

class SortRunnable implements Runnable {
    private final List<Course> courses;
    private final Comparator<Course> comparator;

    SortRunnable(List<Course> courses, Comparator<Course> comparator) {
        this.courses = courses;
        this.comparator = comparator;
    }

    @Override
    public void run() {
        synchronized (courses) {
            courses.sort(comparator);
        }
    }
}
