package LeetCode.Tree;

import LeetCode.Stack.MyStack;

/**
 * Given two binary trees, write a function to check if they are the same or not.
 * 
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 */
public class SameTree100 {
    public boolean isSameTreeRecursion(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }
        boolean isSame;
        isSame = isSameTreeRecursion(p.left, q.left);
        if (!isSame) {
            return false;
        }
        isSame = isSameTreeRecursion(p.right, q.right);
        return isSame;
    }

    public boolean isSameTreeIteration(TreeNode p, TreeNode q) {
        MyStack<TreeNode> tmpStackP = new MyStack<>();
        MyStack<TreeNode> tmpStackQ = new MyStack<>();
        if (p == null || q == null) {
            return p == q;
        }
        tmpStackP.push(p);
        tmpStackQ.push(q);
        while (!tmpStackP.isEmpty() && !tmpStackQ.isEmpty()) {
            TreeNode pRoot = tmpStackP.pop();
            TreeNode qRoot = tmpStackQ.pop();
            if (pRoot.val != qRoot.val) {
                return false;
            }
            if (pRoot.left != null && qRoot.left != null) {
                tmpStackP.push(pRoot.left);
                tmpStackQ.push(qRoot.left);
            } else if (pRoot.left != null || qRoot.left != null) {
                return false;
            }
            if (pRoot.right != null && qRoot.right != null) {
                tmpStackP.push(pRoot.right);
                tmpStackQ.push(qRoot.right);
            } else if (pRoot.right != null || qRoot.right != null) {
                return false;
            }
        }
        return tmpStackP.isEmpty() && tmpStackQ.isEmpty();
    }
}
