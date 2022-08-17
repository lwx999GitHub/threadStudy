package com.thread.example;

import java.util.concurrent.TimeUnit;

public class T05_threadState {

    static class myThread extends Thread{

        @Override
        public void run() {
          for(int i=0;i<10;i++){
              System.out.println(i);

              try {
                  TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t =new myThread();
        System.out.println(t.getState());
        t.start();
        t.join();
        System.out.println(t.getState());
    }
}
