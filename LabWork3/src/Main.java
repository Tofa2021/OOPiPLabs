public class Main {
    public static int sum(int a, int b){
        return a + b;
    }

    public static int sum(int a, int b, int c){
        return a + b + c;
    }

    public static void main(String[] args) {
        Gem gem = new Gem(1000);
        MagicStone magicStone= new MagicStone(120);
        Meteorite meteorite = new Meteorite(10);
        Gem gem2 = new Gem();
        gem.doSomething();
        magicStone.doSomething();
        meteorite.doSomething();
        System.out.println("Sum a and b: " + sum(10, 2));
        System.out.println("Sum a and b and c: " + sum(4, 32, 5));
    }
}