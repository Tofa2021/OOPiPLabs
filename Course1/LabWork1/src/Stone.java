public class Stone {
    private int age;
    private String name;
    private double hardness;

    Stone(int age, String name, double hardness){
        this.age = age;
        this.name = name;
        this.hardness = hardness;
    }

    Stone(int age){
        this(age, "no name", 1.1);
    }

    Stone(int age, String name){
        this(age, name, 1.1);
    }
}
