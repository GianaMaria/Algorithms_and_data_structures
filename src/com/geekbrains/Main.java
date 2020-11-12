package com.geekbrains;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        /* Задание 6.1
         * Файловая система, цель поиска - файл на диске,
         * где ключ - название файла, а значение -расположение на диске
         * Красно-черные деревья необходимы для обеспечения производительности,
         * тк дерево может превратиться в список
         * и используются в качестве системных таблиц символов */

        MyTreeMap<Integer, String> map = new MyTreeMap<>();

        map.put(5, "five");
        map.put(2, "two");
        map.put(4, "four");
        map.put(9, "nine");
        map.put(1, "one");

        System.out.println(map);
        System.out.println(map.get(5));
        map.put(2, "two2");
        System.out.println(map);

//        map.delete(2);
//        System.out.println(map);
        System.out.println(map.minKey());


        /*Пирамидальная сортировка*/
        int[] arr;
        Random random = new Random();
        arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10);
        }
        System.out.println(Arrays.toString(arr));

        HeapSort.sort(arr);

        System.out.println(Arrays.toString(arr));

    }
}
