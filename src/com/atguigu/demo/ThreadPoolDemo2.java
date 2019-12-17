package com.atguigu.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池  ThreadPoolExecutor
 * java提供的三种创建线程池的方式
 * 工作中不会一般使用java提供的创建线程池方式，需要自己手动创建
 */
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        //创建有固定线程个数的线程池
        //ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //创建一个只有一个线程的线程池
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //会根据请求数自动创建线程个数，请求多创的越多
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i=1;i<=10;i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t打印");
                });
            }
        } finally {
            threadPool.shutdown();
        }
    }
}
