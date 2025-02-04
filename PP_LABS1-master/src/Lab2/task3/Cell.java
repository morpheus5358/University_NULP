package Lab2.task3;
import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final List<Good> goods;
    private final double length;
    private final double width;
    private final double height;

    public Cell(double length, double width, double height) {
        if (length <= 0 || width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Cell dimensions must be positive.");
        }
        this.length = length;
        this.width = width;
        this.height = height;
        this.goods = new ArrayList<>();
    }

    public double getCurrentGoodsVolume() {
        double totalVolume = 0;
        for (Good good : goods) {
            totalVolume += good.getVolume();
        }
        return totalVolume;
    }

    public void addGood(Good good) {
        if (goods.size() >= 5) {
            throw new IllegalArgumentException("The cell has reached the item limit.");
        }
        if (getCurrentGoodsVolume() + good.getVolume() > this.getVolume()) {
            throw new IllegalArgumentException("The cell doesn't have enough capacity for this good.");
        }
        goods.add(good);
    }

    public List<Good> getGoods() {
        return goods;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getVolume() {
        return length * width * height;
    }
}
