import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Necklace implements Serializable {
    private final ArrayList<Stone> necklace = new ArrayList<>();

    public void addStone(Stone stone) {
        necklace.add(stone);
    }

    public void clear() {
        necklace.clear();
    }

    public int calculatePrice() {
        int allPrice = 0;

        for (var stone : necklace)
            allPrice += stone.getPrice();

        return allPrice;
    }

    public float calculateWeight() {
        float allWeight = 0f;

        for (var stone : necklace)
            allWeight += stone.getWeight();

        return allWeight;
    }

    public void sortByPriceDescending() {
        necklace.sort(Comparator.comparingInt(Stone::getPrice).reversed());
    }

    public void sortByPriceAscending() {
        necklace.sort(Comparator.comparingInt(Stone::getPrice));
    }

    public List<Stone> filterStonesByTransparency(float lowerBound, float upperBound) {
        return necklace.stream()
                .filter(stone -> stone.getTransparency() >= lowerBound && stone.getTransparency() <= upperBound)
                .toList();
    }

    public void show() {
        System.out.println("Камни в ожерелье:\n" + necklace.stream()
                .map(Stone::toString)
                .reduce((a, b) -> a + "\n" + b)
                .orElse("Ожерелье пустое"));
    }

    public ArrayList<Stone> getNecklace() {
        return necklace;
    }

    public void addStones(ArrayList<Stone> stones) {
        necklace.addAll(stones);
    }
}
