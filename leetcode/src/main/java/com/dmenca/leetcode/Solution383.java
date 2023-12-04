package com.dmenca.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 383. 赎金信
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 *
 * 如果可以，返回 true ；否则返回 false 。
 *
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 */
public class Solution383 {
    /**
     * 需要magazine中的每个字符次数大于ransomNote出现的字符次数
     * 先统计出magazine中每个字符的出现次数，再遍历ransomNote的每个字符，判断该字符是否存在，如果不存在则返回false。
     * 如果存在，则将出现次数-1，判断出现的次数是否已经小于0，如果是则返回false。
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character,Integer> map = new HashMap();
        for (int i =0;i<magazine.length();i++){
            char c = magazine.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for (int i=0;i<ransomNote.length();i++){
            char c = ransomNote.charAt(i);
            Integer count = map.get(c);
            if (count==null||count==0){
                return false;
            }
            count--;
            map.put(c,count);
        }
        return true;
    }

    public boolean canConstruct1(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            cnt[c - 'a']--;
            if(cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {

    }
}
