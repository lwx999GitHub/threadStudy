package com.thread.example.fromVectorQueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestArrayBlockingQueue {
    static BlockingQueue <String> strs=new ArrayBlockingQueue<String>(10);
    static Random r=new Random();

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            try {
                strs.put("a"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            strs.put("bbbb");
        } catch (InterruptedException e) {

        }
    }
}
