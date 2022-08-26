package com.thread.example;

import org.junit.Test;

import java.util.concurrent.*;

public class TestThreadPollRunnable {

    @Test
    public static void createThreadPool1() {

        int pcount = Runtime.getRuntime().availableProcessors();
        //最大线程数控制
        int maxthreadNum = 4;
        ExecutorService executor = new ThreadPoolExecutor(pcount, maxthreadNum, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 5; i++) {
            final int index = i;
            //匿名内部类方式创建
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //业务处理
                    System.out.println(Thread.currentThread().getName() + " " + index);
                }
            });
        }
    }

    public static void main(String[] args) {
        createThreadPool1();
    }

}
