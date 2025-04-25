public abstract class SemiPreciousStone extends Stone {
    private int magicPower;

    public SemiPreciousStone(int price, float weight, float transparency, int magicPower) {
        super(price, weight, transparency);
        this.magicPower = magicPower;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Магическая сила: " + magicPower;

    }

    public int getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(int magicPower) {
        this.magicPower = magicPower;
    }

    public abstract void doMagic();
}
