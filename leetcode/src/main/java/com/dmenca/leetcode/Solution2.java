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
        两数相加，数字逆序存储,
        使用两个指针分别指向l1、l2的开头，取l1、l2指针指向的值、进位符相加，如果大于等于10则计算进位1,对相加的结果对10取余得到当前的值，
        新建一个节点存储当前值，指针往后移动。直到l1、l2都遍历完毕
     */
    public ListNode addTwoNumbers(ListNode l1,ListNode l2){
        ListNode current = new ListNode();
        ListNode head = current;
        // 进位符初始值为0
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
            // 计算两个节点之和 需要加上进位符
            int sum = val1+val2+addFlag;
            // 如果结果大于等于10则进位符置1
            if(sum>=10){
                addFlag = 1;
            }else{
                addFlag = 0;
            }
            // 对结果取余得到当前的值
            int val3 = sum%10;
            ListNode l3 = new ListNode(val3);
            current.next=l3;
            current = current.next;
            //指针向后移动
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        // 如果遍历完最后还有进位符，则需要加上
        if (addFlag==1){
            ListNode node = new ListNode(1);
            current.next=node;
        }
        return head.next;
    }
}
