package com.thread.example.fromVectorQueue;

import javax.management.MBeanAttributeInfo;
import java.util.concurrent.LinkedTransferQueue;

public class TestTransferQueue {


    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
/*        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/

        strs.transfer("abcdefg");
    }
}
