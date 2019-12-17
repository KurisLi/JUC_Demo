package com.atguigu.demo;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch   只有计数器减到0后，调用await方法的线程才可以执行
 *
 * 要求其他线程执行完 main线程才可以执行
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t离开教室");
                countDownLatch.countDown();
                System.out.println("\t"+countDownLatch.getCount());
            }, String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t离开教室");
    }
}
