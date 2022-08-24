package com.thread.example.fromVectorQueue;

import java.util.PriorityQueue;

public class TestPriorityQueue {
    public static void main(String[] args) {
        PriorityQueue <String> q=new PriorityQueue<String>();
        q.add("c");
        q.add("e");
        q.add("a");
        q.add("d");
        q.add("z");
        int size=q.size();
        for(int i=0;i<size;i++){
            System.out.println(q.poll());
        }

    }
}
