package com.atguigu.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试读写锁
 * 设计一个缓存，
 */
class TestCash {
    private Map<String, String> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void write(String key, String value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t写入开始");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"\t写入结束");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void read(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t读取开始");
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取结束"+result);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        TestCash cash = new TestCash();
        for (int i = 1; i <= 10; i++) {
            final int temp = i;
           new Thread(() -> {
                cash.write(temp+"",temp+"");
           }, String.valueOf(i)).start();
       }
        for (int i = 1; i <= 10; i++) {
            final int temp = i;
            new Thread(() -> {
                cash.read(temp+"");
            }, String.valueOf(i)).start();
        }
    }

}
