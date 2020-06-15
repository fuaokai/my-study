package com.fsr.thread;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class Main {

    static Object lock = new Object();
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadSynchronizeThis(), String.valueOf(i)).start();
        }
//        System.out.println(Integer.MAX_VALUE-3);
//        System.out.println(3|7);
//        System.out.println(~(Integer.MAX_VALUE-3));
//        System.out.println((1 << (Integer.MAX_VALUE-3)));
//
//        System.out.println((1 & ~(Integer.MAX_VALUE-3)));
//        System.out.println((1 << (Integer.MAX_VALUE-3)) - 1);
//
//        System.out.println((Integer.MAX_VALUE-3) %32);
//        System.out.println(1 << 1299);
    }

    static class ThreadSynchronizeThis implements Runnable{

        @SneakyThrows
        private synchronized void test1() {
            synchronized (this) {
                System.out.println("test1");
                TimeUnit.MILLISECONDS.sleep(2000);
            }
        }


        @SneakyThrows
        private synchronized void test2() {
            synchronized (lock) {
                System.out.println("test2");
                TimeUnit.MILLISECONDS.sleep(2000);
            }
        }

        @SneakyThrows
        public static void test3() {
            System.out.println("test3");
            TimeUnit.MILLISECONDS.sleep(2000);
        }

        @Override
        @SneakyThrows
        public void run() {
            test1();
            test3();
            test2();
        }
    }
}
