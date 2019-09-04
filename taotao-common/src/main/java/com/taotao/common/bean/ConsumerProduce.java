package com.taotao.common.bean;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @Title TODO
 * @Description:
 * @Author caoqiang
 * @Date 2019/4/18 11:39
 * @Version 1.0
 */
public class ConsumerProduce {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        int maxSize = 10;
        Producer p = new Producer(queue,maxSize);
        Consumer c = new Consumer(queue, maxSize);
        Thread pT = new Thread(p);
        Thread pC = new Thread(c);

        pT.start();
        pC.start();

    }

    static class Producer implements Runnable{

        private Queue<Integer> queue;
        private int maxSize;

        public Producer(Queue<Integer> queue, int maxSize) {
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {

            while (true){
                synchronized (queue){
                    while (queue.size() == maxSize){
                        try {
                            System.out.println("queue is full");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println("Produce" + i);
                    queue.add(i);
                    //使用notifyAll，不然在多个生产者和消费者中会产生死锁
                    queue.notifyAll();
                }
            }

        }
    }

    static class Consumer implements Runnable{
        private Queue<Integer> queue;
        private int maxSize;

        public Consumer(Queue<Integer> queue, int maxSize) {
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true){
                synchronized (queue){
                    while (queue.isEmpty()){
                        try {
                            System.out.println("Queue is empty");

                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int v = queue.remove();
                    System.out.println("Consumer" + v);

                    queue.notifyAll();
                }
            }
        }
    }
}
