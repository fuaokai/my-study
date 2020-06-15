//package com.fsr.thread.custom;
//
//import com.sun.org.apache.xpath.internal.functions.FuncFalse;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.concurrent.*;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.locks.ReentrantLock;
//
//public class MyThreadPool implements ExecutorService {
//
//    private static final int COUNT_BITS = Integer.SIZE - 3;
//    //最大线程数
//    private static final int CAPACITY = (1 << COUNT_BITS) - 1;
//    //线程池的五种状态
//    //RUNNING < SHUTDOWN < STOP < TIDING < TERMINATED
//    private static final int RUNNING    = -1 << COUNT_BITS;   //线程池运行状态，此时线程池会正常执行，接收任务，执行任务
//    private static final int SHUTDOWN   =  0 << COUNT_BITS;   //线程池关机状态，此时线程池不再接收新的任务，但会处理完所有已添加任务 (RUNNING → SHUTDOWN)
//    private static final int STOP       =  1 << COUNT_BITS;   //线程池停止状态，此时线程池不再接收新的任务，并且会中断所有正在执行的任务 (RUNNING or SHUTDOWN) → STOP
//    private static final int TIDYING    =  2 << COUNT_BITS;   //线程池整理状态，此时线程池的任务数为0，并且会执行terminated()钩子方法 (STOP → TIDYING)
//    private static final int TERMINATED =  3 << COUNT_BITS;   //线程池结束状态
//
//    private static final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
//
//
//    private final ReentrantLock mainLock = new ReentrantLock();
//
//    //获取当前线程池状态
//    private static int runStateOf(int c){
//        return c & ~CAPACITY;
//    }
//
//    //统计线程池worker数 (线程数)
//    private static int workerCountOf(int c) {
//        return c & CAPACITY;
//    }
//
//    //根据线程运行状态和工作线程数计算ctl值
//    //ctl高三位用于表示线程状态，后27位用于表示线程数，逻辑或能保证得到的状态和线程数量正确
//    private static int ctlOf(int threadRunState, int workCount) {
//        return threadRunState | workCount;
//    }
//
//    //线程池状态小于指定装填
//    private static boolean runStateLessThan(int c, int s) {
//        return c < s;
//    }
//
//    private static boolean runStateAtLeast(int c, int s) {
//        return c >= s;
//    }
//
//    private int corePoolSize;
//    private int maximumPoolSize;
//    private long keepAliveTime;
//    private BlockingQueue workQueue;
//    private ThreadFactory threadFactory;
//    private RejectedExecutionHandler handler;
//
//    public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit, BlockingQueue workQueue,
//                        ThreadFactory threadFactory, RejectedExecutionHandler handler) {
//        if (corePoolSize < 0 || maximumPoolSize < 0 || maximumPoolSize < corePoolSize || keepAliveTime < 0)
//            throw new IllegalArgumentException("");
//        if (workQueue == null || threadFactory == null || handler == null)
//            throw new NullPointerException();
//        this.corePoolSize    = corePoolSize;
//        this.maximumPoolSize = maximumPoolSize;
//        this.keepAliveTime   = timeUnit.toNanos(keepAliveTime);
//        this.workQueue       = workQueue;
//        this.threadFactory   = threadFactory;
//        this.handler         = handler;
//    }
//
//    @Override
//    public void shutdown() {
//
//    }
//
//    @Override
//    public List<Runnable> shutdownNow() {
//        return null;
//    }
//
//    @Override
//    public boolean isShutdown() {
//        return false;
//    }
//
//    @Override
//    public boolean isTerminated() {
//        return false;
//    }
//
//    @Override
//    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
//        return false;
//    }
//
//    @Override
//    public <T> Future<T> submit(Callable<T> task) {
//        return null;
//    }
//
//    @Override
//    public <T> Future<T> submit(Runnable task, T result) {
//        return null;
//    }
//
//    @Override
//    public Future<?> submit(Runnable task) {
//        if (task == null) {
//            throw new NullPointerException();
//        }
//
//        return null;
//    }
//
//    @Override
//    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
//        return null;
//    }
//
//    @Override
//    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
//        return null;
//    }
//
//    @Override
//    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
//        return null;
//    }
//
//    @Override
//    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//        return null;
//    }
//
//    @Override
//    public void execute(Runnable command) {
//        if (command == null) throw new NullPointerException();
//
//        int c = ctl.get();
//        if (workerCountOf(c) < corePoolSize) {
//            if (addWorker(command, true)) return;
//            c = ctl.get();
//        }
//
//        if (isRunning(c) && workQueue.offer(command)) {
//            int recheck = ctl.get();
//            if (!isRunning(recheck) && remove(command)) {
//                reject(command);
//            } else if (workerCountOf(recheck) == 0) {
//                addWorker(command, false);
//            }
//        }
//    }
//
//    private boolean remove(Runnable task) {
//        boolean removed = workQueue.remove(task);
//        tryTerminate();
//        return removed;
//    }
//
//    protected void terminated(){
//
//    }
//
//    private void tryTerminate() {
//        for (; ; ) {
//            int c = ctl.get();
//            if (isRunning(c) || runStateAtLeast(c, TIDYING) || (runStateOf(c) == SHUTDOWN && !workQueue.isEmpty())) {
//                return;
//            }
//            if (workerCountOf(c) != 0) {
//                // ONLY_ONE = true
//                interruptIdleWorkers(true);
//            }
//
//            final ReentrantLock mainLock = this.mainLock;
//            mainLock.lock();
//            try {
//                if (ctl.compareAndSet(c, ctlOf(TERMINATED, 0))) {
//                    terminated();
//                }
//
//                ctl.set(ctlOf(TERMINATED, 0));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void interruptIdleWorkers(boolean b) {
//    }
//
//    private boolean isRunning(int recheck) {
//        return recheck < SHUTDOWN;
//    }
//
//    private boolean addWorker(Runnable command, boolean b) {
//        return false;
//    }
//}
