package ru.ashebalkin.skypro.course2;

import ru.ashebalkin.skypro.course2.service.IntList;
import ru.ashebalkin.skypro.course2.service.IntListImpl;

import java.util.Random;

public class MainIntList {

    public static void main(String[] args) {

        checkAlgorithmsTime();

        IntList numbers = new IntListImpl();

        numbers.add(5351);
        numbers.add(3518);
        numbers.add(510);
        numbers.add(6483);
        numbers.add(8543);
        numbers.add(3513);
        numbers.add(3515);
        numbers.add(8400);
        numbers.add(2843);
        numbers.add(2334);
        numbers.add(3112);
        numbers.add(3846);
        numbers.add(6843);
        numbers.add(8513);

        System.out.println("numbers.contains(511) = " + numbers.contains(511));
        System.out.println("numbers.contains(510) = " + numbers.contains(510));

    }

    private static void checkAlgorithmsTime() {
        long start;

        IntList numbers1 = new IntListImpl();
        int[] num1 = generateRandomArray(numbers1.toArray());
        start = System.currentTimeMillis();
        sortBubble(num1);
        System.out.println(System.currentTimeMillis() - start + " sortBubble");

        IntList numbers2 = new IntListImpl();
        int[] num2 = generateRandomArray(numbers2.toArray());
        start = System.currentTimeMillis();
        sortSelection(num2);
        System.out.println(System.currentTimeMillis() - start + " sortSelection");

        IntList numbers3 = new IntListImpl();
        int[] num3 = generateRandomArray(numbers3.toArray());
        start = System.currentTimeMillis();
        sortInsertion(num3);
        System.out.println(System.currentTimeMillis() - start + " sortInsertion");
    }

    private static int[] generateRandomArray(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000);
        }
        return arr;
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    private static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
