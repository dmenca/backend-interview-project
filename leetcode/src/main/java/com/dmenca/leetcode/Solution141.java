package com.dmenca.leetcode;

public class Solution141 {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val=x;
            next=null;
        }
    }

    /**
     * 定义一个快指针，一个慢指针，快指针一次走二步，慢指针一次走一步如果慢指针能够跟快指针再次相遇，则代表存在环
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head){
        ListNode quick = head;
        ListNode slow = head;
        while(quick!=null && slow!=null){
            quick = quick.next;
            if(quick.next == null){
                return false;
            }
            quick = quick.next.next;
            slow = slow.next;
            if(quick!=null&&slow!=null&&quick==slow){
                return true;
            }
        }
        return false;
    }
}
