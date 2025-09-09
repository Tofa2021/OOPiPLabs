import java.util.ArrayList;

public class LanguageCourse extends Course{
    private String language;
    private String level;

    public LanguageCourse(String name, int basePricePerMonth, ArrayList<StudyForm> availableStudyForms, ArrayList<DayTime> availableDayTimes, String language, String level) {
        super(name, basePricePerMonth, availableStudyForms, availableDayTimes);
        this.language = language;
        this.level = level;
    }

    @Override
    public void printInfo() {
        System.out.println("Курс " + getName());
        System.out.println("Язык: " + language);
        System.out.println("Уровень знания: " + level);
        System.out.println("Форма обучения: " + getAvailableStudyFormsString());
        System.out.println("Времени проведения: " + getAvailableDayTimesString());
        System.out.println("Стоимость за месяц: " + getPricePerMonth());
    }
}
