package LeetCode.Greedy;

/**
 * Created by zhangguijiang on 2017/10/19.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is
 * the smallest possible.
 * 
 * Note: The length of num is less than 10002 and will be ≥ k. The given num does not contain any leading zero.
 */
public class RemoveKDigits402 {

    public String removeKdigits(String num, int k) {
        Stack<String> stack = new Stack<>();
        int popCount = 0;
        for (int i = 0; i < num.length(); i++) {
            String s = num.substring(i, i+1);
            while (!stack.isEmpty() && popCount < k && stack.peek().compareTo(s) > 0) {
                popCount++;
                stack.pop();
            }
            if (stack.isEmpty() && "0".equalsIgnoreCase(s)) {
                continue;
            }
            stack.push(s);
        }
        while (popCount < k && !stack.isEmpty()) {
            stack.pop();
            popCount++;
        }
        if (stack.isEmpty()) {
            return "0";
        }
        StringBuilder newNum = new StringBuilder();
        while (!stack.isEmpty()) {
            newNum.insert(0, stack.pop());
        }
        return newNum.toString();
    }

    public static void main(String[] args) {
        RemoveKDigits402 test = new RemoveKDigits402();
        String result = test
                .removeKdigits("123", 3);
        System.out.println(result);
    }
}
