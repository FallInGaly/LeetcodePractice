package swordfinger_offer.tree.二叉树的镜像;

import datastruct.TreeNode;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param pRoot TreeNode类
     * @return TreeNode类
     */

    // 不要想的太复杂，就和数组的交换两个索引的元素一样的道理，递归的时候不需要同时考虑对称的两个节点，考虑当前节点即可；
    // 因为在前一步镜像之后相对位置就已经变了，必须动态地考虑问题；
    // 即镜像问题，每个节点只要把自己做好就行了，不用去考虑镜像中的另一个自己
    public TreeNode Mirror (TreeNode pRoot) {
        // write code here
        if(pRoot == null){
            return null;
        }
        TreeNode temp = pRoot.left;
        pRoot.left = pRoot.right;
        pRoot.right = temp;
        if(pRoot.left != null){
            pRoot.left = Mirror(pRoot.left);
        }
        if(pRoot.right != null){
            pRoot.right = Mirror(pRoot.right);
        }
        return pRoot;
    }
}
