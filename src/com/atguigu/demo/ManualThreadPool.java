package com.atguigu.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 手动创建一个线程池
 */
public class ManualThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                5,3L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i=1;i<=8;i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t打印");
                });
            }
        } finally {
            threadPool.shutdown();
        }
    }
}
