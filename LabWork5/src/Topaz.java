public class Topaz extends SemiPreciousStone {

    public Topaz(int price, float weight, float transparency, int magicPower) {
        super(price, weight, transparency, magicPower);
    }

    @Override
    public void doMagic() {
        System.out.println("Вы вылечились на " + super.getMagicPower());
    }


}
