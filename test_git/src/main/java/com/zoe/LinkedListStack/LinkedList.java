package com.zoe.LinkedListStack;


/**
 * Created by g20699 on 2019/7/22.
 * 链表：真正的动态数据结构 不需要处理固定容量的问题 缺点是丧失了随机访问的能力 最简单的动态数据结构
 * 可以更深入地理解引用
 * 更深入地理解递归 辅助组成其他数据结构
 * 数据存储在节点Node中
 *
 * 数组最好用于索引有语意的情况 最大的优点 支持快速查询
 *链表不适合用于索引有语意的情况 最大的优点 动态
 *
 * 时间复杂度
 * addLast(e)       O(n)
 * addFirst(e)      O(1)
 * add(index, e)    O(n)
 * removeLast(e)    O(n)
 * removeFirst(e)   O(1)
 * remove(index,e)  O(n)
 * set(index,e)     O(n)
 * get(index)       O(n)
 * contains(e)      O(n)
 *
 * 增删改查 O(n)
 * 思考：只对链表头的操作是O(1)的
 */
public class LinkedList<E> {
    //对外部用户屏蔽底层细节
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

    //head的存在使得链表向头部添加节点的时候需要有一个addfirst的特殊操作，为了使整个程序看起来更加简洁优雅，实用一个虚拟头结点，这样可以使链表中所有节点都有前一个节点
    //private Node head;
    private Node dummyHead;
    private int size;

    public LinkedList(){
        //head = null;
        dummyHead = new Node(null, null);
        size = 0;
    }

    //获取链表中的元素个数
    public int getSize(){
        return size;
    }

    //返回链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }



    //在链表中间添加新的元素
    //在链表的index（0-based)位置添加新的元素e
    //在链表中不是一个常用操作，只作为练习用
    public void add(int index, E e){

        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        //if(index == 0){
        //    addFirst(e);
        //}
        //else{

        Node prev = dummyHead;
        for(int i = 0 ; i < index ; i ++){
            prev = prev.next;
        }

        //Node node = new Node(e);
        //node.next = prev.next;
        //prev.next = node;

        //优雅写法
        prev.next = new Node(e, prev.next);
        size ++;
    }

    //在链表头添加元素
    public void addFirst(E e){
        //Node node = new Node();
        //node.next = head;
        //head = node;

        //一种优雅的写法
        //head = new Node(e, head);

        add(0 , e);

        //size ++;
    }

    //在链表的末尾添加新的元素e
    public void addLast(E e){
        add(size, e);
    }

    //获得链表的index（0-based)位置的元素e
    //在链表中不是一个常用操作，只作为练习用
    public E get(int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }

        Node cur = dummyHead.next;
        //与插入的循环进行比较，来辨别要找的对象是索引前一个位置还是索引位置
        for(int i = 0 ; i < index ; i ++){
            cur = cur.next;
        }
        return cur.e;
    }

    //获得链表的第一个元素
    public E getFirst(){
        return get(0);
    }

    //获得链表的最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    //修改链表的第index个元素
    public void set(int index, E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }

        Node cur = dummyHead.next;
        for(int i = 0 ; i < index ; i ++){
            cur = cur.next;
        }
        cur.e = e;
    }

    //查找链表中是否存在元素e
    public boolean contains(E e){

        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }


    //从链表中删除第index(0-based)位置的元素，返回删除的元素
    //在链表中不是一个常用的操作，练习用
    public E remove(int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }
        Node prev = dummyHead;
        for(int i = 0 ; i < index ; i ++){
            prev = prev.next;
        }

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size --;

        return retNode.e;
    }

    //从链表中删除第一个元素，返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    //从链表中删除最后一个元素，返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();

        Node cur= dummyHead.next;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        //用for的方式写
        //for(Node cur = dummyHead.next ; cur = Null; cur = cur.next){
        // res.append(cur + "->");
        res.append("Null");

        return res.toString();
    }
}
