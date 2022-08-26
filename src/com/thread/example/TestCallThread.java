package com.thread.example;

import java.util.concurrent.*;

public class TestCallThread  implements Callable<Boolean> {
    private String name;
    public TestCallThread(String name){
        this.name = name;
    }

    @Override
    public Boolean call() {
        // run方法线程体
        for (int i = 0; i < 10; i++) {
            System.out.println(name+"输出--"+i);
        }
        return  true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        TestCallThread threadtest1 = new TestCallThread("小明");
        TestCallThread threadtest2 = new TestCallThread("小陈");
        TestCallThread threadtest3= new TestCallThread("小王");
        //(1)创建执行服务：
        ExecutorService ser = Executors.newFixedThreadPool(3);
        //(2)提交执行
        Future<Boolean> r1 = ser.submit(threadtest1);
        Future<Boolean> r2 = ser.submit(threadtest2);
        Future<Boolean> r3 = ser.submit(threadtest3);
        //(3)获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);

        //(4)关闭服务
        ser.shutdown();

    }

}

