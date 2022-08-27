package com.thread.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestCallThreadSum implements Callable<Long> {
    private int start;
    public TestCallThreadSum(int start) {
        this.start = start;
    }
    @Override
    public Long call() throws Exception {
        long sum = 0;
        System.out.println("ThreadName:"+Thread.currentThread().getName());
        for (int i = start+1; i <= start + 4166; i++) {
            sum = sum + i;
            Thread.sleep(2);
        }
        return sum;
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService ser = new ThreadPoolExecutor(8, 8, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        List<Future<Long>> resultList = new ArrayList<Future<Long>>();
        for (int i = 0; i < 8; i++) {
            TestCallThreadSum threadtest1 = new TestCallThreadSum(i * 4166);
            Future<Long> res = ser.submit(threadtest1);
            resultList.add(res);
        }
        Long sum = 0L;
        for (int i = 0; i < resultList.size(); i++) {
            Future<Long> result = resultList.get(i);
            sum = sum + result.get();
        }
        System.out.println("sum:" + sum);
        long end = System.currentTimeMillis();
        System.out.println("cost time total:" + (end - start));
        ser.shutdown();
    }

/*    ThreadName:pool-1-thread-1
    ThreadName:pool-1-thread-5
    ThreadName:pool-1-thread-3
    ThreadName:pool-1-thread-4
    ThreadName:pool-1-thread-6
    ThreadName:pool-1-thread-2
    ThreadName:pool-1-thread-8
    ThreadName:pool-1-thread-7
    sum:555394456
    cost time total:11161*/

    //与单线程测试
/*    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        long sum = 0L;
        for (int i = 0; i <= 33328; i++) {
            sum = sum + i;
            Thread.sleep(2);
        }
        System.out.println("sum=" + sum);
        long end = System.currentTimeMillis();
        System.out.println("cost time:" + (end - start));
    }*/

    //sleep 2秒
/*        sum=555394456
        cost time:82910*/

}

