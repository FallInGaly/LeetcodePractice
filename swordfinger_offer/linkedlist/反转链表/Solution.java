package swordfinger_offer.linkedlist.反转链表;

import datastruct.ListNode;

public class Solution {
    public ListNode ReverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head.next;
        while(next != null){
            ListNode curNext = next.next;
            cur.next = pre;
            next.next = cur;
            pre = cur;
            cur = next;
            next = curNext;
        }
        return cur;
    }
}

