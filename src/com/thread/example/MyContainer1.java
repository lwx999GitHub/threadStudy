package com.thread.example;

import javax.management.MBeanAttributeInfo;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer1<T> {

    private final LinkedList<T> list = new LinkedList<T>();
    private final int MAX = 10;
    private int count = 0;

    private synchronized void put(T t) {
        //为什么要用WHILE?
        //答:因为while中wait后，其它线程生产满了，如果没有while，那么wait不会判断，会往下面执行,就会出问题
        // （容器满了，还在加）
        while (list.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
        }
        list.add(t);
        count++;
        this.notifyAll();
    }

    private synchronized T get() {
        T t = null;


        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        //启动消费者线程消费
        MyContainer1<String> c = new MyContainer1<String>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程生产
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }


    }

}
