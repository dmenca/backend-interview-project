package com.dmenca.leetcode;
/**
 两数相加，数字逆序存储
 */
public class Solution2 {
    class ListNode{

        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){this.val=val;}
    }
    /**
     两数相加，数字逆序存储
     */
    public ListNode addTwoNumbers(ListNode l1,ListNode l2){
        ListNode current = new ListNode();
        ListNode head = current;
        int addFlag=0;
        while(l1!=null || l2!=null){
            int val1=0;
            if(l1 != null){
                val1 = l1.val;
            }
            int val2=0;
            if(l2 != null){
                val2 = l2.val;
            }
            int sum = val1+val2+addFlag;
            if(sum>=10){
                addFlag = 1;
            }else{
                addFlag = 0;
            }
            int val3 = sum%10;
            ListNode l3 = new ListNode(val3);
            current.next=l3;
            current = current.next;
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        return head.next;
    }
}
