public class Gem extends Stone{
    private int price;

    Gem(int price) {
        super(price);
    }

    Gem(){
        super();
    }

    @Override
    public void doSomething() {
        System.out.println("Gem becomes more expensive");
    }


}
