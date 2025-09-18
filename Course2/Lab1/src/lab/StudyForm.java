package lab;

public enum StudyForm implements Nameable {
    OFFLINE("Очно"),
    ONLINE("Дистанционно"),
    ;

    private final String name;

    StudyForm(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
