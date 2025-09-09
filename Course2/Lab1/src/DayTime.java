public enum DayTime {
    MORNING("Утро"),
    AFTERNOON("День"),
    EVENING("Вечер");

    private final String name;

    DayTime(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
