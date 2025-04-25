public class Meteorite extends Stone{

    Meteorite(int price) {
        super(price);
    }

    @Override
    public void doSomething() {
        System.out.println("Meteorite is falling");
    }
}
