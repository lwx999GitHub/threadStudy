package com.thread.example;

public class T02_HowToCreateThread {
   static class MyThread extends Thread{
       @Override
       public void run() {
           System.out.println("Hello MyThread!");
       }
   }

   static class MyRun implements Runnable{
       @Override
       public void run() {
           System.out.println("Hello MyRun!");
       }
   }

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("hello Lambda!");
        }).start();
    }

}
