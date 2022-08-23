package com.threadPoll.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateThreadPollDemoSchedule {
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
    public static void main(String[] args) throws InterruptedException {
        //三个线程执行三个任务
        //最多有三个任务同时执行

        ScheduledExecutorService pool= Executors.newScheduledThreadPool(2);
        for(int i=0;i<5;i++)
        {
            //任务名称,
            pool.scheduleAtFixedRate(new TargetTask(),10,10, TimeUnit.SECONDS);
            //延迟2秒后开始执行首个任务,之后间隔5秒执行一个任务，而不是等到上个任务执行结束

            //pool.execute(new TargetTask());
            //pool.submit(new TargetTask());
        }
        TimeUnit.SECONDS.sleep(60);
        pool.shutdown();
    }

}
