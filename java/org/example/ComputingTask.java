package org.example;

import org.example.game.Field;

import java.util.concurrent.RecursiveTask;

public class ComputingTask extends RecursiveTask<Field> {
    private int dx, dy, x, y;
    private Field previousField;
    private Field currentField;

    public ComputingTask(int dx, int dy, Field previousField, Field curentField) {
        this.dx = dx;
        this.dy = dy;
        this.previousField = previousField;
        this.currentField = curentField;
        x = dx;
        y = dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setPreviousField(Field previousField) {
        this.previousField = previousField;
    }

    public void setCurrentField(Field currentField) {
        this.currentField = currentField;
    }/*

    @Override
    protected Field compute() {
        for (int x = dx; x < currentField.getWidth(); x += 3) {
            for (int y = dy; y < currentField.getHeight(); y += 3) {
                currentField.setSell(y, x, getNewValue(y, x));
                System.out.println("Current thread: " + Thread.currentThread().getName() + ", it - " + (3*dx + dy));
            }
        }
        return currentField;
    }*/

    @Override
    protected Field compute() {
        System.out.println("Current thread: " + Thread.currentThread().getName() + ", it - " + (3*dx + dy));
        currentField.setSell(y, x, getNewValue(y, x));
        if (x + 3 >= currentField.getWidth()) {
            x = dx;
            if (y + 3 >= currentField.getHeight()) return currentField;
            y += 3;
        } else {
            x += 3;
        }
        return compute();
    }

    private int getNewValue(int y, int x) {
        int sum = previousField.getNeighborsSum(y, x);
        if (sum == 3) return 1;
        if (sum == 2) return previousField.getSell(y, x);
        return 0;
    }
}
