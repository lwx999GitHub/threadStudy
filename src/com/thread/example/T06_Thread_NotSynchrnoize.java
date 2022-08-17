package com.thread.example;

public class T06_Thread_NotSynchrnoize {

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "  :m1 start ...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " :m1 end");
    }

    //非synchronized会执行
    public void m2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":m2");
    }

    public static void main(String[] args) {
        T06_Thread_NotSynchrnoize t = new T06_Thread_NotSynchrnoize();
        new Thread(t::m1, "t1").start();
        new Thread(t::m2, "t2").start();

    }
}
