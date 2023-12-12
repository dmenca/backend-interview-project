package com.dmenca.leetcode;

/**
 双指针 验证回文串
 **/

public class Solution125 {
    public static boolean isPalindrome(String s){
        int left=0;
        int right=s.length()-1;
        s = s.toLowerCase();
        while(left<right){
            char lch= s.charAt(left);
            char rch= s.charAt(right);
            if (!(lch>='a'&&lch<='z')){
                left++;
            }
            if (!(rch>='a'&&rch<='z')){
                right--;
            }
            if (left<right && s.charAt(left)!=s.charAt(right)){
                return false;
            }
            if (left<right && s.charAt(left)==s.charAt(right)){
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("hhh"));
        System.out.println(isPalindrome("abcd"));
        System.out.println(isPalindrome("Aba"));
        System.out.println(isPalindrome("A,B,C,,c,ba"));
    }


}
