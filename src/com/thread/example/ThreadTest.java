package com.thread.example;

/**
 * 通过继承Thread实现线程
 */
public class ThreadTest extends Thread {
    private int i = 0;
    @Override
    public void run() {
        for (; i < 50; i++) {
            System.out.println(Thread.currentThread().getName() + " is running " + i);
        }
    }

    public static void main(String[] args) {
        for (int j = 0; j < 50; j++) {
            if (j == 20) {
                new ThreadTest().start();
                new ThreadTest().start();
            }
        }
    }
}