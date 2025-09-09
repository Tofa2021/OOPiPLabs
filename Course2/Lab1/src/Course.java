import java.util.ArrayList;

public abstract class Course {
    private String name;
    private int pricePerMonth;
    private ArrayList<StudyForm> availableStudyForms = new ArrayList<>();
    private ArrayList<DayTime> availableDayTimes = new ArrayList<>();

    public ArrayList<StudyForm> getAvailableStudyForms() {
        return availableStudyForms;
    }

    public ArrayList<DayTime> getAvailableDayTimes() {
        return availableDayTimes;
    }

    public String getAvailableStudyFormsString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (StudyForm studyForm : availableStudyForms){
            stringBuilder.append(studyForm.getName()).append(", ");
        }
        return stringBuilder.toString();
    }

    public String getAvailableDayTimesString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (DayTime dayTime : availableDayTimes){
            stringBuilder.append(dayTime.getName()).append(", ");
        }
        return stringBuilder.toString();
    }

    public Course(String name, int pricePerMonth, ArrayList<StudyForm> availableStudyForms, ArrayList<DayTime> availableDayTimes) {
        this.name = name;
        this.pricePerMonth = pricePerMonth;
        this.availableStudyForms = availableStudyForms;
        this.availableDayTimes = availableDayTimes;
    }

    public String getName() {
        return name;
    }

    public int getPricePerMonth() {
        return pricePerMonth;
    }

    public boolean checkAvailability(StudyForm studyForm, DayTime dayTime){
        return availableStudyForms.contains(studyForm) && availableDayTimes.contains(dayTime);
    }

    public abstract void printInfo();
}
