package com.taotao.common.bean;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @Title TODO
 * @Description: TODO
 * @Author caoqiang
 * @Date 2019/4/18 16:39
 * @Version 1.0
 */
public class ThreadJoin implements Runnable{

    private Queue<Integer> queue;

    public ThreadJoin(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            try {
                int v = 11;
                Thread.sleep(5000);
                System.out.println("Consumer" + v + "+++++++++");

                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Producer implements Runnable{
        private Queue<Integer> queue;

        public Producer(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
                Random random = new Random();
                int i = random.nextInt();
                queue.add(i);
                System.out.println("Producer" + i + "---------");
        }
    }


    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        while (true){
            Thread B = new Thread(new ThreadJoin(queue));
            Thread A = new Thread(new Producer(queue));
            B.start();
            A.start();

            try {
                A.join();
//                B.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("123456");
        }
    }
}
