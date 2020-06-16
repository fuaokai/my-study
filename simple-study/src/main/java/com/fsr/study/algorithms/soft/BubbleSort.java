package com.fsr.study.algorithms.soft;

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

    }

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        if (unsorted == null) {
            return null;
        }

        for (int i = 0; i < unsorted.length - 1; i++) {
            boolean isSwapped = false;
            for (int j = i + 1; j < unsorted.length; j++) {
                T swapped = unsorted[i];
                T beSwapped = unsorted[j];
                if (swapped.compareTo(beSwapped) > 0) {
                    swap(i, j, unsorted);
                }
            }
            if (!isSwapped) {
                break;
            }
        }
        return null;
    }
}
