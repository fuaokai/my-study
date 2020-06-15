package com.fsr.data.chapter1;

public class TimeComplex {
    public static void main(String[] args) {
//        int i=0;
//        int[] arrs = new int[3];
//        for (; i <=3 ; i++) {
//            arrs[i] = 0;
//            System.out.println("Hello World");
//        }
        System.out.println(100^2);
    }









    static int cal(int n) {
        int sum = 0;
        int i = 1;
        for (; i <= n; ++i) {
            sum = sum + i;
        }
        return sum;
    }
}
