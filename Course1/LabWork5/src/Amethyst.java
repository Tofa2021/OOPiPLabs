public class Amethyst extends SemiPreciousStone {

    public Amethyst(int price, float weight, float transparency, int magicPower) {
        super(price, weight, transparency, magicPower);
    }

    @Override
    public void doMagic() {
        System.out.println("Ваша удача повышена на " + super.getMagicPower());
    }

    public void attractLuck() {
        System.out.println("Удача тянется к вам.");
    }
}
