package datastruct;
import java.util.*;

public class BST {
    public ArrayList<Integer> preOrder(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode popNode = null;
        while(stack.size() > 0){
            popNode = stack.pop();
            list.add(popNode.val);
            if(popNode.right != null){
                stack.push(popNode.right);
            }
            if(popNode.left != null){
                stack.push(popNode.left);
            }
        }
        return list;
    }

    // 中序遍历最精妙的点在于：使用一个全局的指针来贯穿整个遍历过程，遇到临界左节点就进行指针的重定向,避免重复不断向left指针遍历
    public ArrayList<Integer> inOrder(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        TreeNode popNode;
        while(stack.size() > 0 || curNode != null){
            // 增加元素全部由此处进行
            // 而且此处增加元素只考虑当前指针，而不会提前将node.left左指针入栈
            if(curNode != null){
                stack.push(curNode);
                curNode = curNode.left;
            }else{
                popNode = stack.pop();
                list.add(popNode.val);
                // 此处只是对于指针进行基于right的重定向
                if(popNode.right != null){
                    curNode = popNode.right;
                }else{
                    curNode = null;
                }
            }
        }
        return list;
    }

    public ArrayList<Integer> postOrder(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> reverse = new Stack<>();
        stack.push(root);
        TreeNode popNode;
        while(stack.size() > 0){
            popNode = stack.pop();
            reverse.push(popNode);
            if(popNode.left != null){
                stack.push(popNode.left);
            }
            if(popNode.right != null){
                stack.push(popNode.right);
            }
        }
        while(reverse.size() > 0){
            list.add(reverse.pop().val);
        }
        return list;
    }

    public ArrayList<Integer> layerOrder(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode popNode = null;
        while(queue.size() > 0){
            popNode = queue.remove();
            list.add(popNode.val);
            if(popNode.left != null){
                queue.add(popNode.left);
            }
            if(popNode.right != null){
                queue.add(popNode.right);
            }
        }
        return list;
    }
}
