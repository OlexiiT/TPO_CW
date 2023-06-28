package org.example.game;

import java.util.Arrays;
import java.util.Random;

public class Field {
    private int height = 8;
    private int width = 8;
    private int[][] field = new int[64][64];

    public Field() {
    }

    public Field(int height, int width) {
        this.height = height;
        this.width = width;
        this.field = new int[height][width];
    }

    public int getSell(int y, int x) {
        if (y < 0 || x < 0 || y >= height || x >= width) {
            return 0;
        }
        return field[y][x];
    }

    public int getNeighborsSum(int y, int x) {
        if (y < 0 || y >= height || x < 0 || x >= width) return -1;
        return  getSell(y - 1, x - 1) + getSell(y - 1, x) + getSell(y - 1, x + 1) +
                getSell(y, x - 1)        +                        getSell(y, x + 1)        +
                getSell(y + 1, x - 1) + getSell(y + 1, x) + getSell(y + 1, x + 1);
    }

    public void setSell(int y, int x, int value) {
        if (value != 0 && value != 1) return;
        if (y < 0 || x < 0 || y >= height || x >= width) {
            return;
        }
        field[y][x] = value;
    }

    public void randomFill(double percents) {
        Random random = new Random(55);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                field[y][x] = random.nextDouble() > percents ? 1 : 0;
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private String sellToString(int v) {
        if (v == 1) return "▉";
        return " ";
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //buffer.append(sellToString(field[y][x]));
                buffer.append(field[y][x]);
                if (x == width - 1) buffer.append("\n");
                else buffer.append(" ");
            }
        }
        return buffer.toString();
    }
}
