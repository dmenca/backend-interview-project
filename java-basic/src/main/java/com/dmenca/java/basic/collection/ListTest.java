package com.dmenca.java.basic.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) {

        // 初始化一个ArrayList
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList("hello"));
        // 添加元素到 ArrayList 中
        stringList.add("goodbye");

        System.out.println(stringList);// [hello, world, !, goodbye]
        // 修改 ArrayList 中的元素
        stringList.set(0, "hi");
        System.out.println(stringList);// [hi, world, !, goodbye]
        // 删除 ArrayList 中的元素
        stringList.remove(0);
        System.out.println(stringList); // [world, !, goodbye]

        stringList.add(0,"xxx");
        // Vector是线程安全的列表 Stack继承了Vector 是一个先进后出的栈
        //随着 Java 并发编程的发展，`Vector` 和 `Stack` 已经被淘汰，推荐使用并发集合类（例如 `ConcurrentHashMap`、`CopyOnWriteArrayList` 等）或者手动实现线程安全的方法来提供安全的多线程操作支持。
        Collections.synchronizedList(new ArrayList<>());
        List<String> copyArrayList = new CopyOnWriteArrayList<>();

        // LinkedList 本质是定义了节点的双向链表,拥有Node拥有prev、next指针，List拥有first跟last节点指针
        // 头部插入/删除：只需要修改头结点的指针即可完成插入/删除操作，因此时间复杂度为 O(1)。
        // 尾部插入/删除：只需要修改尾结点的指针即可完成插入/删除操作，因此时间复杂度为 O(1)。
        // 指定位置插入/删除：需要先移动到指定位置，再修改指定节点的指针完成插入/删除，因此需要移动平均 n/2 个元素，时间复杂度为 O(n)。
        List<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.remove(1);


    }
}
