package ru.geekbrains.java2.lesson5;

public class MultiThread {

    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;
    static float[] arr = new float[SIZE];

    public static void main(String[] args) throws InterruptedException {
        firstMethod();
        secondMethod();
    }

    public static void firstMethod()
    {
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        //System.out.println(a);
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long b = System.currentTimeMillis();
        System.out.println("firstMethod: " + (b - a));
    }

    private static void secondMethod() throws InterruptedException {
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        float partsArr[][] = prepareParts();
        splitingArray(arr, partsArr);
        Thread thread = new Thread(() -> fillingByFormArr(partsArr[0], 0));
        thread.start();
        fillingByFormArr(partsArr[1], HALF);
        thread.join();
        joiningArray(arr, partsArr);
        long b = System.currentTimeMillis();
        System.out.println("secondMethod: " + (b - a));
    }

    private static float[][] prepareParts() {
        return new float[][]{new float[HALF], new float[HALF]};
    }

    private static void splitingArray(float[] arr, float[][] partsArr) {
        System.arraycopy(arr, 0, partsArr[0], 0, HALF);
        System.arraycopy(arr, HALF, partsArr[1], 0, HALF);
    }

    private static void fillingByFormArr(float[] arr, int startPosition) {
        for (int i = 0, len = arr.length; i < len; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (i + startPosition)/ 5) * Math.cos(0.2f + (i + startPosition) / 5)
                    * Math.cos(0.4f + (i + startPosition)/ 2));
        }
    }

    private static void joiningArray(float[] arr, float[][] partsArr) {
        System.arraycopy(partsArr[0], 0, arr, 0, HALF);
        System.arraycopy(partsArr[1], 0, arr, HALF, HALF);
    }

}
