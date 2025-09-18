package lab;

public enum DayTime implements Nameable {
    MORNING("Утро"),
    AFTERNOON("День"),
    EVENING("Вечер"),
    ;

    private final String name;

    DayTime(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
