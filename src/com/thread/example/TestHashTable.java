package com.thread.example;
import com.thread.example.constant.Constant;

import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;

public class TestHashTable {
    //0.定义一个容器
    static Map<UUID, UUID> map = new Hashtable<>();//线程安全的
    static int count = Constant.count;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        int start;
        int gap = Constant.count / Constant.threadCount;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + gap; i++) {
                map.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[Constant.threadCount];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(i * (Constant.count/Constant.threadCount));
        }

        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("cost total times:" + (end - start));
        System.out.println("map size:" + map.size());

        // 读的时间
        start=System.currentTimeMillis();
        for(int i=0;i<threads.length;i++){
            threads[i]=new Thread(()->{
                for(int j=0;j<10000000;j++){
                    map.get(keys[10]);
                }
            });
        }

        for(Thread t :threads){
            t.start();
        }

        for(Thread t :threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end=System.currentTimeMillis();

        System.out.println("redd time:"+(end-start));



    }


}
