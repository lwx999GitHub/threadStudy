package com.thread.example.fromVectorQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestLinkBlockingQueue {
    static BlockingQueue <String> strs=new LinkedBlockingQueue<String>(1);
    static Random r=new Random();

    public static void main(String[] args) {
        new Thread(()->{
            for(int i=0;i<100;i++){
                try {
                    System.out.println("put");
                    strs.put("a"+i);
                    //TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"p1").start();

        for(int i=0;i<5;i++){
            new Thread(()->{
                for(;;){
                    try {
                        strs.take();
                        System.out.println("strs.take:"+strs.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
