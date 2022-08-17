package com.thread.example;

import java.util.concurrent.TimeUnit;

/**
 * 两个方法共用一把锁，因为是一个线程
 */
public class Account {
    String name;
    double balance;
    public synchronized void set(String name,double balance){
        this.name=name;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance=balance;
    }

    public synchronized double getBalance(String name){
        return this.balance;
    }

    public static void main(String[] args) {
        Account a=new Account();
        new Thread(()->a.set("zhangsan",100.0)).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("zhangsan"));
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("zhangsan"));
    }
}
