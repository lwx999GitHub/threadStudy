package com.thread.example;

public class SynchronizeMethod {
    private static Integer i = 0;

    public static void main(String[] args) {
        new Thread(()->{print();}).start();
        new Thread(()->{print();}).start();
        new Thread(()->{print();}).start();
        new Thread(()->{print();}).start();
        new Thread(()->{print();}).start();
        new Thread(()->{print();}).start();

        new Thread(()->{print();}).start();
        new Thread(()->{print();}).start();
        new Thread(()->{print();}).start();
        new Thread(()->{print();}).start();
        new Thread(()->{print();}).start();
        new Thread(()->{print();}).start();
    }

    public static synchronized   void print(){
        //System.out.println(Thread.currentThread().getName());
        System.out.println(++i);
    }


}
