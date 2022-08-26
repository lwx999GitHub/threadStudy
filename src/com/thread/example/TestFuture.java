package com.thread.example;

import java.util.concurrent.*;

public class TestFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
/*        FutureTask<Integer> task=new FutureTask<>(()->{
            TimeUnit.SECONDS.sleep(1);
            return 1;
        });

        new Thread(task).start();

        System.out.println(task.get());*/




        ExecutorService service= Executors.newFixedThreadPool(5);

        Future <Integer> f=service.submit(()->{
            TimeUnit.SECONDS.sleep(1);
            return 1;
        });

        System.out.println(f.get());

    }




}
