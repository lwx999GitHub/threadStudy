package com.thread.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestCallThreadSum implements Callable<Integer> {
    private String name;
    private List<Integer> list = new ArrayList<Integer>();

    public TestCallThreadSum(String name, List list) {
        this.name = name;
        this.list = list;
    }

   // static int sum = 0;

    @Override
    public Integer call() throws Exception {
        int sum=0;
      for(int i=0;i<list.size();i++){
          sum=sum+list.get(i);
      }
        return sum;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Integer> list1 = new ArrayList<Integer>();
        for (int i = 1; i <= 3333; i++) {
            list1.add(i);
        }

        List<Integer> list2 = new ArrayList<Integer>();
        for (int i = 3334; i <= 6667; i++) {
            list2.add(i);
        }

        List<Integer> list3 = new ArrayList<Integer>();
        for (int i = 6668; i <= 10000; i++) {
            list3.add(i);
        }
        TestCallThreadSum threadtest1 = new TestCallThreadSum("小明", list1);
        TestCallThreadSum threadtest2 = new TestCallThreadSum("小陈", list2);
        TestCallThreadSum threadtest3 = new TestCallThreadSum("小王", list3);

        ExecutorService ser = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        //(2)提交执行
        Future<Integer> r1 = ser.submit(threadtest1);
        Future<Integer> r2 = ser.submit(threadtest2);
        Future<Integer> r3 = ser.submit(threadtest3);

        //ser.
        //(3)获取结果
        Integer rs1 = r1.get();
        Integer rs2 = r2.get();
        Integer rs3 = r3.get();
       // System.out.println("sum=" + rs1.intValue()+rs2.intValue()+rs3.intValue());
        System.out.println("rs1=" + rs1);
        System.out.println("rs2=" + rs2);
        System.out.println("rs3=" + rs3);




        //(4)关闭服务
        ser.shutdown();

    }

}

