package swordfinger_offer.linkedlist.合并k个已排序的链表;

import datastruct.ListNode;

import java.util.*;

public class Solution {
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if(lists == null || lists.size() == 0){
            return null;
        }
        ListNode dummyHead = new ListNode(-1);
        ListNode curNode = dummyHead;
        ListNode popNode = null;
        int length = lists.size();
        PriorityQueue<ListNode> queue = new PriorityQueue<>(length, new Comparator<ListNode>(){
            @Override
            public int compare(ListNode node1, ListNode node2){
                return node1.val - node2.val;
            }
        });
        for(int i = 0 ; i < length ; i ++){
            if(lists.get(i) != null){
                queue.add(lists.get(i));
            }
        }
        while(queue.size() > 0){
            popNode = queue.remove();
            if(popNode.next != null){
                queue.add(popNode.next);
            }
            curNode.next = popNode;
            curNode = curNode.next;
        }
        return dummyHead.next;
    }
}
