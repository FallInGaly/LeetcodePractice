package swordfinger_offer.linkedlist.两个链表的第一个公共结点;

import datastruct.ListNode;

public class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null){
            return null;
        }
        int count1 = 0;
        int count2 = 0;
        ListNode curNode1 = pHead1;
        ListNode curNode2 = pHead2;
        while(curNode1 != null){
            count1 ++;
            curNode1 = curNode1.next;
        }
        while(curNode2 != null){
            count2 ++;
            curNode2 = curNode2.next;
        }
        ListNode FastNode = count1 > count2 ? pHead1 : pHead2;
        ListNode SlowNode = FastNode == pHead1 ? pHead2 : pHead1;
        int fastIndex = 0;
        while(fastIndex < (Math.abs(count1-count2))){
            FastNode = FastNode.next;
            fastIndex ++;
        }
        while(FastNode != null && SlowNode != null){
            if(FastNode == SlowNode){
                break;
            }
            FastNode = FastNode.next;
            SlowNode = SlowNode.next;
        }
        return FastNode == null && SlowNode == null ? null : FastNode;
    }
}
