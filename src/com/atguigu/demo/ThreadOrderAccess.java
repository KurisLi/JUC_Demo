package com.atguigu.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 呼啦圈模式
 * 三个线程，A先执行，打印1-5，B再执行，打印1-10，C最后执行，打印1-15
 * 循环10次
 * <p>
 * 分析：线程操作资源类
 * 三个线程之间需要交互，而且需要制定顺序，也就是说一个线程执行完后需要唤醒指定的线程
 */
class SourceClass {
    //资源类，封装线程需要调用的方法
    private int nums;
    private Lock lock = new ReentrantLock();
    private int flag = 1;//1代表A线程，2代表B线程，3代表C线程
    Condition condition1 = lock.newCondition();//代表A的钥匙
    Condition condition2 = lock.newCondition();//代表B的钥匙
    Condition condition3 = lock.newCondition();//代表C的钥匙

    public void print5() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1) {
                condition1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "打印第" + i + "次");
            }
            flag = 2;
            condition2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print10() throws InterruptedException {

        lock.lock();
        try {
            while (flag != 2) {
                condition2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "打印第" + i + "次");
            }
            flag = 3;
            condition3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print15() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3) {
                condition3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "打印第" + i + "次");
            }
            flag = 1;
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadOrderAccess {
    public static void main(String[] args) {
        SourceClass sourceClass = new SourceClass();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    sourceClass.print5();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    sourceClass.print10();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    sourceClass.print15();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();

    }
}
