package Lab2.task3;

import java.util.ArrayList;


public class Warehouse {
    private final ArrayList<Cell> cells = new ArrayList<>();

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public void addGoodToCell(Cell cell, Good good) {
        if (good.getLength() > cell.getLength() || good.getWidth() > cell.getWidth() || good.getHeight() > cell.getHeight()) {
            throw new IllegalArgumentException("This good exceeds the cell's dimensions.");
        }

        cell.addGood(good);
    }

    public void printAllGoods() {
        for (Cell cell : cells) {
            if (cell.getGoods().isEmpty()) {
                System.out.println("The cell is empty.");
            } else {
                System.out.println("Cell contains:");
                for (Good good : cell.getGoods()) {
                    System.out.println(good);
                }
            }
        }
    }
}
