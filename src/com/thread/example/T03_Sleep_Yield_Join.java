package com.thread.example;

public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
        testYield();
    }
    static void testYield(){
        new Thread(()->{
            for(int i=0;i<100;i++){
                System.out.println("A"+i);
                if(i%2==0){
                    Thread.yield();//正在执行线程，让出CPU，供其它线程执行线程
                }

            }

        }).start();

        new Thread(()->{
            for(int i=0;i<100;i++){
                System.out.println("B"+i);
                if(i%2==0){
                    Thread.yield();
                }

            }

        }).start();
    }

}