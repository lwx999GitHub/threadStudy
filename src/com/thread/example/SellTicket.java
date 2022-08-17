package com.thread.example;

public class SellTicket implements Runnable {
    private int tickets = 100;
    //创建锁对象（只能创建一个，该对象相当于锁，多个线程共用同一把锁）
    private Object obj = new Object();
    @Override
    public void run() {
        while (true){
            //synchronized (obj){
                if(tickets>0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"正在卖出第"+tickets--+"张票");
                }else{
                    break;
                }
            }
        }
   // }
}

