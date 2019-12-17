package com.atguigu.demo;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        /*阻塞队列BlockingQueue是  Queue的子接口,是一个集合
           有7个实现类，常用的有3种，分别为ArrayBlockingQueue、LinkedBlockingQueue、SynchronousQueue
        */
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
       // System.out.println(blockingQueue.add("d"));//add 方法在超出队列范围后继续添加会报错
        System.out.println(blockingQueue.element());//element方法会取出队列的首位元素，先进先出
       // System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());//remove方法和add方法配合使用，队列为空时继续移除会报错
        System.out.println("----------------------------------------------------------");
        ArrayBlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue1.offer("1"));
        System.out.println(blockingQueue1.offer("2"));
        System.out.println(blockingQueue1.offer("3"));
        System.out.println(blockingQueue1.offer("4"));//offer方法在队列满了之后不会报错，但是会判断为false，并不会添加
        System.out.println(blockingQueue1.peek());//peek方法与offer类似，显示队列中的首位元素
//        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue1.poll());
        System.out.println(blockingQueue1.poll());
        System.out.println(blockingQueue1.poll());
        System.out.println(blockingQueue1.poll());//当队列满时会取出的值为null，并不会报错
        System.out.println("--------------------------------------------------------");
        ArrayBlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<>(3);
        blockingQueue2.put("3");
        blockingQueue2.put("4");
        blockingQueue2.put("5");
//        blockingQueue2.put("6");  put方法在队列满时继续添加会造成线程阻塞
        System.out.println("添加成功");
        //blockingQueue2.put("6");
        blockingQueue2.take();
        blockingQueue2.take();
        blockingQueue2.take();
        System.out.println("取出成功");
    }
}
