package swordfinger_offer.tree.二叉树的下一个结点;

import datastruct.TreeLinkNode;

public class Solution {
    // 1. 有右子树
    // 2. 没有右子树 在父节点的右子树 父节点的父节点
    //               在父节点的左子树 父节点
    // 3. 先判断右子树，然后看父节点，没有父节点 返回null

    // 要考虑的边界条件：向右的斜树的叶子结点（较大层高时），考虑空值问题
    //                  父亲节点的下一个节点（右子树是树结构而不是单节点的时候，要取到的是右子树的最小节点，而不是右子树的根节点）
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if(pNode == null){
            return null;
        }
        TreeLinkNode resNode = null;
        if(pNode.right != null){
            resNode = pNode.right;
            while(resNode.left != null){
                resNode = resNode.left;
            }
            return resNode;
        }
        if(pNode.next == null){
            return null;
        }
        TreeLinkNode parent = pNode.next;
        if(parent.left == pNode){
            return parent;
        }
        if(parent.right == pNode){
            TreeLinkNode ancestor = null;
            while(true && parent != null){
                ancestor = parent.next;
                if(ancestor == null){
                    return null;
                }
                if(parent == ancestor.left){
                    break;
                }
                parent = parent.next;
            }
            return ancestor;
        }
        return null;
    }
}
