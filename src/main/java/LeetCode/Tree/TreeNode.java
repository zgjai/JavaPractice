package LeetCode.Tree;

import java.util.function.Function;

import LeetCode.Stack.MyStack;

/**
 * Created by zhangguijiang on 2018/3/2.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

    // 深度优先遍历(DFS)
    // 先序遍历
    // 递归
    public static void preOrderRecursion(TreeNode root, Function<TreeNode, Void> function) {
        if (root == null) {
            return;
        }
        function.apply(root);
        preOrderRecursion(root.left, function);
        preOrderRecursion(root.right, function);
    }

    // 非递归
    public static void preOrderIteration(TreeNode root, Function<TreeNode, Void> function) {
        if (root == null) {
            return;
        }
        MyStack<TreeNode> stack = new MyStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            function.apply(node);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    // 中序遍历
    // 递归
    public static void inOrderRecursion(TreeNode root, Function<TreeNode, Void> function) {
        if (root == null) {
            return;
        }
        inOrderRecursion(root.left, function);
        function.apply(root);
        inOrderRecursion(root.right, function);
    }

    // 非递归
    public static void inOrderIteration(TreeNode root, Function<TreeNode, Void> function) {
        if (root == null) {
            return;
        }
        MyStack<TreeNode> stack = new MyStack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                function.apply(node);
                node = node.right;
            }
        }
    }

    // 后序遍历
    // 递归
    public static void postOrderRecursion(TreeNode root, Function<TreeNode, Void> function) {
        if (root == null) {
            return;
        }
        postOrderRecursion(root.left, function);
        postOrderRecursion(root.right, function);
        function.apply(root);
    }

    // 非递归
    public static void postOrderIteration(TreeNode root, Function<TreeNode, Void> function) {
        if (root == null) {
            return;
        }
        MyStack<TreeNode> stack = new MyStack<>();
        MyStack<TreeNode> output = new MyStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            output.push(node);
        }
        while (!output.isEmpty()) {
            function.apply(output.pop());
        }
    }

    // 广度优先遍历(BFS)，即层次遍历
    // 非递归
    public static void breadFirstIteration(TreeNode root, Function<TreeNode, Void> function) {
    }

    // 递归
    public static void breadFirstRecursion(TreeNode root, Function<TreeNode, Void> function) {

    }
}
