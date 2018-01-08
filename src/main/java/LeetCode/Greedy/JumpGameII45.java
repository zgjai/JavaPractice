package LeetCode.Greedy;

/**
 * Created by zhangguijiang on 2017/10/20.
 */

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * For example: Given array A = [2,3,1,1,4]
 * 
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last
 * index.)
 * 
 * Note: You can assume that you can always reach the last index.
 */
public class JumpGameII45 {
    public int jump(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return 0;
        }
        int maxIndex = nums[0];
        int currMaxIndex = nums[0];
        int jump_min = 1;
        for (int i = 1; i < nums.length; i++) {
            if (i > currMaxIndex) {
                jump_min++;
                currMaxIndex = maxIndex;
            }
            if (maxIndex < i + nums[i]) {
                maxIndex = i + nums[i];
            }
        }
        return jump_min;
    }
}
