package com.fsr.study.algorithms.soft;

import java.util.Arrays;

import static com.fsr.study.algorithms.soft.SortUtils.less;
import static com.fsr.study.algorithms.soft.SortUtils.swap;

/**
 * 选择排序
 *
 * @author Aokai.Fu
 * @version v1.0
 * @apiNote
 * @create 2020/6/16 17:40
 */
public class SelectionSort implements SortAlgorithm {

    public static void main(String[] args) {
//        Integer[] unsorted = {1, 2, 3, 4, 5};
        Integer[] unsorted = {1, 8, 31, 4, 8};
        SortAlgorithm sort = new SelectionSort();
        sort.sort(unsorted);
        System.out.println(Arrays.toString(unsorted));
    }

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        for (int i = 0, size = unsorted.length; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (!less(unsorted[min], unsorted[j])) {
                    min = j;
                }
            }
            if (min != i) {
                swap(i, min, unsorted);
            }
        }
        return unsorted;
    }
}
