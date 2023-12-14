package com.dmenca.java.basic.collection;

import java.util.*;

public class SetTest {
    public static void main(String[] args) {
        // https://www.javatpoint.com/hashset-vs-linkedhashset-in-java
        // HashSet、LinkedHashSet、TreeSet都是Set接口的实现类，都能保证数组唯一，但都不是线程安全的
        // HashSet`、`LinkedHashSet` 和 `TreeSet` 的主要区别在于底层数据结构不同。
        // `HashSet` 的底层数据结构是哈希表（基于 `HashMap` 实现）。
        // `LinkedHashSet` 的底层数据结构是链表和哈希表，元素的插入和取出顺序满足 FIFO。
        // `TreeSet` 底层数据结构是红黑树，元素是有序的，排序的方式有自然排序和定制排序。
        //  底层数据结构不同又导致这三者的应用场景不同。`HashSet` 用于不需要保证元素插入和取出顺序的场景，`LinkedHashSet` 用于保证元素的插入和取出顺序满足 FIFO 的场景，`TreeSet` 用于支持对元素自定义排序规则的场景。
        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(4);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(1);
        hashSet.add(100);
        hashSet.add(99);
        Iterator<Integer> iterator = hashSet.iterator();
        System.out.println("--------hashSet:");
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(4);
        linkedHashSet.add(2);
        linkedHashSet.add(3);
        linkedHashSet.add(1);
        linkedHashSet.add(100);
        linkedHashSet.add(99);
        iterator = linkedHashSet.iterator();
        System.out.println("--------linkedHashSet:");
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("--------treeSet:");
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(4);
        treeSet.add(2);
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(100);
        treeSet.add(99);
        iterator = treeSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
