public abstract class Stone {
    private int price;

    Stone(int price){
        this.price = price;
    }

    public abstract void doSomething();

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
