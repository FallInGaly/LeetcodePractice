package swordfinger_offer.linkedlist.删除链表的节点;

import datastruct.ListNode;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param head ListNode类
     * @param val int整型
     * @return ListNode类
     */
    public ListNode deleteNode (ListNode head, int val) {
        // write code here
        if(head == null){
            return null;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode cur = head;
        while(cur != null){
            if(cur.val == val){
                pre.next = cur.next;
                return dummyHead.next;
            }
            pre = cur;
            cur = cur.next;
        }
        return dummyHead.next;
    }
}