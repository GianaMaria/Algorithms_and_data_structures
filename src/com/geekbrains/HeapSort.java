package com.geekbrains;

public class HeapSort {
    private static int heapSize;

    public static void sort(int[] array) {
        buildHeap(array);
        while (heapSize > 1) {
            swap(array, 0, heapSize - 1);
            heapSize--;
            heapifi(array, 0);
        }
    }

    private static void buildHeap(int[] array) {
        heapSize = array.length;
        for (int i = array.length / 2; i >= 0; i--) {
            heapifi(array, i);

        }
    }

    private static void heapifi(int[] array, int i) {
        int l = left(i);
        int r = right(i);
        int largest = i;
        if (l < heapSize && array[i] < array[l]) {
            largest = l;
        }
        if (r < heapSize && array[largest] < array[r]) {
            largest = r;
        }
        if (i != largest) {
            swap(array, i, largest);
            heapifi(array, largest);
        }
    }

    private static int right(int i) {
        return 2 * i + 2;
    }

    private static int left(int i) {
        return 2 * i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
