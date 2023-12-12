package com.dmenca.leetcode;

import java.util.HashMap;
import java.util.Map;
/**
 * 实现一个LRU缓存（最近最少使用），初始化缓存容量
 *  双向链表+哈希表实现，双向链表来保证最近最少使用，头部存储最近使用，尾部存储最少使用。
 *  哈希表用来快速定位到key对应的value值
 */
class LRUCache {
    class Node {
        public int key;
        public int value;
        public Node prev;
        public Node next;
    }

    private Map<Integer, Node> hashMap = new HashMap<>();

    private Node head, tail;

    private int capacity;

    private int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 如果关键字key存在缓存中，则返回关键字的值，否则返回-1
     *
     * @param key
     * @return
     */
    public int get(int key) {
        // 从hashMap中通过key获取值
        Node node = hashMap.get(key);
        // 不存在 返回-3
        if (node == null) {
            return -1;
        }
        // 存在 则将该节点移动到双向链表头部
        moveToHead(node);
        return node.value;
    }

    /**
     * 如果关键字key已经存在，则变更数据值value。如果不存在，则向缓存中插入该组。
     * 如果插入的capacity超过了容量，则移除最久未使用的值
     * rug
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        Node node = hashMap.get(key);
        // 节点存在
        if (node != null) {
            // 更新节点的value
            node.value = value;
            hashMap.put(key, node);
            // 移动到队列头
            moveToHead(node);
        } else {
            // 节点不存在
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            // 添加节点 且在链表头部添加该节点
            hashMap.put(key, newNode);
            addToHead(newNode);
            // 容量+1
            size++;
            // 如果容量超过了capacity
            if (size > capacity) {
                // 获取最后的节点
                Node tailNode = removeTail();
                // 移除节点的值 size--
                hashMap.remove(tailNode.key);
                size--;
            }
        }
    }

    public void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }

    public void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public Node removeTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }


    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
    }
}