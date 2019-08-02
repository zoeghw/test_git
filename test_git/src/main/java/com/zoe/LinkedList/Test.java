package com.zoe.LinkedList;

/**
 * Created by g20699 on 2019/8/1.
 */

public class Test {

    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = 0 ; i < 5 ; i ++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2,666);
        System.out.println(linkedList);

        linkedList.remove(2);
        if(!linkedList.isEmpty()){System.out.println(linkedList);}

        linkedList.removeFirst();
        if(!linkedList.isEmpty()){System.out.println(linkedList);}

        linkedList.removeLast();
        if(!linkedList.isEmpty()){System.out.println(linkedList);}
    }
}

