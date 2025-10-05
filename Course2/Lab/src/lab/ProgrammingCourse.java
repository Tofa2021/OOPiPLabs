package lab;

public class ProgrammingCourse extends Course {
    private final String language;

    public ProgrammingCourse(String name, int pricePerMonth, StudyForm studyForm, DayTime dayTime, String language) {
        super(name, pricePerMonth, studyForm, dayTime);
        this.language = language;
    }

    @Override
    public void printInfo() {
        System.out.println(getName());
        System.out.println("Цена за месяц: " + getPricePerMonth());
        System.out.println("Форма обучения: " + getStudyForm().getName());
        System.out.println("Время проведения: " + getDayTime().getName());
        System.out.println("Изучаемый язык программирования: " + language);
    }
}
