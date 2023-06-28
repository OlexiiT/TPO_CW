package org.example.Processors;

import org.example.game.Field;

public class SimpleProcessor {
    private Field previousField;
    private Field currentField;

    public SimpleProcessor(Field previousField) {
        this.previousField = previousField;
    }

    public void start() {
        start(100);
    }

    public void start(int iterations) {
        int c = 0;
        //System.out.println(previousField);
        while (c < iterations) {
            iterate();
            //System.out.println(previousField);
            c++;
        }
    }

    private void iterate() {
        currentField = new Field(previousField.getHeight(), previousField.getWidth());
        for (int y = 0; y < currentField.getHeight(); y++) {
            for (int x = 0; x < currentField.getWidth(); x++) {
                currentField.setSell(y, x, getNewValue(y, x));
            }
        }
        previousField = currentField;
    }

    private int getNewValue(int y, int x) {
        int sum = previousField.getNeighborsSum(y, x);
        if (sum == 3) return 1;
        if (sum == 2) return previousField.getSell(y, x);
        return 0;
    }
}
