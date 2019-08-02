package com.zoe.LinkedListStack;

/**
 * Created by g20699 on 2019/7/18.
 */
public interface Stack<E> {

    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
