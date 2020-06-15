package com.fsr.thread.custom;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleThreadPool implements ExecutorService {

    private static int COUNT_BITS = Integer.MAX_VALUE - 3;

    //最大线程数
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    //线程池的五种状态
    //RUNNING < SHUTDOWN < STOP < TIDING < TERMINATED
    private static final int RUNNING    = -1 << COUNT_BITS;   //线程池运行状态，此时线程池会正常执行，接收任务，执行任务
    private static final int SHUTDOWN   =  0 << COUNT_BITS;   //线程池关机状态，此时线程池不再接收新的任务，但会处理完所有已添加任务 (RUNNING → SHUTDOWN)
    private static final int STOP       =  1 << COUNT_BITS;   //线程池停止状态，此时线程池不再接收新的任务，并且会中断所有正在执行的任务 (RUNNING or SHUTDOWN) → STOP
    private static final int TIDYING    =  2 << COUNT_BITS;   //线程池整理状态，此时线程池的任务数为0，并且会执行terminated()钩子方法 (STOP → TIDYING)
    private static final int TERMINATED =  3 << COUNT_BITS;   //线程池结束状态

    private static AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

    private static int ctlOf(int threadState, int threadCount) {
        return threadState | threadCount;
    }

    private static int runningState(int threadState, int threadCount) {
        return threadState | threadCount;
    }

    private static int runStateOf(int ctl) {
        return ctl & ~CAPACITY;
    }

    private int coreThreadSize;

    private int maxThreadSize;

    private long keepTime;

    private RejectedExecutionHandler rejectedExecutionHandler;
    
    private BlockingQueue<Runnable> works;

    private ThreadFactory threadFactory;

    public SimpleThreadPool(int coreThreadSize) {
//        this(coreThreadSize, 100, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000), new MySimpleThreadFactory(), null);
    }

    public SimpleThreadPool(int coreThreadSize, int maxThreadSize, long keepTime, TimeUnit timeUnit,
                            BlockingQueue<Runnable> works,ThreadFactory threadFactory, RejectedExecutionHandler rejected) {
        this.coreThreadSize = coreThreadSize;
        this.maxThreadSize = maxThreadSize;
        this.keepTime = timeUnit.toNanos(keepTime);
        this.works = works;
        this.threadFactory = threadFactory;
        this.rejectedExecutionHandler = rejected;
    }


    @Override
    public void execute(Runnable command) {
        if (command == null) {
            throw new NullPointerException();
        }

        if (coreThreadSize < workerCountOf(ctl.get())) {
            addWork(command);
        }
    }

    private void addWork(Runnable command) {
        Worker myThread = new Worker(command);
        myThread.run();
    }

    private int workerCountOf(Integer ctl) {
        return ctl & CAPACITY;
    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return null;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return null;
    }

    @Override
    public Future<?> submit(Runnable task) {
        return null;
    }

    @SneakyThrows
//    public void submit(Runnable task) {
//        works.put(new Worker(task));
//    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    private class MySimpleThreadFactory implements ThreadFactory {

        private Integer number;

        @Override
        public Thread newThread(Runnable runnable) {
            ThreadGroup threadGroup = new ThreadGroup("MySimpleThread");
            Thread thread = new Thread(threadGroup, runnable, "MySimpleThreadPool-"+number++, 0);
            if (thread.isDaemon()) thread.setDaemon(false);
            if (thread.getPriority() != Thread.NORM_PRIORITY) thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    }

    private class Worker implements Runnable {

        private Runnable task;
        private Thread currentThread;

        public Worker(Runnable task) {
            this.task = task;
            //暂时不是知道为什么newThread的参数是当前对象
            this.currentThread = new MySimpleThreadFactory().newThread(this);

        }

        @Override
        public void run() {
//            HashSet
            task.run();
        }
    }

//
//    private class MyThread implements Runnable {
//        public MyThread(BlockingQueue<Worker> works) {
//        }
//
//        public void stop() {
//            Thread thread = Thread.currentThread();
//            System.out.println(thread.getName());
//            Thread.currentThread().interrupt();
//        }
//
//        @Override
//        @SneakyThrows
//        public void run() {
//            while (true) {
//            }
//        }
//    }

}