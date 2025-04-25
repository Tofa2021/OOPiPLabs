public class MagicStone extends Stone{
    MagicStone(int price) {
        super(price);
    }

    @Override
    public void doSomething() {
        System.out.println("MagicStone makes magic");
    }
}