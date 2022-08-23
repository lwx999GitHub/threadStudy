package com.threadPoll.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateThreadPollDemoCache {
    public static final int SLEEP_GAP=1000;
    static class TargetTask implements Runnable{
        static AtomicInteger taskNo=new AtomicInteger(1);
        private String taskName;
        public TargetTask()
        {
            taskName="task-"+taskNo;
            taskNo.incrementAndGet();
        }
        public void run()
        {
            System.out.println("task:"+taskName+" is doing...");
            try {
                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task:"+taskName+" end...");
        }
    }
    public static void main(String[] args) {
        //三个线程执行三个任务
        //最多有三个任务同时执行
        ExecutorService pool= Executors.newCachedThreadPool();
        for(int i=0;i<1990000001;i++)
        {
            pool.execute(new TargetTask());
            //pool.submit(new TargetTask());
        }
        pool.shutdown();
    }

}
