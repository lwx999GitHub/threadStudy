package com.thread.example;

import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        final Object o = new Object();
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        t1 = new Thread(() -> {
            synchronized (o) {
                for (char c : aI) {
                    System.out.print(c);
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c : aC) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }

        }, "t2");

        t1.start();
        t2.start();

    }
}
