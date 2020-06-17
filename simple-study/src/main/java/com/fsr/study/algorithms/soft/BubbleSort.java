package com.fsr.study.algorithms.soft;

import static com.fsr.study.algorithms.soft.SortUtils.*;

import java.util.Arrays;
import java.util.Collections;

/**
 * 冒泡排序
 *
 * @author Aokai.Fu
 * @version v1.0
 * @apiNote BubbleSoft
 * @create 2020/6/16 17:40
 */
public class BubbleSort implements SortAlgorithm{

    public static void main(String[] args) {
        Integer[] unsorted = {1, 8, 31, 4, 8};
        SortAlgorithm sort = new BubbleSort();
        sort.sort(unsorted);
        System.out.println(Arrays.toString(unsorted));
    }

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        for (int i = 0, size = unsorted.length; i < size - 1; i++) {
            boolean isSwapped = false;
            for (int j = 0; j < size - 1 - i; j++) {
                T one = unsorted[j];
                T two = unsorted[j + 1];
                if (!less(one, two)) {
                    swap(j, j + 1, unsorted);
                    isSwapped = true;
                }
            }

            if (!isSwapped) {
                break;
            }
        }
        return unsorted;
    }

//    @Override
//    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
//
//        int count =0;
//        for (int i = 0, size = unsorted.length; i < size - 1; ++i) {
//            boolean isSwapped = false;
//            for (int j = 0; j < size - 1 - i; ++j) {
//                T one = unsorted[j];
//                T two = unsorted[j + 1];
//                if (!less(one, two)) {
//                    swap(j, j+1, unsorted);
//                    isSwapped = true;
//                }
//                count++;
//            }
//            if (!isSwapped) {
//                break;
//            }
//        }
//        System.out.println(count);
//        return unsorted;
//    }
}
