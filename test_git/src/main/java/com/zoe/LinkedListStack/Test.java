package com.zoe.LinkedListStack;

import java.util.Random;

/**
 * Created by g20699 on 2019/8/1.
 * 数组栈会时不时重新分配静态数组，将原来的数组中的元素复制拷贝到新的数组中，这个过程相对比较耗时
 * 但是这个结论不是一定的，在一些系统上LinkedList需要不停地new新的Node，这个过程也会耗时
 * 整体来说这两个栈的时间复杂度是一致的
 */
public class Test {
    public static double testStack(Stack<Integer> stack, int opCount){

        Long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount ; i ++){
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount ; i ++){
            stack.pop();
        }
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 10000;

        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack,opCount);
        System.out.println("ArrayStack, time:" + time1 + "s");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testStack(linkedListStack,opCount);
        System.out.println("LinkedListStack, time:" + time2 + "s");
    }
}
