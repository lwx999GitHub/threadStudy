package com.thread.example;

public class T03_Sleep_Yield_Join {
    public static void main(String[] args) throws InterruptedException {
        //testYield();
        //testSleep();
        testJoin();
    }

    static void testSleep() throws InterruptedException {
     Thread t1= new Thread(()->{
            for(int i=0;i<100;i++){
                System.out.println("A"+i);
                if(i%2==0){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
     t1.start();
     t1.join();
     System.out.println("Main结束");
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

    static void testJoin() throws InterruptedException {
        Thread tA= new Thread(()->{
            for(int i=0;i<100;i++){
                System.out.println("A"+i);
                if(i%2==0){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        tA.start();

        Thread tB= new Thread(()->{
            for(int i=0;i<100;i++){
                try {
                    tA.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B"+i);
                if(i%2==0){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        tB.start();

    }

}
