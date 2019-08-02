package com.zoe.LinkedListQueue;

/**
 * Created by g20699 on 2019/7/19.
 * 队列也是一种线性结构
 * 相比数组，队列对应的操作是数组的子集
 * 先入先出 一端进另一端出 FIFO
 * Queue<E>
 * void enqueue(E)    O(1)均摊
 * E dequeue()        O(n)  挪
 * E getFront()       O(1)
 * int getSize()      O(1)
 * boolean isEmpty()  O(1)
 */
public interface Queue<E> {
    void enqueue(E e);
    E dequeue();
    E getFront();
    int getSize();
    boolean isEmpty();
}
