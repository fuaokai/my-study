package com.fsr.study.algorithms.soft;

import java.util.Arrays;

import static com.fsr.study.algorithms.soft.SortUtils.*;

/**
 * 插入排序
 * @author Aokai.Fu
 * @version v1.0
 * @apiNote InsertionSort
 * @create 2020/6/17 15:37
 */
public class InsertionSort  implements SortAlgorithm{

    public static void main(String[] args) {
//        Integer[] unsorted = {1, 8, 31, 4, 1};
        Integer[] unsorted = {1, 3, 6, 78, 1, 54, 231, 9, 12};
        SortAlgorithm sort = new InsertionSort();
        sort.sort(unsorted);
        System.out.println(Arrays.toString(unsorted));
    }

//    @Override
//    public <T extends Comparable<T>> T[] sort(T[] array) {
//        for (int j = 1; j < array.length; j++) {
//
//            // Picking up the key(Card)
//            T key = array[j];
//            int i = j - 1;
//
//            while (i >= 0 && less(key, array[i])) {
//                array[i + 1] = array[i];
//                i--;
//            }
//            // Placing the key (Card) at its correct position in the sorted subarray
//            array[i + 1] = key;
//        }
//        return array;
//    }

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        for (int i = 1, size = unsorted.length; i < size; i++) {
            int compareIndex = i - 1;
            while (less(unsorted[compareIndex], unsorted[i]) && compareIndex >= 0) {
                unsorted[compareIndex] = unsorted[i];
                compareIndex--;
            }
        }
        return unsorted;
    }
}
