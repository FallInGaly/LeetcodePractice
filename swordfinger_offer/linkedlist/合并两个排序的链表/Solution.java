package swordfinger_offer.linkedlist.合并两个排序的链表;

import datastruct.ListNode;

public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }
        ListNode dummyHead = new ListNode(-1);
        ListNode curNode = dummyHead;
        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                curNode.next = list1;
                list1 = list1.next;
            }else{
                curNode.next = list2;
                list2 = list2.next;
            }
            curNode = curNode.next;
        }
        if(list1 != null){
            curNode.next = list1;
        }
        if (list2 != null){
            curNode.next = list2;
        }
        return dummyHead.next;
    }
}