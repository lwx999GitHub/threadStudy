package com.thread.example;

public class SynchronizeNormal {
    private static Integer i = 0;
    private static Object obj = new Object();

    public static void main(String[] args) {
        createThread();
        createThread();
        createThread();
        createThread();
        createThread();
        createThread();
    }

    private static void createThread(){
        new Thread( () -> {
            synchronized (obj) {
                System.out.println(++i);
            }
        }).start();
    }
//说明不加锁，就有问题(没按顺序打印,或漏数)。
}
