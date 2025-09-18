package lab;

import java.util.ArrayList;

public class Client implements Nameable {
    private final ArrayList<ClientCourse> courses = new ArrayList<>();
    private final String name;

    public Client(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void addClientCourse(Course course, int monthDuration) {
        courses.add(new ClientCourse(course, monthDuration));
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (var course : courses) {
            totalPrice += course.calculatePrice();
        }
        return totalPrice;
    }

    public void printCoursesInfo() {
        for (var clientCourse : courses) {
            clientCourse.printInfo();
        }
    }
}
