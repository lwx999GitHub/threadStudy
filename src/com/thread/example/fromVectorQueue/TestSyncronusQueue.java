package com.thread.example.fromVectorQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class TestSyncronusQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs= new SynchronousQueue<>();
/*        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
        strs.put("aaa");
        System.out.println(strs.size());
    }
}
