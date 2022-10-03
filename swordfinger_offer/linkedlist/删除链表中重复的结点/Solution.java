package swordfinger_offer.linkedlist.删除链表中重复的结点;

import datastruct.ListNode;

public class Solution {
    public ListNode deleteDuplication(ListNode pHead) {
        if(pHead == null){
            return null;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = pHead;
        ListNode pre = dummyHead;
        ListNode cur = pHead;
        while(cur != null && cur.next != null){
            if(cur.val != cur.next.val){
                pre = cur;
                cur = cur.next;
                continue;
            }
            int val = cur.val;
            while(cur != null && cur.val == val){
                cur = cur.next;
            }
            pre.next = cur;
        }
        return dummyHead.next;
    }
}

