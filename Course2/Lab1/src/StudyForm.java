public enum StudyForm{
    OFFLINE("Очно"),
    ONLINE("Дмстанционно");

    private final String name;

    StudyForm(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
