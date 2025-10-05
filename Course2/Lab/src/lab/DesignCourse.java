package lab;

public class DesignCourse extends Course {
    private final String designApplication;

    public DesignCourse(String name, int pricePerMonth, StudyForm studyForm, DayTime dayTimes, String designApplication) {
        super(name, pricePerMonth, studyForm, dayTimes);
        this.designApplication = designApplication;
    }

    @Override
    public void printInfo() {
        System.out.println(getName());
        System.out.println("Цена за месяц: " + getPricePerMonth());
        System.out.println("Форма обучения: " + getStudyForm().getName());
        System.out.println("Время проведения: " + getDayTime().getName());
        System.out.println("Изучаемое программа для дизайна: " + designApplication);
    }
}
