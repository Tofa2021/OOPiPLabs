package lab;

import java.util.ArrayList;

public abstract class Course implements Nameable {
    private final String name;
    private final int pricePerMonth;
    private final StudyForm studyForm;
    private final DayTime dayTime;
    private final ArrayList<Client> clients = new ArrayList<>();

    public Course(String name, int pricePerMonth, StudyForm studyForm, DayTime dayTimes) {
        this.name = name;
        this.pricePerMonth = pricePerMonth;
        this.studyForm = studyForm;
        this.dayTime = dayTimes;
    }

    public String getName() {
        return name;
    }

    public int getPricePerMonth() {
        return pricePerMonth;
    }

    public StudyForm getStudyForm() {
        return studyForm;
    }

    public DayTime getDayTime() {
        return dayTime;
    }

    public boolean isAvailable(StudyForm studyForm, DayTime dayTime) {
        return this.studyForm == studyForm && this.dayTime == dayTime;
    }

    public void enroll(Client client) {
        clients.add(client);
    }

    public boolean isEnrolled(Client client) {
        return clients.contains(client);
    }

    public abstract void printInfo();
}
