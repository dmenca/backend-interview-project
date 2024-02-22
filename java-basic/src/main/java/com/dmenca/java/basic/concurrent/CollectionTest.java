package com.dmenca.java.basic.concurrent;

import java.util.*;
import java.util.concurrent.*;

public class CollectionTest {
    public static void main(String[] args) {
        // 线程安全的hashmap
        // JDK1.7 对整个桶数组分割分段Segment分段锁，每一把锁只锁容器中一部分数据，多线程访问不同数据段的数据不会进行锁竞争
        // JDK1.8采用Node数组+链表+红黑树的结构实现，并发控制采用synchronized和CAS来操作。
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap();

        //全局的锁套了一层,内部sync方法后调用的hashmap自身方法
        Map<String,String> stringStringMap = Collections.synchronizedMap(new HashMap<String,String>());

        // Vector 是并发安全的List, 但是也是加了锁，性能比较低
        Vector<String> vector = new Vector<String>();
        vector.add("h1");

        // 线程安全的list 适合读多写少的场景
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        // 写入操作前后通过ReentrantLock lock 加锁和解锁 不修改原数组 而是复制到新的数组，对新的数组进行修改后再赋值回来
        // 读取操作不加锁
        list.add("h1");
        list.get(0);

        // 非阻塞队列 CAS非阻塞算法实现的 性能比较高 没有加锁
        Queue queue1 = new ConcurrentLinkedQueue();

        // 阻塞队列
        // 提供了可阻塞的插入和移除的方法。当队列容器已满，生产者线程会被阻塞，直到队列未满；当队列容器为空时，消费者线程会被阻塞，直至队列非空时为止
        // 3 个常见的 BlockingQueue 的实现类：ArrayBlockingQueue、LinkedBlockingQueue、PriorityBlockingQueue 。
        // ArrayBlockingQueue 内部也是通过ReentrantLock 以及两个condition、和数组实现
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10,true);
        blockingQueue.add(1);
        // 如果数量是0就等待
        try {
            blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 通过单向链表实现 满足先进先出特性
        // 内部通过ReentrantLock 以及两个condition、和链表实现
        LinkedBlockingDeque<Integer> linkedBlockingDeque = new LinkedBlockingDeque(1);
        linkedBlockingDeque.add(1);
        // 报错 超过容量
        linkedBlockingDeque.add(2);
        linkedBlockingDeque.removeFirst();


        // 支持优先级的无界阻塞队列 默认情况下元素采用自然顺序进行排序，也可以通过自定义类实现 `compareTo()` 方法来指定元素排序规则，或者初始化时通过构造器参数 `Comparator` 来指定排序规则。
        // 插入队列的对象必须是可比较大小的
        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<Integer>();
        priorityBlockingQueue.add(1);

    }
}
