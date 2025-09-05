public abstract class Stone {
    int price;

    public abstract void doSomething();

    Stone(int price){
        this.price = price;
    }

    Stone(){
        price = 0;
    }
}
