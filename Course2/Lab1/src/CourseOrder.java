public class CourseOrder {
    private Trainee trainee;
    private Course course;
    private int duration;
    private DayTime dayTime;
    private StudyForm studyForm;

    public CourseOrder(Trainee trainee, Course course, int duration, DayTime dayTime, StudyForm studyForm) {
        this.trainee = trainee;
        this.course = course;
        this.duration = duration;
        this.dayTime = dayTime;
        this.studyForm = studyForm;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public Course getCourse() {
        return course;
    }

    public int getDuration() {
        return duration;
    }

    public DayTime getDayTime() {
        return dayTime;
    }

    public StudyForm getStudyForm() {
        return studyForm;
    }

    public double calculateCoursePrice(){
        return course.getPricePerMonth() * duration;
    }

    public void printInfo() {
        course.printInfo();
        System.out.println("Срок (в месяцах): " + duration);
        System.out.println("Итого за курс: " + calculateCoursePrice());
    }
}
