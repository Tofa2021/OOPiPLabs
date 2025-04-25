import java.io.Serializable;
import java.util.Objects;

public abstract class Stone implements Serializable {
    private int price;
    private float weight;
    private float transparency;

    public Stone(int price, float weight, float transparency) {
        this.price = price;
        this.weight = weight;
        this.transparency = transparency;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getTransparency() {
        return transparency;
    }

    public void setTransparency(float transparency) {
        this.transparency = transparency;
    }

    @Override
    public String toString() {
        return "Название: " + getClass().getSimpleName() + " " +
                "Цена: " + price +
                ", Вес: " + weight +
                ", Прозрачность: " + transparency;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Stone stone = (Stone) o;
        return price == stone.price && Float.compare(weight, stone.weight) == 0 && Float.compare(transparency, stone.transparency) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, weight, transparency);
    }
}
