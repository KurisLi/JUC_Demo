package com.atguigu.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用线程池模拟卖票，3个售票员卖出30张票
 */
class Ticket{
    private int nums = 30;
    private Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if (nums>0){
                System.out.println(Thread.currentThread().getName()+"\t卖出第"+nums--+"张票，剩余"+nums+"张");
            }
        } finally {
            lock.unlock();
        }
    }
}
public class ThreadPoolDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //创建出线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i <35 ; i++) {
                threadPool.execute(()->{
                    ticket.sale();
                });
            }

        }finally {
            threadPool.shutdown();
        }
    }
}
