package com.fsr.thread;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    public static void main(String[] args) {
//        Executors.newCachedThreadPool();
//        Executors.newWorkStealingPool(10);
//        Executors.newSingleThreadExecutor();
//        Executors.newScheduledThreadPool(10);
//
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        service.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("nihao");
//            }
//        });
//        service.shutdown();
//        service.shutdownNow();
//
//        System.out.println(1 | 8);

        Arrays.asList(new String[]{"1","2","3","4","4"}).stream().map((a)-> new Integer(a)).filter((a)-> a>2).distinct().skip(1).forEach(System.out::println);

//        System.out.println(~(1 << Integer.MAX_VALUE - 3));
//        System.out.println((1 << Integer.MAX_VALUE - 3));
//        System.out.println(~(15));
//        System.out.println((3));

//        System.out.println((-1 << (Integer.MAX_VALUE - 3)) | 0);
//        System.out.println(-1 << (Integer.MAX_VALUE - 3));
//        System.out.println(0 << (Integer.MAX_VALUE - 3));
//        System.out.println(1 << (Integer.MAX_VALUE - 3));
//        System.out.println(2 << (Integer.MAX_VALUE - 3));
//        System.out.println(3 << (Integer.MAX_VALUE - 3));
    }
}
