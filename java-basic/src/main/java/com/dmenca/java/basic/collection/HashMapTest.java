package com.dmenca.java.basic.collection;

import java.util.HashMap;
import java.util.HashSet;

public class HashMapTest {
    /**
     * 这段代码片段是为了确保一个给定的容量 cap 是2的幂次方。这通常用于计算哈希表的大小，确保哈希表的大小总是2的幂次方，以便在进行哈希操作时能够更高效地利用位运算。
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 100000) ? 100000 : n + 1;
    }

    public static void main(String[] args) {
        //
        System.out.println("stdr".hashCode());
        System.out.println(tableSizeFor(10));
        HashMap<String,String> hashMap = new HashMap<String,String>();
        System.out.println(hashMap.put("key111","value111"));
        System.out.println(hashMap.get("key111"));
    }

}
