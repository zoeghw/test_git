package com.zoe.LinkedListStack;

/**
 * Created by g20699 on 2019/7/17.
 *简单的时间复杂度分析 O简单地来说描述的是算法的运行时间和输入数据之间的关系，忽略常数
 * O(1),O(n),O(lgn),O(nlogn),O(n^2)
 * 渐进时间复杂度
 * 考虑最差情况
 * 添加操作
 * addLast(e)   O(1)
 * addFirst(e)  O(n)
 * add(index, e) O(n)
 * resize O(n)
 * 删除操作
 * removeLast(e) O(1)
 * removeFirst(e) O(n)
 * remove(index, e) O(n)
 * resize O(n)
 * 修改操作
 * set(index, e) O(1)
 * 查找操作
 * get(index) O(1)
 * contains(e) O(n)
 * find(e) O(n)
 * 动态数组
 * 增：O(n)
 * 删：O(n)
 * 改：已知索引O(1);未知索引O(n)
 * 查：已知索引O(1);未知索引O(n)
 *
 * resize()计算均摊复杂度的思想
 * 复杂度震荡
 * Lazy 四分之一减半
 */
public class Array<E> {

    private E[] data;
    //设计成员变量，不直接从外部获得信息，破坏类的封装性
    private int size;
    //设计有效变量的长度
    //无序单独声明容量，直接使用data的长度，这样可以少维护一个变量

    //构造函数，传入数组的容量capacity构造Array
    public Array(int capacity){
        //改为泛型的时候不可以将int直接转换为E,需要将类型定义为object，然后在强制转换为E
        data = (E[])new Object[capacity];
        size = 0;
    }

    //不指定参数的构造函数，默认数组容量为10
    public Array(){
        this(10);
        //capacity:是IDEA自动提示，非常有用
    }

    //获取数组中的元素个数
    public int getSize(){
        return size;
    }

    //获取数组的容量
    public int getCapacity(){
        return data.length;
    }

    //返回数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //向所有元素后添加一个新元素
    public void addLast(E e){

        /**可复用
         * if (size == data.length){
         throw new IllegalArgumentException("AddLast failed. Array is full.");
         }
         data[size] = e;
         size ++;
         */
        add(size , e);
    }

    //向所有元素前添加一个新元素
    public void addFirst(E e){
        add(0, e);
    }

    //向指定位置添加元素
    //向index位置插入一个新元素e
    public void add(int index, E e){

        //扩容思路
        if (size == data.length){
            resize(2 * data.length);
        }

        if (index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <=size.");
        }

        for (int i = size - 1 ; i >= index ; i--){
            data[i + 1] = data[i];
        }

        data[index] = e;
        size ++;
    }

    //get方法可以抛出异常
    public E getLast(){
        return get(size - 1);

    }

    public E getFirst(){
        return get(0);
    }


    //获取index索引位置的元素
    //通过封装的方法保证数据的安全
    public E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed.Index is illegal.");
        }
        return data[index];
    }

    //修改index位置的元素为e
    public void set(int index, E e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed.Index is illegal.");
        }
        data[index] = e;

    }

    //查找数组中是否存在元素e，将contains的类型改为E之后，由于data[i]与e都变成了类对象，所以两个类对象之间的比较应该使用equals()
    public boolean contains(E e){
        for(int i = 0; i < size ; i ++){
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    //查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e){
        for(int i = 0; i < size ; i ++){
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    //从数组中删除元素e
    // 思考：如果数组中存在多个e，如何一次删除所有？
    public void removeElement(E e){
        int index = find(e);
        if(index != -1){
            remove(index);
        }
    }

    //从数组中删除index位置的元素，返回删除的元素
    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed.Index is illegal.");
        }
        //先保存要删除的元素
        E ret = data[index];
        for (int i = index + 1 ; i < size ; i ++){
            data[i -1] = data[i];
        }
        size --;
        data[size] = null;
        //此句为非必须，因为后续进行添加操作时，就会自动失去指向性 术语 lotiering objects != memory leak
        //能够手动去除更好

        if (size == data.length/4 && data.length / 2 != 0){
            resize(data.length/2);
        }
        return ret;
    }

    //从数组中删除第一个元素，返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    //从数组中删除最后一个元素，返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }




    @Override
    //覆盖
    //toString方法：
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n" , size , data.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(data[i]);
            if (i != size - 1){
                res.append(",");
            }
        }
        res.append(']');
        return res.toString();


    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for(int i = 0 ; i < size ; i ++){
            newData[i] = data[i];
        }
        data = newData;
    }
}

