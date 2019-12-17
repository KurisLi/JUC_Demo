package com.atguigu.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 抢车位
 *
 * 控制多个资源给多个线程分配
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//假设有3个车位

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                boolean flag = false;
                try {
                    semaphore.acquire();
                    flag=true;
                    System.out.println(Thread.currentThread().getName()+"\t抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if (flag){
                        semaphore.release();
                    }
                }
            }, String.valueOf(i)).start();
        }
    }
}
