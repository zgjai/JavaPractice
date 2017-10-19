package LeetCode.Greedy;

/**
 * Created by zhangguijiang on 2017/10/19.
 */

/**
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate
 * between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence
 * with fewer than two elements is trivially a wiggle sequence.
 * 
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and
 * negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two
 * differences are positive and the second because its last difference is zero.
 * 
 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence
 * is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the
 * remaining elements in their original order.
 */
public class WiggleSubsequence376 {

    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxWL = 1;
        String state = "BEGIN";
        for (int i = 1; i < nums.length; i++) {
            int difference = nums[i] - nums[i-1];
            switch (state) {
            case "BEGIN":
                if (difference > 0) {
                    state = "UP";
                    maxWL++;
                } else if (difference < 0) {
                    state = "DOWN";
                    maxWL++;
                }
                break;
            case "UP":
                if (difference < 0) {
                    state = "DOWN";
                    maxWL++;
                }
                break;
            case "DOWN":
                if (difference > 0) {
                    state = "UP";
                    maxWL++;
                }
                break;
            }
        }
        return maxWL;
    }
}
