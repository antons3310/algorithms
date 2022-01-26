package ru.ashebalkin.skypro.course2;

import ru.ashebalkin.skypro.course2.service.IntList;
import ru.ashebalkin.skypro.course2.service.IntListImpl;

import java.util.Random;

public class MainIntList {

    public static void main(String[] args) {

//        checkAlgorithmsTime();

        IntList numbers = new IntListImpl();

        numbers.add(5351);
        numbers.add(3518);
        numbers.add(510);
        numbers.add(6483);
        numbers.add(8543);

        numbers.removeByIndex(3);





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

        IntList numbers4 = new IntListImpl();
        int[] num4 = generateRandomArray(numbers4.toArray());
        start = System.currentTimeMillis();
        quickSort(num4, 0, num4.length - 1);
        System.out.println(System.currentTimeMillis() - start + " quickSort");

        IntList numbers5 = new IntListImpl();
        int[] num5 = generateRandomArray(numbers5.toArray());
        start = System.currentTimeMillis();
        mergeSort(num5);
        System.out.println(System.currentTimeMillis() - start + " mergeSort");
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

    private static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }
}
