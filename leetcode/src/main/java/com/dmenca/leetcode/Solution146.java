package com.dmenca.leetcode;

/**
 * 实现一个LRU缓存（最近最少使用），初始化缓存容量
 *  双向链表+哈希表实现，双向链表来保证最近最少使用，头部存储最近使用，尾部存储最少使用。
 *  哈希表用来快速定位到key对应的value值
 */
public class Solution146 {
    class LRUCache{
        class Node{
            public int key;
            public int value;
            public Node prev;
            public Node next;

        }
        public LRUCache(int capacity){

        }

        /**
         * 如果关键字key存在缓存中，则返回关键字的值，否则返回-1
         * @param key
         * @return
         */
        public int get(int key){

        }

        /** 如果关键字key已经存在，则变更数据值value。如果不存在，则向缓存中插入该组。
         *  如果插入的capacity超过了容量，则移除最久未使用的值
         * rug
         * @param key
         * @param value
         */
        public void put(int key,int value){

        }
    }
    public
}
