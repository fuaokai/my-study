package com.fsr.study.algorithms.soft;

/**
 * Created by SortUtils
 *
 * @author Aokai.Fu
 * @version v1.0
 * @apiNote SortUtils
 * @create 2020/6/16 17:51
 */
public class SortUtils {

    public static <T extends Comparable> void swap(int i, int j, T[] unsorted){
        T temp = unsorted[i];
        unsorted[i] = unsorted[j];
        unsorted[j] = temp;
    }


    public static <T extends Comparable> boolean less(T v, T w){
        return v.compareTo(w) < 0;
    }
}
