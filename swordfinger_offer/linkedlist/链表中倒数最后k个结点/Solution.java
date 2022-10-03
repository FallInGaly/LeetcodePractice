package swordfinger_offer.linkedlist.链表中倒数最后k个结点;

import datastruct.ListNode;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param pHead ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode FindKthToTail (ListNode pHead, int k) {
        // write code here
        if(pHead == null || k <= 0){
            return null;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = pHead;
        ListNode fastNode = dummyHead;
        ListNode slowNode = dummyHead;
        int preIndex = 0;
        // 要考虑第k个节点是Null的情况
        while(preIndex <= k){
            if(fastNode == null){
                return null;
            }
            if(preIndex != k){
                fastNode = fastNode.next;
            }
            preIndex ++;
        }
        // 倒数第k个就意味着fastNode必须要到Null，slowNode才可以
        while(fastNode != null){
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }
        return slowNode;
    }
}
