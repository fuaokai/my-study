package com.fsr.study.algorithms.soft;

import java.lang.reflect.Array;
import java.util.Arrays;

import static com.fsr.study.algorithms.soft.SortUtils.less;

/**
 * 希尔排序
 * @author Aokai.Fu
 * @version v1.0
 * @apiNote InsertionSort
 * @create 2020/6/17 15:37
 */
public class ShellSort implements SortAlgorithm{

    public static void main(String[] args) {
//        Integer[] unsorted = {1, 8, 31, 4, 1};
        int[] unsorted = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        SortAlgorithm sort = new ShellSort();
        sort(unsorted);
        System.out.println(Arrays.toString(unsorted));
    }

//    @Override
//    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
//        int length = unsorted.length;
//        int gap = 1;
//        int part = 3;
//
//        while (gap < length / part) {
//            gap = part * gap + 1;
//        }
//
//        for (; gap > 0; gap /= part) {
//            for (int i = gap; i < length; i++) {
//                int j;
//                for (j = i; j >= gap && less(unsorted[j], unsorted[j - gap]); j -= gap) {
//                    unsorted[j] = unsorted[j - gap];
//                }
//                unsorted[j] = unsorted[i];
//            }
//        }
//        return unsorted;
//    }

    /**
     * This method implements Generic Shell Sort.
     *
     * @param array the array to be sorted
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {

        return array;
    }

    static  int sort(int arr[])
    {
        int n = arr.length;

        for (int gap = n/2; gap > 0; gap /= 2)
        {
            for (int i = gap; i < n; i += 1)
            {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];

                }
                arr[j] = temp;
            }
        }
        return 0;
    }


}
