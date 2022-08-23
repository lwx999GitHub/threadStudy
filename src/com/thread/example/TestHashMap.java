package com.thread.example;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

public class TestHashMap {
    //0.定义一个容器
    static Map<UUID, UUID> map = new HashMap<>();//线程非安全的
    static int count = 1000000;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new MyThread(i * 10000);
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
        System.out.println("map size:" + map.size());//不是100W,
    }

    static class MyThread extends Thread {
        int start;
        int gap = count / 100;

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
}
