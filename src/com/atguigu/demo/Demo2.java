package com.atguigu.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Source2 {
    //定义资源
    private int nums = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        //如果不加判断控制，两个线程会随机调度，起不到合理修改资源的效果
        //使用lock来替换synchronized
        //TimeUnit.SECONDS.sleep(1);
        lock.lock();
        try {
            while (nums != 0) {
                condition.await();
            }
            ++nums;
            System.out.println(Thread.currentThread().getName() + "--------increment******" + nums);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() throws Exception {
        //如果不加判断控制，两个线程会随机调度，起不到合理修改资源的效果
        //使用lock来替换synchronized
        //TimeUnit.SECONDS.sleep(1);
        lock.lock();
        try {
            while (nums == 0) {
                condition.await();
            }
            --nums;
            System.out.println(Thread.currentThread().getName() + "--------decrement******" + nums);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }
    //内聚方法
    /*public synchronized void increment() throws Exception {
        //如果不加判断控制，两个线程会随机调度，起不到合理修改资源的效果
        TimeUnit.SECONDS.sleep(1);
        while (nums!=0){
            this.wait();
        }
        ++nums;
        System.out.println(Thread.currentThread().getName()+"--------increment******"+nums);
        this.notifyAll();
    }
    public synchronized void decrement() throws Exception {
        while (nums==0){
            this.wait();
        }
        --nums;
        System.out.println(Thread.currentThread().getName()+"---------decrement******"+nums);
        this.notifyAll();
    }*/

}

/**
 * 生产者和消费者模式
 * 多个线程多一个资源类进行操作，使0进行加一、减一循环操作
 */
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        Source2 source2 = new Source2();
        new Thread(() -> {
            try {
                // TimeUnit.SECONDS.sleep(3);
                for (int i = 1; i <= 10; i++) {
                    source2.increment();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    source2.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    source2.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    source2.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}
