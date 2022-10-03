package swordfinger_offer.tree.序列化二叉树;

import datastruct.TreeNode;

public class Solution {
    // 1. 因为前序+中序可以唯一确定一棵二叉树，所以可以使用前序+中序进行序列化
    // 2. 最值得注意的一点是：使用字符串保存整数的时候，一定要使用标识符进行分隔，不然所有的值都挤在一起，如果整型值大于9的话，那么后序只通过String的索引是无法区分每一个整型值的，因为给你"100"，你无法判断他在加入字符串之前是几个值？哪些值等。
    // 所以将多个整型值存入字符串，后序还要反序列化将其区分的场景，在加入字符串的时候一定要使用分隔符进行分隔
    String Serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        StringBuffer stringBufferPre = new StringBuffer();
        StringBuffer stringBufferIn = new StringBuffer();
        preOrder(root, stringBufferPre);
        inOrder(root, stringBufferIn);
        String seria = stringBufferPre.toString().trim() + "&" + stringBufferIn.toString().trim();
        return seria;
    }

    public void preOrder(TreeNode node, StringBuffer stringBufferPre){
        if(node == null){
            return;
        }
        stringBufferPre.append(node.val);
        stringBufferPre.append(" ");
        preOrder(node.left, stringBufferPre);
        preOrder(node.right, stringBufferPre);
    }

    public void inOrder(TreeNode node, StringBuffer stringBufferIn){
        if(node == null){
            return;
        }
        inOrder(node.left, stringBufferIn);
        stringBufferIn.append(node.val);
        stringBufferIn.append(" ");
        inOrder(node.right, stringBufferIn);
    }

    TreeNode Deserialize(String str) {
        if(str == null || str.length() == 0){
            return null;
        }
        String [] strs = str.split("&");
        if(strs.length != 2){
            return null;
        }
        String [] pres = strs[0].split(" ");
        String [] ins = strs[1].split(" ");
        int length = pres.length;
        int [] pre = new int [length];
        int [] in = new int [length];
        for(int i = 0 ; i < length ; i ++){
            pre[i] = Integer.parseInt(pres[i]);
        }
        for(int i = 0 ; i < length ; i ++){
            in[i] = Integer.parseInt(ins[i]);
        }
        return DeserializeRescursion(pre, in, 0, length-1, 0, length-1);
    }

    public TreeNode DeserializeRescursion(int [] pre, int [] in, int preStart, int preEnd, int inStart, int inEnd){
        if(preStart < 0 || preEnd >= pre.length || inStart < 0 || inEnd >= in.length || preStart > preEnd || inStart > inEnd){
            return null;
        }
        TreeNode head = new TreeNode(pre[preStart]);
        if(preStart == preEnd || inStart == inEnd){
            return head;
        }
        int headVal = pre[preStart];
        int headIndexIn = inStart;
        while(headIndexIn < inEnd && in[headIndexIn] != headVal){
            headIndexIn ++;
        }
        int leftTreeLength = headIndexIn - inStart;
        if(headIndexIn > inStart){
            // 说明有左子树
            head.left = DeserializeRescursion(pre, in, preStart+1, preStart+leftTreeLength, inStart, headIndexIn-1);
        }
        if(headIndexIn < inEnd){
            // 说明有右子树
            head.right = DeserializeRescursion(pre, in, preStart+leftTreeLength+1, preEnd, headIndexIn+1, inEnd);
        }
        return head;
    }
}