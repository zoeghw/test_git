package com.zoe.LinkedListQueue;

/**
 * Created by g20699 on 2019/8/1.
 * 队列的改进：从head段删除元素，从tail端插入元素
 * 由于没有dummyHead，要注意链表为空的情况
 */
public class LinkedListQueue<E> implements Queue<E>{
    private class Node{
        public E e ;
        public Node next ;

        //构造函数
        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null , null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node head, tail;
    private int size;

    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public void enqueue(E e){
        //从队尾
        if(tail == null){
            tail = new Node(e);
            head = tail;
        }else{
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;
    }

    @Override
    public E dequeue(){
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        Node retNode = head;
        head = head.next;
        retNode.next = null;
        if(head == null){
            tail =null;
        }
        size --;
        return retNode.e;
    }

    @Override
    public E getFront(){
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty");
        }
        return head.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue:front");

        Node cur= head;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        //用for的方式写
        //for(Node cur = dummyHead.next ; cur = Null; cur = cur.next){
        // res.append(cur + "->");
        res.append("Null tail");

        return res.toString();
    }

    public static void main(String[] args){

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
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
