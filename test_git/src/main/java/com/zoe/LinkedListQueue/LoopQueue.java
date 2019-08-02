package com.zoe.LinkedListQueue;

/**
 * Created by g20699 on 2019/7/22.
 *front == tail 队列为空
 *（tail + 1) %c = front 队列满
 * capacity中，有意识地浪费一个空间，避免队列空和队列满表意相同
 * 不再复用动态数组Array，而是重新从底层写起
 *
 * 循环队列的时间复杂度分析
 * LoopQueue<E>
 * void enqueue(E)    O(1)均摊
 * E dequeue()        O(1)均摊
 * E getFront()       O(1)
 * int getSize()      O(1)
 * boolean isEmpty()  O(1)
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    //添加一点维护成本，简化逻辑
    private int size;

    //构造函数
    public LoopQueue(int capacity){
        //有意识地浪费一个空间
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }
    //没有参数的构造函数直接调用有参数的构造函数，为capacity传入一个默认值即可
    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public boolean isEmpty(){
        return front == tail;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public void enqueue(E e){

        //看队列是否为满
        if((tail + 1) % data.length == front){
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    @Override
    public E dequeue(){

        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0){
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront(){
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    private void resize(int newCapacity){

        E[] newData = (E[])new Object[newCapacity + 1];
        for(int i = 0 ; i < size ; i ++){
            //索引偏移
            newData[i] = data[(i + front) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n" , size , getCapacity()));
        res.append("front[");
        //注意在resize与toString的循环遍历中使用了两种不同的方法，一种是使用front的偏移，另外一种是从front到tail但是过程要循环起来，两种方式可以互换
        for(int i = 0 ; i != tail ; i = (i + 1) % data.length){
            res.append(data[i]);
            if ((i +1) % data.length != tail){
                res.append(", ");
            }
        }
        res.append("]tail");
        return res.toString();
    }

    public static void main(String[] args){

        LoopQueue<Integer> queue = new LoopQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }



}
