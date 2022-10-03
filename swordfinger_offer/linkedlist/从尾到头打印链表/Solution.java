package swordfinger_offer.linkedlist.从尾到头打印链表;

import datastruct.ListNode;

import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> resList = new ArrayList<>();
        if (listNode == null){
            return resList;
        }
        printListFromTailToHeadRecursion(listNode, resList);
        return resList;
    }

    public void printListFromTailToHeadRecursion(ListNode listNode, ArrayList<Integer> resList){
        if(listNode.next == null){
            resList.add(listNode.val);
            return;
        }
        printListFromTailToHeadRecursion(listNode.next, resList);
        resList.add(listNode.val);
    }
}