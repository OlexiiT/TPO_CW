package org.example;

import org.example.Processors.ParalellProcessor;
import org.example.game.Field;
import org.example.Processors.SimpleProcessor;

public class Main {
    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
        testSimpleProc();
        //testParalellProc();

    }

    private static void testParalellProc() {
        int[] iterationsArray = new int[]{10, 50, 250, 1000, 2000, 5000, 10000};
        int[] sizesArray = new int[]{64, 128, 256, 512, 1024};
        Field field;
        ParalellProcessor processor;

        for (int size : sizesArray) {
            for (int iterations : iterationsArray) {
                field = new Field(size, size);
                field.randomFill(0.5);

                processor = new ParalellProcessor(field);
                long start = System.currentTimeMillis();
                processor.start(iterations);
                long finish = System.currentTimeMillis();
                System.out.println("Size: " + field.getHeight() + "x" + field.getWidth());
                System.out.println("Iterations: " + iterations);
                System.out.println(finish - start + "ms.\n");
            }
        }
    }

    private static void testSimpleProc() {
        int[] iterationsArray = new int[]{10, 50, 250, 1000, 2000, 5000, 10000};
        int[] sizesArray = new int[]{64, 128, 256, 512, 1024};
        Field field;
        SimpleProcessor processor;

        for (int size : sizesArray) {
            for (int iterations : iterationsArray) {
                field = new Field(size, size);
                field.randomFill(0.5);

                processor = new SimpleProcessor(field);
                long start = System.currentTimeMillis();
                processor.start(iterations);
                long finish = System.currentTimeMillis();
                System.out.println("Size: " + field.getHeight() + "x" + field.getWidth());
                System.out.println("Iterations: " + iterations);
                System.out.println(finish - start + "ms.\n");
            }
        }
    }
}