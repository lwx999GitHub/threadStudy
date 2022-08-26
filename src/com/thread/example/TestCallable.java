package com.thread.example;

import java.util.concurrent.*;

public class TestCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<String> c=new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello Callable";
            }
        };

        ExecutorService service= Executors.newCachedThreadPool();
        Future<String> f=service.submit(c);
        System.out.println(f.get());
        System.out.println(f.isDone());
        service.shutdown();

    }





}
