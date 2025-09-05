public class Gem extends Stone{

    Gem(int price) {
        super(price);
    }

    @Override
    public void doSomething() {
        System.out.println("Gem becomes more expensive");
    }
}
