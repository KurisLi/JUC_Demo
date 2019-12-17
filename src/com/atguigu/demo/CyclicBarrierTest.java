package com.atguigu.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 累积到一定数量才可以执行，做加法
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("集齐7课龙珠召唤神龙");
        });

        for (int i = 1; i <= 10; i++) {
            final int tempI = i;
            new Thread(() -> {
                try {
                    System.out.println("收集到第"+tempI+"颗");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
