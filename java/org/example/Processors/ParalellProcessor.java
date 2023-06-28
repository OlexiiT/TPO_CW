package org.example.Processors;

import org.example.ComputingTask;
import org.example.game.Field;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ParalellProcessor {
    private Field previousField;
    private Field currentField;
    private ForkJoinPool pool;
    private ComputingTask task;

    public ParalellProcessor(Field previousField) {
        this.previousField = previousField;
    }

    public void start(int iterations) {
        pool = new ForkJoinPool();
        task = new ComputingTask(0, 0, previousField, currentField);
        //System.out.println(previousField);
        for (int i = 0; i < iterations; i++) {
            iterate();
            //System.out.println(previousField);
        }
    }

    private void iterate() {
        currentField = new Field(previousField.getHeight(), previousField.getWidth());
        task.setPreviousField(previousField);
        for (int i = 0; i < 9; i++) {
            task = new ComputingTask(i / 3, i % 3, previousField, currentField);
            task.setDx(i / 3);
            task.setDy(i % 3);
            task.setCurrentField(currentField);
            currentField = pool.invoke(task);
        }
        previousField = currentField;
    }
}
