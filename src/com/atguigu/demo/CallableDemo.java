package com.atguigu.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 测试callable接口，需要用到futuretask类，这个类实现了Runnable接口，又需要callable作为构造参数
 */
class Mythread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("执行callable");
        return 1024;
    }
}
public class CallableDemo {

    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new Mythread());
        new Thread(futureTask,"A").start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    

}
