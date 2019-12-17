package com.atguigu.demo;

/**
 * 两个线程操作统一资源，A加1，B减1
 */
class Source{
    private int nums = 0 ;
    public synchronized void increment() throws InterruptedException {
        while(nums!=0){
            this.wait();
        }
        ++nums;
        System.out.println("**************increment"+nums);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (nums==0){
            this.wait();
        }
        --nums;
        System.out.println("**************decrement"+nums);
        this.notifyAll();
    }

}

public class demo {

    public static void main(String[] args) {
        Source source = new Source();
        new Thread(()->{
            try {
                for (int i = 1; i < 10; i++) {
                    source.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"").start();
        new Thread(()->{
            try {
                for (int i = 1; i < 10; i++) {
                    source.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
        new Thread(()->{
            try {
                for (int i = 1; i < 10; i++) {
                    source.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
        new Thread(()->{
            try {
                for (int i = 1; i < 10; i++) {
                    source.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"D").start();
    }
}
