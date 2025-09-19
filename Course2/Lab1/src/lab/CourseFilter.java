package lab;

@FunctionalInterface
public interface CourseFilter {
    boolean filter(Course course);
}
