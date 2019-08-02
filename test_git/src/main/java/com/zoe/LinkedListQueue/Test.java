package com.zoe.LinkedListQueue;

import java.util.Random;

/**
 * Created by g20699 on 2019/8/1.
 */
public class Test {

    private static double testQueue(Queue<Integer> q , int opCount){
        long startTime = System.nanoTime();

        Random random = new Random();
        for(int i = 0 ; i < opCount ; i ++){
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for(int i = 0 ; i < opCount ; i ++){
            q.dequeue();
        }
        long endTime = System.nanoTime();

        //纳秒转成秒
        return (endTime - startTime) / 1000000000.0;

    }
    public static void main(String[] args) {
        //测试
        int opCount = 100000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1 + "s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time: " + time2 + "s");

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double time3 = testQueue(linkedListQueue, opCount);
        System.out.println("LinkedListQueue, time: " + time3 + "s");
    }
}
