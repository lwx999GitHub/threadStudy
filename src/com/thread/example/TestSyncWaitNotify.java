package com.thread.example;

public class TestSyncWaitNotify {

    private static volatile boolean t2Started = false;

    public static void main(String[] args) {
        final Object o = new Object();
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        new Thread(() -> {
            synchronized (o) {
                while (!t2Started) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                for (char c : aI) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();

       //t2Started = true;
        new Thread(() -> {
            synchronized (o) {
                t2Started = true;
                for (char c : aC) {
                    System.out.print(c);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();

    }
}
